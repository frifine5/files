

Spring WebFlux

    原先的web 框架包括SpringFramework、SpringWebMVC，是专门为ServletAPI和Servlet容器建造的。而反应栈web架构-SpringWebFlux是在5.0版本之后加入的。它是非阻塞的，支持反应背压，运行在如Netty、Undertow和Servlet3.1+容器上的。两个web框架的源码模块在SpringFramwork上共存并以它们的名称（spring-webmvc和spring-webflux)指代。每个模块都是可选择的。应用可以选择其中一个使用，甚至在某些项目中也可以都使用，例如在SpringMVC controllers 中带有反应器的网络客户端WebClient。

1.1 Overview 简介
   WebFlux缘何成立？（WebFulx的由来）

   一方面是需要用很少的硬件资源和少量线程来非阻塞的处理web堆栈信息。Servlet3.1提供了非阻塞/IO的API。然而它的使用需要避开一些ServletAPI的阻塞场景，例如过滤器，获取参数等。这就为什么需要一个全新的在非阻塞期间运行的公共API的原因。这很重要，因为服务器（如Netty)现在多以在异步和非阻塞空间建立起来。

   另一方面是因为函数式编程。诸如java5加入的注解（REST、单元测试），java8的lambda表达式给java加入了函数式编程的可能。这对于构建非阻塞应用和异步逻辑的可延续的APIs来说是一个福音。在编程模型上，java8为实现Spring WebFlux提供了功能性的web端点和带注释的控制器。

1.1.1 "Reactive"的定义 - "响应式"的定义
   我们了解了“非阻塞”和“函数式”的含义，那么“响应式”又意味着什么呢？

   响应式，这个术语，意味着程序模型是建立响应变化的基础上的：网络组件响应I/O事件，UI控制器响应鼠标事件等等。在那样的场景中，响应是非阻塞的。因为相比于阻塞模式，我们现在可以通过“响应”来完成操作或填充数据。
    
   另一个Spring团队与“响应式”建立良好机制的原因是非阻塞的后台压力。在同步模式中，发生阻塞时，压力自然而然的导致了因阻塞而等待。而在非阻塞模式，控制事件的效率很重要，所以高压力不会压垮目标服务器。
    
   响应流是一个小的规范，它定义了在异步组件之间的压力交互。比如说数据库（作为发布者）可以生产数据然后由HTTP服务器（作为订阅者）可以写入响应。相应流主要是让订阅者控制发布者生成数据的速度。

```
    通常来说：如果发布者变慢怎么办？响应流的目的是建立一个反应机制和边界，如果发布者变慢了，响应可以缓存，删除或失败。（而不是阻塞等待超时；这是我加的）

```

   1.1.2 响应式API
    
   相应流在互操作上扮演着重要角色。它作用于库和基础组件，而对应用API不甚有用，因为它是低级别的。应用接口需要高级别、丰富、强大的API来组成异步逻辑，类似于流API，也不仅限于集合。这就是反应库所扮演的角色。

   反应器是Spring WebFlux的库。它提供Mono和Flux 两种API类型，通过一组与操作者对齐的字符来处理 0到1（Mono类型）和0到N（Flux）的操作者序列。反应器是反应流的库，并且所有操作者都支持非阻塞的方式来应对压力。反应器非常关注服务端的java。它是与spring紧密相连开发的。

   WebFlux要求反应器作为反应流与其它反应库交互操作的核心依赖。一般地，WebFlux API接受一个发布者作为输入，适配一个交互的反应类型，并使用它，Flux或Mono作为输出。所以可以通过任何发布者作为输入，你可以申请操作作为输出，但你需要用另一个反应库来适配使用输出信息。只要可行，WebFlux就可以使用RxJava或其它响应库。详情参见Reactive Libraries。

```
   除了Reactive APIs, WebFlux还可以与Kotlin的Coroutines APIs联用，后者是提供命令式的编程方式。下面的Kotlin代码提供了使用Coroutines APIs的例子。
```



   1.1.3 程序模型

