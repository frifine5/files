### Django note
#### info
```
	urls.py
网址入口，关联到对应的views.py中的一个函数（或者generic类），访问网址就对应一个函数。

	views.py
处理用户发出的请求，从urls.py中对应过来, 通过渲染templates中的网页可以将显示内容，比如登陆后的用户名，用户请求的数据，输出到网页。

	models.py
与数据库操作相关，存入或读取数据时用到这个，当然用不到数据库的时候 你可以不使用。

	forms.py
表单，用户在浏览器上输入数据提交，对数据的验证工作以及输入框的生成等工作，当然你也可以不使用。

templates 文件夹

	views.py 中的函数渲染templates中的Html模板，得到动态内容的网页，当然可以用缓存来提高速度。

	admin.py
后台，可以用很少量的代码就拥有一个强大的后台。

	settings.py
Django 的设置，配置文件，比如 DEBUG 的开关，静态文件的位置等。

```

#### env install
##### 安装python  pip 
 ubuntu：
 apt-get install python-dev python-pip


##### 安装Django
 pip install Django
 指定版本
 pip install Django==1.8.16    or 1.11.8

 升级pip
 pip install --upgrade pip


#### basical command基本命令
```
1.新建项目
	django-admin.py startproject [project_name]
	
2.新建app
切换到项目目录下cd project_name
	django-admin.py startapp [app_name]
	或者
	python manage.py startapp [app_name]
	
3.创建库表
#1.7.1及以上版本
	1）创建更改文件
	python manage.py makemigrations
	2)将生成的py文件应用到数据库
	python manage.py migrate
	
#1.6及以下版本
	python manage.py syncdb
	
4.使用开发服务器

开发服务器，即开发时使用，一般修改代码后会自动重启，方便调试和开发，但是由于性能问题，建议只用来测试，不要用在生产环境。
	python manage.py runserver
 
# 当提示端口被占用的时候，可以用其它端口：
	python manage.py runserver 8001
	python manage.py runserver 9999
（当然也可以kill掉占用端口的进程，具体后面有讲，此处想知道的同学可查下 lsof 命令用法）
 
# 监听机器所有可用 ip （电脑可能有多个内网ip或多个外网ip）
	python manage.py runserver 0.0.0.0:8000
# 如果是外网或者局域网电脑上可以用其它电脑查看开发服务器
# 访问对应的 ip加端口，比如 http://172.16.20.2:8000

5. 清空数据库
	python manage.py flush

此命令会询问是 yes 还是 no, 选择 yes 会把数据全部清空掉，只留下空表。

6. 创建超级管理员
	python manage.py createsuperuser
# 按照提示输入用户名和对应的密码就好了邮箱可以留空，用户名和密码必填
# 修改 用户密码可以用：
	python manage.py changepassword username
	
7. 导出数据 导入数据
	python manage.py dumpdata appname > appname.json
	python manage.py loaddata appname.json


8. Django 项目环境终端
	python manage.py shell

如果你安装了 bpython 或 ipython 会自动用它们的界面，推荐安装 bpython。

这个命令和 直接运行 python 或 bpython 进入 shell 的区别是：你可以在这个 shell 里面调用当前项目的 models.py 中的 API，对于操作数据，还有一些小测试非常方便。

9. 数据库命令行
	python manage.py dbshell

Django 会自动进入在settings.py中设置的数据库，如果是 MySQL 或 postgreSQL,会要求输入数据库用户密码。
在这个终端可以执行数据库的SQL语句。如果您对SQL比较熟悉，可能喜欢这种方式。

10. 更多命令
终端上输入 python manage.py 可以看到详细的列表，在忘记子名称的时候特别有用

```









### nginx 部署Django 项目

1. nginx安装 

   ```
   apt-get install nginx
   use command:
   	/etc/init.d/nginx start	#启动
   		/etc/init.d/nginx stop	#停止
   		/etc/init.d/nginx restart #重启
   	
   	nginx配置文件
   	/etc/nginx/nginx.conf
   ```

2. uwsgi 安装和使用

  ```
  python3 -m pip install uwsgi
测试：创建test.py
def application(env, start_response):
	start_response('200 OK', [('Content-Type','text/html')])
	return [b"Hello uwsgi"]

运行测试文件：
uwsgi --http :8001 --wsgi-file test.py
浏览器打开： 127.0.0.1:8001
  ```
  ```
  配置Django与uwsgi连接。假定Django项目路径为[project_dir]
  uwsgi --http :8001 --chdir [project_dir] --wsgi-file [project_name]/wsgi.py --master --processes 1 --threads 1 --stats 127.0.0.1:9191
  
  
  # 常用选项
http ： 协议类型和端口号
processes ： 开启的进程数量
workers ： 开启的进程数量，等同于processes（官网的说法是spawn the specified number ofworkers / processes）
chdir ： 指定运行目录（chdir to specified directory before apps loading）
wsgi-file ： 载入wsgi-file（load .wsgi file）
stats ： 在指定的地址上，开启状态服务（enable the stats server on the specified address）
threads ： 运行线程。由于GIL的存在，我觉得这个真心没啥用。（run each worker in prethreaded mode with the specified number of threads）
master ： 允许主进程存在（enable master process）
daemonize ： 使进程在后台运行，并将日志打到指定的日志文件或者udp服务器（daemonize uWSGI）。实际上最常用的，还是把运行记录输出到一个本地文件上。
pidfile ： 指定pid文件的位置，记录主进程的pid号。
vacuum ： 当服务器退出的时候自动清理环境，删除unix socket文件和pid文件（try to remove all of the generated file/sockets）

  ```

