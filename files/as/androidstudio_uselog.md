原

# Argument for @NotNull parameter 'name' of com/android/tools/idea/welcome/Platform.<init> must not be

​											

​                  启动Android studio时候报如下错误java.lang.RuntimeException:  java.lang.IllegalArgumentException: Argument for @NotNull parameter  'name' of com/android/tools/idea/welcome/Platform.<init> must not  be null。
 解决方法：
 1）进入刚安装的Android Studio目录下的bin目录。找到idea.properties文件，用文本编辑器打开。
 2）在idea.properties文件末尾添加一行： disable.android.first.run=true ，然后保存文件。
 3）关闭Android Studio后重新启动，便可进入界面。             