​    Spring-web模型中包括反应器基础-SpringWebFlux，包括HTTP的抽象，反应流适配器等支持服务器、编码和足以媲美ServletAPI的核心web处理API--WebHandler API并具有非阻塞特性。

在基础包中，Spring WebFlux提供两种程序模型：

1）Annotated Controllers（注解控制器）：和SpringMvc一样，是基于注解的spring-web模块。SpringMVC和WebFlux控制器同样支持响应返回类型，因此很难将它们区分开。值得注意的不同点是WebFlux也支持响应@RequestBody的声明。

2）Functional Endpoints（函数式端点）：这是基于Lambda，轻量的，函数式编程模型。你可以理解为它是一个小的库，或者是一个单元集合，它能路由和处理请求。它和注解控制器很大的不同是它从始至终掌握着程序的处理，而注解是声明了意图之后就等待被回调。



   1.1.4 适用性/范围

​	SpringMvc 或者 WebFlux

​    二则其一是不全面的。实际上，两者一起使用才能扩展适用范围。两者都被设计成为彼此连接协调使用，他们一边适用又彼此互补。下图表示两者的关系，它们有公共的，也有各自独特的支持。

 ![spring mvc and webflux venn](https://docs.spring.io/spring/docs/current/spring-framework-reference/images/spring-mvc-and-webflux-venn.png) 

   我们建议你遵守以下几点规范：

​	如果您是使用的SpringMVC应用并运行的很好，那么无需改变。命令式编程是易写易读和调试的。您可最大限度的选择库，因为旧的库大多数都是阻塞的。

   如果你已经购买的非阻塞web堆栈，那么SpringWebFlux提供一个在此领域里同样优秀的模块。它还提供了多种服务器（Netty, Tomcat, Jetty, Undertown, Servlet3.1+Container)，多种编程模式(注解和函数式编程)，多种反应器库（Reactor, RxJava, ...）可供选择。

   如果你对轻量的函数式编程框架有兴趣，使用JAVA8的lambda或Kotlin，你也可以使用SpringWebFlux函数式web端点。它也可以在小服务或不复杂的微服务的应用中表现出它的高透明度和控制的优势。

   在微服务架构中，你可以混合使用SpringMVC和SpringWebFlux控制器或函数式端点。它们同样支持注解和函数式模型，以便您可以根据需要选择好用的工具。

   检查依赖是一个简单验证的方式。如果你使用了阻塞的持久化APIs(如JPA,JDBC)或者网络APIs，那么SpringMVC至少是目前常用架构中最好的选择。在技术上，您也可以在单线程中使用Reactor和RxJava去执行阻塞业务，但这将无法充分发挥非阻塞堆栈的优势。

   如果你使用SpringMVC应用调用远程服务，试试reactive WebClient。SpringMVC控制器方法可以返回响应类型（Reactor，RxJava， 或其它）。延迟越大，那么响应式的优势就越明显。SpringMVC控制器还可以调用其它反应器组件。

   如果你在一个大团队，请记住向非阻塞、函数式、声明式编程转换这个陡峭的学习曲线。在不完全切换的情况下，使用WebClient是一种实用的方法。实际上，小措施大收益。我们主张，大范围的切换应用是不必要的。如果你不能保证理解了它的优势，首先学习下非阻塞IO是如何工作的（比如Node.js的单线程的并发）和它的影响。



   1.1.5 服务器

​    SpringWebFlux支持在Tomcat、Jetty、Servlet3.1+容器上，运行非阻塞式的服务，如Netty和UnderTown。所有服务都是适应低级别的，通用的API，以便高级别的程序模块可以获得其支持。

​    SpringWebFlux没有构建开始和结束服务。然而，使用spring的配置和WebFlux基础包组装的应用可以使用几行代码就运行起来。

