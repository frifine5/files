

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













   

   

   

   



   