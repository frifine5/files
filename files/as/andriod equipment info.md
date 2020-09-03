    列表内容

名称 	接口参数名称 	备注 	示例
序列号 	getDeviceId 	序列号IMEI 	865872025238821
andrlid_id 	getString 	android_id 	bcbc00f09479aa5b
手机号码 	getLine1Number 	手机号码 	13117511178
手机卡序列号 	getSimSerialNumber 	手机卡序列号 	89860179328595969501
IMSI 	getSubscriberId 	IMSI 	460017932859596
手机卡国家 	getSimCountryIso 	手机卡国家 	cn
运营商 	getSimOperator 	运营商 	46001
运营商名字 	getSimOperatorName 	运营商名字 	中国联通
国家iso代码 	getNetworkCountryIso 	国家iso代码 	cn
网络运营商类型 	getNetworkOperator 	返回MCC+MNC代码 	(SIM卡运营商国家代码和运营商网络代码)(IMSI) 46001
网络类型名 	getNetworkOperatorName 	返回移动网络运营商的名字(SPN) 	中国联通
网络类型 	getNetworkType 		3
手机类型 	getPhoneType 	手机类型 	1
手机卡状态 	getSimState 		1
mac地址 	getMacAddress 	mac地址 	a8:a6:68:a3:d9:ef
蓝牙名称 	getName 		HUAWEI TAG-TL00
返回系统版本 	getDeviceSoftwareVersion 		null
CPU型号 	cpuinfo 	CPU的型号 	MT6592
固件版本 	getRadioVersion 	无线电固件版本号，通常是不可用的 	MOLY.WR8.W1328.MD.TG.MP.V1.P22, 2014/07/15 19:57
Build系列 	android.os.Build 		
系统版本 	RELEASE 	获取系统版本字符串。如4.1.2 或2.2 或2.3等 	4.4.4
系统版本值 	SDK 	系统的API级别 一般使用下面大的SDK_INT 来查看 	19
品牌 	BRAND 	获取设备品牌 	Huawei
型号 	MODEL 	获取手机的型号 	HUAWEI G750-T01
ID 	ID 	设备版本号 	HUAWEITAG-TLOO
DISPLAY 	DISPLAY 	获取设备显示的版本包（在系统设置中显示为版本号）和ID一样 	TAG-TLOOCO1B166
产品名 	PRODUCT 	整个产品的名称 	G750-T01
制造商 	MANUFACTURER 	获取设备制造商 	HUAWEI
设备名 	DEVICE 	获取设备驱动名称 	hwG750-T01
硬件 	HARDWARE 	设备硬件名称,一般和基板名称一样（BOARD） 	mt6592
指纹 	FINGERPRINT 	设备的唯一标识。由设备的多个信息拼接合成 	Huawei/G750-T01/hwG750-T01:4.2.2/HuaweiG750-T01/C00B152:user/ota-rel-keys,release-keys
串口序列号 	SERIAL 	返回串口序列号 	YGKBBBB5C1711949
设备版本类型 	TYPE 	主要为user 或eng. 	user
描述build的标签 	TAGS 	设备标签。如release-keys 或测试的 test-keys 	release-keys
设备主机地址 	HOST 	设备主机地址 	scmbuild
设备用户名 	USER 	基本上都为android-build 	queen
固件开发版本代号 	codename 	设备当前的系统开发代号，一般使用REL代替 	REL
源码控制版本号 	build_incremental 	系统源代码控制值，一个数字或者git hash值 	C01B166
主板 	board 	获取设备基板名称 	TAG-TL00
主板引导程序 	bootloader 	获取设备引导程序版本号 	unkonwn
Build时间 	time 	Build时间 	1476084456000
系统的API级别 	SDK_INT 	数字表示 	19
cpu指令集1 	CPU_ABI 	获取设备指令集名称（CPU的类型） 	arm64-v8a
cpu指令集2 	CPU_ABI2 					

WifiManager 	WIFI相关 		
蓝牙地址 	getAddress 	蓝牙地址MAC地址 	6a:cd:57:f2:3b:59
无线路由器名 	getSSID 	WIFI名字 	210e03fcf0
无线路由器地址 	getBSSID 		ce:ea:8c:1a:5c:b2
内网ip(wifl可用) 	getIpAddress 	可以用代码转成192.168形式 	-2023511872
Display 	屏幕相关 		
屏幕密度 	density 	屏幕密度（像素比例：0.75/1.0/1.5/2.0） 	2.0
屏幕密度 	densityDpi 	屏幕密度（每寸像素：120/160/240/320） 	480
手机内置分辨率 	getWidth 	内置好的不准确已废弃API 	720
手机内置分辨率 	getHeight 		1184
x像素 	xdpi 	屏幕x方向每英寸像素点数 	422.03
Y像素 	ydpi 	屏幕y方向每英寸像素点数 	424.069
字体缩放比例 	scaledDensity 			2.0	