​    SpringBoot有一个WebFlux启动程序，可以自动执行这些步骤。默认的，启动器使用Netty，但也很容易通过更改Maven或Gradle的依赖切换到Tomcat、Jetty或UnderTown等容器。SpringBoot默认使用Netty，因为它在异步、非阻塞领域中应用更广泛，不论是作为客户端还是服务端。

   Tomcat和Jetty都可以被SpringMVC和WebFlux使用。要记住，它使用哪种方式是非常不同的。SpringMVC是依赖Servlet的阻塞IO，如果应用更需要，让应用直接使用Servlet的API。SpringWebFlux依赖Servlet3.1+的非阻塞IO，在低级别的适配者使用ServletAPI而不是无保留地直接使用它。

对于UnderTown，SpringWebFlux直接使用UnderTown APIs替代Servlet APIs。



   1.1.6 表现

   表现有许多特性和含义。响应式和非阻塞并不保证运行地更快。在一些场景中（如使用WebClient去执行调用服务）它们能。但在大多数情况中，它们更多的是使用非阻塞模式来工作以减轻请求的处理压力。

   关键的是响应式和非阻塞使用小而固定的线程和更少的内存处理需求的能力。那可以是应用保持低负载，因为它们被圈定在一个更易预测的范围内。为了突出它的好吃，你需要一些延迟（包括使用一个又慢又不可预期的网络IO的混合）。这时，响应式堆栈就可以开始显示它的优势，并且优势是明显的。

   1.1.7 并发模型

   不论是SpringMVC还是WebFlux都支持注解的控制器，但它们在并发的一个关键不同之处是默认假定阻塞和线程数。

   在SpringMVC中，假定应用会阻塞线程，因而在处理请求时，servlet容器使用一个大的线程池来缓解潜在的阻塞。

   在SpringWebFlux中，假定应用不会阻塞，因而，非阻塞服务使用一个小的固定容量的线程池来处理请求。

```
"To scale"和"small number of threads"看上去矛盾。不阻塞当前线程（改为回调来替代）意味着不需要额外的线程，也就是说没有阻塞线程需要吸收。
```

调用一个阻塞API

如果您需要使用一个阻塞库，Reactor和RxJava都提供PublishOn操作使继续运行在一个不同的线程上。那意味着可以容易逃跑的窗口。请记住，无论如何，阻塞APIs并不是并发模型的好选择。

可变状态

在Reactor和RxJava中，您通过操作者声明逻辑，并且在运行时，数据按不同阶段处理，形成一个管路。这样做的好处是，不用去保护可变状态，因为管路中的程序代码块永远不会被并发调用。

线程模型

什么样的线程适合在SpringWebFlux上运行？

   在一个“寻常的”SpringWebFlux服务上，您期望一个线程服务其它线程处理请求（典型的是和CPU的核心数相同）。然而Servlet容器会启动更多的线程（比如tomcat是10个），不论是servlet阻塞IO还是Servlet3.1非阻塞IO的使用。

   响应的WebClient采用事件驱动方式运行。所以您将看到一个很小固定数量的操作线程。然而，响应式的Netty在客户端和服务端都有应用，默认使用两个事件loop资源。

   Reactor和RxJava提供线程池的抽象类，名叫Schedulers。用于PublishOn 操作者去切换不同的线程池。Schedulers使用名称对应的并发集合，比如，“parallel”（线程数按cpu边界）或“elastic”（线程数按io边界）。如果您看到这些线程，意味着使用指定的线程池集合。

   数据许可库和别的三方库依赖也可被创建并被它们自己的线程使用。

配置

SpringFramework没有提供启动/停止服务的支持。您可以使用服务指定的配置器APIs来陪孩子线程模型，或者，您可以使用SpringBoot，检查SpringBoot的配置选项。您还可以直接配置WebClient。至于其它的库，请参见它们各自的文档。



1.2 响应式的核心

   Spring-web模块包括以下响应式web应用基础的支撑：