3. nginx + uwsgi + Django 三者结合

目录：
   project_name/
   |--manage.py
   |--project_name/
   |  |--__init__.py
   |  |--settings.py
   |  |--urls.py
   |  |--wsgi.py			# Django 与 uwsgi的关联文件？
   |--project_uwsgi.ini		#uwsgi启动命令的自定义加载文件


1)在我们通过Django创建myweb项目时，在子目录myweb下已经帮我们生成的 wsgi.py文件。所以，我们只需要再创建myweb_uwsgi.ini配置文件即可，当然，uwsgi支持多种类型的配置文件，如xml，ini等。此处，使用ini类型的配置。

```
	# myweb_uwsgi.ini file
	[uwsgi]
	# Django-related settings
	socket = :8000
	
    # the base directory (full path)
    chdir           = /home/fnngj/pydj/myweb
    
    # Django s wsgi file
    module          = myweb.wsgi

	# process-related settings
	# master
	master          = true

	# maximum number of worker processes
	processes       = 4

    # ... with appropriate permissions - may be needed
    # chmod-socket    = 664
    # clear environment on exit
    vacuum          = true
```

 　　这个配置，其实就相当于在上一小节中通过wsgi命令，后面跟一堆参数的方式，给文件化了。
　　socket  指定项目执行的端口号。
　　chdir   指定项目的目录。
　　module  myweb.wsgi ，可以这么来理解，对于myweb_uwsgi.ini文件来说，与它的平级的有一个myweb目录，这个目录下有一个wsgi.py文件。
　　其它几个参数，可以参考上一小节中参数的介绍。


2)接下来，切换到myweb项目目录下，通过uwsgi命令读取myweb_uwsgi.ini文件启动项目。
```
	cd /home/fnngj/pydj/myweb/
	uwsgi --ini myweb_uwsgi.ini
```

3)再接下来要做的就是修改nginx.conf配置文件。打开/etc/nginx/nginx.conf文件，添加服务器设置的相关内容
```
server {
    listen         8099; #服务端口
    server_name    127.0.0.1;
    charset UTF-8;
    access_log      /var/log/nginx/myweb_access.log;
    error_log       /var/log/nginx/myweb_error.log;

    client_max_body_size 75M;

    location / { 
        include uwsgi_params;
        uwsgi_pass 127.0.0.1:8000;
        uwsgi_read_timeout 2;
    }   
    location /static {
        expires 30d;
        autoindex on; 
        add_header Cache-Control private;
        alias [project_dir]/[project_name/app_name/selfdefined_name]/static/;
     }
 }

```

4)启动nginx，然后访问：http://127.0.0.1:8099/
　　通过这个IP和端口号的指向，请求应该是先到nginx的。如果你在页面上执行一些请求，就会看到，这些请求最终会转到uwsgi来处理。
　　
　　
　　
4. supervisor 管理进程

1)下载安装supervisor
	pip install supervisor
	
2)配置文件
	/etc/supervisor/supervisord.conf		#默认配置
	/etc/supervisor/conf.d/*.conf			#子进程配置，由默认配置的include加载
每个应用可以使用一个单独的.conf文件进行配置
like below：
```
[program:prj_name]								#项目名
directory=/opt/bin								#脚本目录
command=/usr/bin/python /opt/bin/test.py		#脚本执行命令
#supervisor启动的时候是否随着同时启动，默认true
autostart=true
#当程序exit的时候，这个program不会自动重启,默认unexpected
#设置子进程挂掉后自动重启的情况，有三个选项，false,unexpected和true。如果为false的时候，无论什么情况下，都不会被重新启动，如果为unexpected，只有当进程的退出码不在下面的exitcodes里面定义的
autorestart=false
startsecs=1		#这个选项是子进程启动多少秒之后，此时状态如果是running，则我们认为启动成功了。默认值为1
#日志输出
stderr_logfile=/tmp/blog_stderr.log
stdout_logfile=/tmp/blog_stdout.log
user = xx		#脚本运行的用户身份
redirect_stderr = true				#把 stderr 重定向到 stdout，默认 false
stdout_logfile_maxbytes = 20M		#stdout 日志文件大小，默认 50MB
stdout_logfile_backups = 20			#stdout 日志文件备份数

```

3)运行
```
	/etc/init.d/supervisor start	#启动
	/etc/init.d/supervisor stop		#停止
```


4)web界面操作
在supervisor全局配置中增加[inet_http_server]选项组参数
```
[inet_http_server]
port=xx.xx.0.1:9001
username=user
password=123abc
```

5)子进程管理（命令行）
```
#查看
supervisorctl status
prj1		RUNNING		pid xxxx, uptime x:xx:xx
prj2 		RUNNING 	pid xxxx, uptime x:xx:xx

#关闭、开启指定子进程
supervisor stop prj1
prj1: stopped

supervisor start prj1
prj1: started

#关闭、开启所有
supervisor stop all
supervisor start all
```
详细可参见  http://supervisord.org/configuration.html


some notices
```
1. uwsgi定义的线程多时，supervisor关闭操作不能完全执行。
```






























