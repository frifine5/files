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
。

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