​      支持两种级别的请求处理:

​		1) HttpHandler(Http处理)：Http 请求的基础协议 使用非阻塞IO、响应式流背压处理，此外还有响应式的Netty、UnderTown、Tomcat、Jetty和Servlet3.1+容器等适配。

​         2）WebHandler API: 它是较高水平的处理请求的通用网络的API。顶层连接程序模型构建如注解的控制器和函数式的端点。

​      作为客户端，它可作基本的HttpClient连接器，使用非阻塞IO和响应流来发起Http请求，也可使用响应式Netty和Jetty的HttpClient。更高级的WebClient也是基于该协议构建的。

​      对于客户端和服务端，codecs用来序列化和反序列化Http请求和应答的内容。

   1.2.1 Http处理器

   HttpHandler是使用简单方法处理请求和应答的一个简易协议。它有意最小化，它主要也是唯一的目的是作为不同HTTP服务器APIs的最小抽象。

   下表描述了所支持的服务器APIs：

| 服务器名 | 使用的服务器APIs | 响应流支持 |
|-|-|-|
| Netty | Netty API | Reactor Netty |
| UnderTown | UnderTown API | spring-web:Undertown to Reactive Streams bridge |
| Tomcat | Servlet3.1 非阻塞 I/O; Tomcat API to 写使用ByteBuffers 代替byte[] | spring-web:Servlet3.1 非阻塞 I/O to Reactive Streams bridge |
| Jetty | Servlet3.1 非阻塞 I/O; Jetty API 写使用ByteBuffers 代替byte[] | spring-web:Servlet3.1 非阻塞 I/O to Reactive Streams bridge |
| Servlet 3.1 container | Servlet 3.1 非阻塞I/O | spring-web:Servlet3.1 非阻塞 I/O to Reactive Streams bridge |



下表描述服务的依赖（详见各支持版本）

| 服务器名      | 组ID                    | 工件名（Artifact name）     |
| ------------- | ----------------------- | --------------------------- |
| Reactor Netty | io.projectreactor.netty | reactor-netty               |
| Undertow      | io.undertow             | undertow-core               |
| Tomcat        | org.apache.tomcat.embed | tomcat-embed-core           |
| Jetty         | org.eclipse.jetty       | jetty-server, jetty-servlet |

The code snippets below show using the `HttpHandler` adapters with each server API:

下面的代码片段是使用HttpHandler适配各个服务API的：

**Reactor Netty**

Java

Kotlin

```java
HttpHandler handler = ...
ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
HttpServer.create().host(host).port(port).handle(adapter).bind().block();
```

**Undertow**

Java

Kotlin

```java
HttpHandler handler = ...
UndertowHttpHandlerAdapter adapter = new UndertowHttpHandlerAdapter(handler);
Undertow server = Undertow.builder().addHttpListener(port, host).setHandler(adapter).build();
server.start();
```

**Tomcat**

Java

Kotlin

```java
HttpHandler handler = ...
Servlet servlet = new TomcatHttpHandlerAdapter(handler);

Tomcat server = new Tomcat();
File base = new File(System.getProperty("java.io.tmpdir"));
Context rootContext = server.addContext("", base.getAbsolutePath());
Tomcat.addServlet(rootContext, "main", servlet);
rootContext.addServletMappingDecoded("/", "main");
server.setHost(host);
server.setPort(port);
server.start();
```

**Jetty**

Java

Kotlin

```java
HttpHandler handler = ...
Servlet servlet = new JettyHttpHandlerAdapter(handler);

Server server = new Server();
ServletContextHandler contextHandler = new ServletContextHandler(server, "");
contextHandler.addServlet(new ServletHolder(servlet), "/");
contextHandler.start();

ServerConnector connector = new ServerConnector(server);
connector.setHost(host);
connector.setPort(port);
server.addConnector(connector);
server.start();
```

**Servlet 3.1+ Container**

