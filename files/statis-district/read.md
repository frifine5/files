## 国家统计局 -- 行政区划代码 （省市区）

#### excel表为2017年统计数据

#### java文件为分析拆除代码

##### 1. 依赖poi库

<!-- 解析Excel文件的jar包 用于2003- 版本的excel -->
<!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
<dependency> 
	<groupId>org.apache.poi</groupId>
	<artifactId>poi</artifactId>
	<version>3.17</version>
</dependency>

<!-- 解析Excel文件的jar包 用于2007+ 版本的excel -->
<dependency>
	<groupId>org.apache.poi</groupId> 
	<artifactId>poi-ooxml</artifactId>
	<version>3.17</version>
	</dependency><dependency>
	<groupId>junit</groupId>
	<artifactId>junit</artifactId>
	<version>4.12</version>
	<scope>test</scope>
</dependency>