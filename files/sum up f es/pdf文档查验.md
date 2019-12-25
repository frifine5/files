## 写在前面（适用范围）：

> ​		本接口为上传文档查验pdf文档的签名有效性，当且仅支持p7b(rsa,和sm2)和ESEAL(GMT0031和GUOBAN2018)的签名查验。

### 业务接口

> ​	${ip:port}/${project}/pdf/verifyByFile

##### 请求协议	POST

### 业务请求参数

> ​	请求参数格式为表单形式加文件上传流数据

##### 业务请求参数说明

| 参数名    | 类型   | 是否必须 | 参数含义                               |
| --------- | ------ | -------- | -------------------------------------- |
| visitorId | 字符串 | 是       | 访问者标识                             |
| signType  | 整型   | 是       | 签名类型： 0 - p7b； 1 - 电子签章ESEAL |
| algorithm | 字符串 | 是       | 签名算法：rsa和sm2（ecc)               |
| file      | 文件流 | 是       | 上传的待查验文件                       |
| requestId | 字符串 | 否       | 如为空值，则服务端生成                 |



### 业务返回参数

##### 业务返回参数说明



<table>
    <thead>
        <tr>
            <th colspan="2">参数名</th>
            <th>类型</th>
            <th>是否必须</th>  
            <th>参数含义</th>
        </tr >
    </thead>
    <tbody>  
        <tr>
            <td colspan="2">resultCode</td>
            <td>字符串</td>
            <td>是</td>
            <td>返回结果码，0-完成</td>
        </tr>
        <tr>
            <td colspan="2">resultMsg</td>
            <td>字符串</td>
            <td>是</td>
            <td>业务执行信息</td>
        </tr>
        <tr>
            <td colspan="2">requestId</td>
            <td>字符串</td>
            <td>是</td>
            <td>请求编号UUID</td>
        </tr>
        <tr>
            <td colspan="1" rowspan="2" algin="center" valign="middle">data</td>
            <td>timestamp</td>
            <td>字符串</td>
            <td>是</td>
            <td>时间戳</td>
        </tr>
        <tr>
            <td>verifyData</td>
            <td>字符串</td>
            <td>是</td>
            <td>json字符串。根据签名类型和算法的不同，返回的结构不同</td>
        </tr>  
    </tbody>
</table>



 