部署war包到任何一个Servlet3.1+的容器，都可以通过实现和扩展war包里的AbstractReactiveWebInitializer.使用ServletHttpHandlerAdapter并注册为一个servlet来封装一个HttpHandler类。

   1.2.2 WebHandler API  

   在org.springframework.web.server包中,HttpHandler 提供通用的webAPI通过多个WebExceptionHandler、WebFilter、和一个WebHandler组件来处理web请求。通过在ApplicationContext中使用auto-deteted组件或注册builder组件来注册单点WebHttpHandlerBuilder使这个链结合起来。

   当在不同的HTTP服务器上使用HttpHandler时， API意在在web应用中提供一些更为宽的特性，如:

​     具有属性的用户会话；

​     请求属性；

​     解析请求的域或主体；

​     允许解析和缓存数据；

​    多部分数据的抽象；

​    更多。。。



指定的bean类型

   下表列举WebHttpHandlerBuilder组件能够在Spring ApplicationContext中自动检测或者直接注册到其中。

| Bean 名称 | Bean 类型 | 数量 | 描述 |
|-|-|-|-|
|<any>|WebExceptionHandler|0...N|提供WebFilter实例和WebHandler链的异常处理，更多详情，参见Exceptions|
|<any>|WebFilter|0...N|过滤器和WebHandler前后的申请拦截。更多详情，参见Filters（过滤器）|
|webHandler|WebHandler|1|请求的处理|
|webSessionManager|WebSessionManager|0..1|WebSession实例的管理，默认使用ServerWebExchange的DefaultWebSessonManager|
|serverCodecConfigurer|ServerCodecConfigurer|0..1|允许HttpMessageReader实例解析数据或多部分数据，默认使用的方法是ServerWebExchange的ServerCodecConfigurer.create()。|
|localeContextResolver|LocaleContextResolver|0..1|转换为LocaleContext，默认使用ServerWebExchange的AcceptHeaderLocaleContextResolver|
|forwardedHeaderTransformer|ForwardedHeaderTransformer|0..1|为处理类型头，扩展并移除或仅移除，默认不使用|

对于数据

   ServerWebExchange 陈述以下方法来访问表单数据：

java

Kotlin

```
    Mono<MultiValueMap<String, String>> getFormData();
```

DefaultServerWebExchange 使用配置的HttpMessageReader 将表单数据（application/x-www-form-urlencoded)解析为一个MultiValueMap。FormHttpMessageReader配置使用ServerCodecConfigurer实例。详见WebHandler API

多部分数据

和在springMvc中一样

ServerWebExchange 陈述如下方法访问多部分数据：

java

kotlin

``` 
    Mono<MultiValueMap<String, Part>> getMultipartData();
```

DefaultServerWebExchange 使用配置的HttpMessageReader<MultiValueMap<String, Part>>将 multipart/form-data解析为一个MultiValueMap对象。当前，SynchronossNIO Multipart是唯一支持的三方库，也是唯一已知的非阻塞的转换多部分请求的库。它使用ServerCodecConfigurer Bean来开启。

在流中，转换多部分数据，你可以使用Flux<Part>代替HttpMessageReader<Part>来返回。例如，一个注解的控制器，使用@RequestPart支持类似Map通过名称来访问单个组件，因而解析全部多部分数据。对比之下，你可使用@RequestBody去检测内容得到Flux<Part> 而不用获取一个MultiValueMap。

Forwarded Headers 转发头

和SpringMVC一样

   当请求穿过代理，主机，端口，其报文头有可能变了，所以它成为一个挑战，那就是如何在客户端，创建一个正确的指向主机端口和目标的链接。

   RFC7239号文件，定义了HTTP转发头的协议，那就是代理将提供原始请求的信息。包括其它的非标准的头信息：如X-Forwarded-Host, X-Forwarded-Port, X-Forwarded-Proto, X-Forwarded-Ssl和X-Forwarded-Prefix.

