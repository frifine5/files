# 页面自动执行（加载）js的几种方法]

一、JS方法
1.最简单的调用方式，直接写到html的body标签里面：

```
<html> 
    <body onload="load();">
    </body> 
</html>
```

2.在JS语句调用：

```
<script type="text/javascript">
    function myfun() 　　{ 　　 
        alert("this window.onload"); 　　
    } 　　
    /*用window.onload调用myfun()*/　
    　
    // 不要括号
    window.onload = myfun;
</script>
```



 

3.JS调用 onload方法 



```
<script type="text/javascript">
    window.onload = function(){
        func1();
        func2();
        func3(); 
    }
</script>
```



 

二、JQ方法

1.整个页面的document全部加载完成以后执行。不幸的这种方式不仅要求页面的DOM  tree全部加载完成，而且要求所有的外部图片和资源全部加载完成。更不幸的是，如果外部资源，例如图片需要很长时间来加载，那么这个js方法执行感觉就比较慢了。也就是说这是一种最严谨的页面加载完再执行方法的方法。

```
window.onload =function() { 
    $("table tr:nth-child(even)").addClass("even"); 
    //这个是jquery代码 
};
```

 

2.仅只需要加载所有的DOM结构，在浏览器把所有的HTML放入DOM tree之前就执行方法。包括在加载外部图片和资源之前。

```
$(document).ready(function() { 
    //任何需要执行的js特效
    $("table tr:nth-child(even)").addClass("even"); 
});
```

 

有一种灰常简便的写法：

```
$(function() { 
    $("table tr:nth-child(even)").addClass("even"); 
    //任何需要执行的js特效
}
```