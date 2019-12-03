func



p1.: log in /sign in

  register by self-define name or email or phone.

  sign in by name and password



p2.: register view 

​	input seal base option and optional option:

​		name, organization, address, type, valid-time, and p10, and rules version(for selection)

​        preview seal Image or upload your self-define image.

​        is select maker's cert or default

​		is upload self-cert by self-define.

​		

p3.: make and view process

​     do make e-seal process

​	 show e-seal structure information:

​			NAME, CODE, VERSION, IMAGE, VALID , OWN-DN, ISSUE-DN

​     

p4.: correct message or modify or  redo or refuse approve.

​	show e-seal structure information and approve information and do approved process

​	some power limit to other operator



p5.: manage role - divide role duty of other people

​	one role one duty

​	

p6.: own log and full log



> 责任链转换， 责任链历史。
>
> 权限限制，权限分配，权限调配

​			



p7.: for personal do 

​	one time one thing and provide use-PDF-file record 









数据分析：

 >标识：ID= "ES" ，固定
 >
 >版本号：version
 >
 >厂商：Vid= ""， 用于厂商区分和互认； cyk

> 印章标识：esID= "" ， 印章编号/标识/唯一号等

> 印章类型：type= 0，1，2.. 印章类型，字典编号（1-单位；2-个人）
>
> 印章名称：name
>
> 签章人列表类型，
>
> 签章人证书列表：证书 + 杂凑值
>
> 印章制作时间
>
> 印章有效期起始
>
> 印章有效期终止

> 印章图片类型（格式）
>
> 印章图片数据
>
> 图像宽
>
> 图像高

> 自定义数据（厂商自定义，可选项）

> 制章人证书
>
> 制章人证书算法
>
> 电子印章的签名值，明文范围为印章信息项





验证次序

​	格式验证， 归属验证， 时间有效性验证，证书验证， 签名值验证。