转发头转换器是一个多主机端口目标的请求的组件，它基于转发头，并且在转换完后移除它们。你可以使用forwardedHeaderTransformer来声明使用它，它将被检测和使用。

因为应用无法知道头信息是代理增加的还是客户端恶意的，所以转发头需遵循一些安全注意事项。这就是为什么在代理的信任边界中需要删除来自外部的不被信任的转发信息。你也可将转发器设置为remove=true，那样就不会使用转发头信息而直接移除。

``` 
在5.1中，ForwardedHeaderFilter已经被弃用，而被ForwardedHeaderTransfrmer替代。所以转发头可在交换建立之前被处理。如果过滤器可以随处配置，那可以使用f来代替
```

   1.2.3 过滤器

在WebHandler API， 你可使用WebFilter在rest处理链的前后实现拦截逻辑操作。使用WebFlux配置，注册WebFilter只需要声明一个SpringBean，和（可选的）通过@Order指定顺序。

CORS (Cross-Origin Resource Sharing,跨域资源共享)

Spring WebFlux通过注解或控制器提供细粒度的支持跨域资源共享的配置。然而，使用它是必须使用安全机制，基于CorsFilter，并且它的顺序必须在安全链拦截器的前面。详见CORS和CORS WebFilter。

1.2.4 异常

在WebHandler API中，你可使用WebExceptionHandler去处理来自WebFilter和WebHandler的异常。使用WebFlux配置，注册WebExceptionHandler易如声明一个Spring Bean，可用@Order来指定执行的顺序或者实现Ordered接口。下表描述可用的WebExceptionHandler实现类：

|异常处理类|描述|
|-|-|
|ResponseStatusExceptionHandler|通过设置HTTP异常的状态码的应答来处理异常的类ResponseStatusException|
| WebFluxResponseStatusExceptionHandler | ResponseStautsExceptionHandler的扩展，可以使用@ResponseStuts注解来声明任何异常。这个处理类在WebFlux配置中声明。 |

   1.2.5 转码器

spring-web和spring-core模块提供了字节内容往更高级对象的非阻塞IO的序列化和反序列化（功能）。如下描述：

​	Encoder和Decoder是独立于HTTP 编码/解码的低级别协议。

​    HttpMessageReader和HttpMessageWriter是Http消息内容的协议。

​	编/解码器可在使用的应用中适配使用：编码器使用EncoderHttpMessageWriter适配，而解码器使用DecoderHttpMessageReader.

   DataBuffer(数据缓存)抽象成不同的字节缓存描述(如Netty, ByteBuf, java.nio.ByteBuffer等)，同时也是转码器工作的地方。

  spring-core模块提供byte[], ByteBuffer, DataBuffer, Resource 和Spring encoder和decoder等实现类。spring-web模块提供JSON，Jackson Smile, JAXB2， Protocol Buffers 和其它编解码Http message 读写数据，多部分数据内容和服务发送事件以及其它。

  ClientCodecConfigurer 和 ServerCodecConfigurer 是典型的使用配置和定制化的转码器应用。参见HTTP消息转码章节。

Jackson JSON

   JSON和二进制JSON都是由Jackson库支持的。

   Jackson2Decoder 功能如下：

​       json异步非阻塞解析器用于将字节流聚合到TokenBuffer中，并用json对象来表示。

​       每个TokenBuffer通过传递json 对象map来创建高级的对象。

​       解析一个单值时只需要一个TokenBuffer。

​      当解析一个多值时，每个TokenBuffer通过对象map像描述表格一样的对象。输入内容可以是json数组，或者类型是“application/stream+json"类型的就是按行分割的json。

   Jackson2Encoder功能如下：

​      对于单个值的，简单序列化成对象map；

​      对于多个值的，如类型是"application/json",默认的将使用Flux#collectToList()序列化成结果集；

​      对于多个值的，如类型是"application/stream+json"或"application/stream+x-jackson-smile"，使用行分割json的格式去编码和写每个值；

​       对于SSE，每个事件调用Jackson2Encoder，并输出它们都没有延迟的输出交付。

``` 
Jackson2Encoder和Jackson2Decoder 默认不支持String类型，换而言之，它们使用CharSequenceEncoder来事项字符或字符序列的序列化。如果要使用string数组的，需要使用Flux#collectToList()和编码一个Mono<List<String>>.
```

  表单数据

​    FormHttpMessageReader 和 FormHttpMessageWriter支持"application/x-www-form-urlencoded"的内容编码。

​    服务端，内容需要被多次访问，ServerWebExchange提供ForHttpMessageReaer专门的getFormData()方法来解析内容，并缓存便于再次访问。详见WebHandler API章节。

​	一旦getFormData()被使用，那么原始的数据将不可再被从requestbody中读取。因此应用需要统一通过ServerWebExchange来获取缓存表单的数据，而不是再从requestbody中读取。

多部分

​	多部分的读写类支持”multipart/form-data"内容的读写。在使用时，其实际解析为Flux<Part>并被放入MultiValueMap中。现在是使用同步的非阻塞的多部分解析。

​    在服务端，多部分内容也会被多次访问，ServerWebExchange提供专门的getMultipartData()方法读取和重复访问。

​    一旦被其读取，那么requestBody中的原始数据将不可读，为保持一致性，随后都只从这个方法中读取，而不是再从requestBody中解析。

流

​    当作为响应时的流数据，定期发送数据保证客户端的连接很重要。比如发送一个仅提交/空的/或者其它“no-op"的数据来保持心跳。

数据缓存

​	在WebFlux中， 数据缓存使用字节缓存。spring core的部分可以关联 Data Buffers and Codecs章节。理解像Netty、字节缓存被池化和计数的关键是何时释放并回收以避免内存溢出。

​		WebFlux应用通常不需要担心这个问题，除非直接撤销或生产数据缓存，而不是依赖转码器来转换为高级对象。或者它们选择创建自定义的转码器。如果是这样，请复习Data Buffers and Codecs章节，尤其是Using DataBuffer。

   1.2.6 日志

​    调试级别的日志在springWebflux中被设计成紧凑的，最小的，人性化的。它关注高价值位的信息，它们被一遍遍使用，而其它的只在特定问题中有用。

​	跟踪级别的日志通常遵循调试日志同样的原则，但可以被用在任何问题的调试中。进一步的有些日志信息在跟踪和调试中显示不同级别的细节。

   良好的日志来自于日志使用的经验。如果你发现任何不符合规定的地方，请告诉我们。

日志标识

   在webFlux中，单一请求可以被多个线程执行，线程号在属于同一请求的交互日志中并无作用。就是为什么webFlux日志信息默认使用请求指定的id作为前缀。

   在服务端，log标识号以ServerWebExchange的LOG_ID_ATTRIBUTE属性保存，而基于该ID的完整格式化前缀来源于ServerWebExchange.getLogPrefix()函数。在客户端，日志ID号被保存在ClientRequest类的LOG_ID_ATTRIBUTE属性中，而格式化前缀从ClientRequest.logPrefix()方法生产。

   敏感数据

   调试和追踪日志可以记录敏感信息。所以参数和头信息默认被屏蔽，且必须显式调用才能记录下来。

下面是几个服务端调用的例子：

Java

Kotlin

```java
@Configuration
@EnableWebFlux
class MyConfig implements WebFluxConfigurer {

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().enableLoggingRequestDetails(true);
    }
}
```

下面是客户端调用的例子

Java

Kotlin

```java
Consumer<ClientCodecConfigurer> consumer = configurer ->
        configurer.defaultCodecs().enableLoggingRequestDetails(true);

WebClient webClient = WebClient.builder()
        .exchangeStrategies(ExchangeStrategies.builder().codecs(consumer).build())
        .build();
```

### 1.3. 转发处理类`DispatcherHandler`



































