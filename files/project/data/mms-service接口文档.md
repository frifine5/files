# mms-service 密码机服务接口文档
该文档用于mms-service接口使用说明

统一返回示例

    {
  
      "code": 0,                  状态码      0： 成功   -1： 失败
      "count": null,              数量
      "data": "AAAAAJghsJrF0L9nW+XkqvGXfW+bwUsYK2BREbIc7Jkcw1C/scuVSuKQgbELI5+UFj1cFWfsXIuTdstjs38BkEHXVWmumkvo08260V6VkmZrGdYOfg/gpBI8w/Ao7GTLcP7PKYng/ff7KdckpsPom1kDIzF0wOQWgq47eVqJHHCku4hh",       返回数据  code 为0有数据 -1 无数据
      "msg": null                 说明信息  code 为0无数据 -1 有数据
    }





## 1、密码机内部签名

接口路径：/sm9/internal/sign

请求方式：HTTP POST

请求头域：Content-Type application/json

请求参数(JSON格式)：

|名称|类型|是否必须|描述|
|----|----|----|----|
|index|int|是|密钥索引|
|data|String|是|明文数据 base64编码|



- 返回数据(JSON格式)：

|ID|名称|类型|描述|
|----|----|----|----|----|
| code | 是否成功标识 |String| 0： 成功  -1： 失败 |
| count | 数据条数 |int| 数据条数 |
| data | 返回数据 |String|返回签名值 base64编码 |
| msg | 错误信息 |String| 返回错误信息 |

- 请求示例：

````
{
    "index":2,
    "data":"cmFkaXNo"
}

````

- 返回示例：

```
{
  "code": 0,
  "count": null,
  "data": "iGxM+hYpMJhNKpmSbnBLu79+peEFdBU65556utiEfSR9NlHRoScXGWD66LZMmtWdmJW0NkME+RXTwm1U0ip+fyqR6fQSGHCcCL95oa2IPValawbJS0iMA3WP1G4lP6xF",
  "msg": null
}

````


## 2、密码机内部验签

接口路径：/sm9/internal/verifySign

请求方式：HTTP POST

请求头域：Content-Type application/json

请求参数(JSON格式)：

|名称|类型|是否必须|描述|
|----|----|----|----|
|index|int|是|密钥索引|
|plainData|String|是|明文数据 base64编码|
|signData|String|是|签名数据 base64编码|




- 返回数据(JSON格式)：

|ID|名称|类型|描述|
|----|----|----|----|----|
| code | 是否成功标识 |String| 0： 成功  -1： 失败 |
| count | 数据条数 |int| 数据条数 |
| data | 返回数据 |String|验签结果  true false |
| msg | 错误信息 |String| 返回错误信息 |

- 请求示例：

```
{
    "index":2,
    "plainData":"cmFkaXNo",
    "signData":"LmzLzVshWTqZLac9Xw9A+axN6e4VT7m61bz7t7Rj6scAkrL78SJT/NZQwcgu5R1abjoGlzl6fAdOXYgX6ji3lkJaSzOl/EMTfvT0J/4dYyQNGCAuV2XaTWoArQ3EdYtV"
}

```

- 返回示例：

```
{
  "code": 0,
  "count": null,
  "data": "true",
  "msg": null
}

````


## 3、密码机内部加密

接口路径：/sm9/internal/encrypt

请求方式：HTTP POST

请求头域：Content-Type application/json

请求参数(JSON格式)：

|名称|类型|是否必须|描述|
|----|----|----|----|
|index|int|是|密钥索引|
|data|String|是|明文数据 base64编码|



- 返回数据(JSON格式)：

|ID|名称|类型|描述|
|----|----|----|----|----|
| code | 是否成功标识 |String| 0： 成功  -1： 失败 |
| count | 数据条数 |int| 数据条数 |
| data | 返回数据 |String|密文数据  base64编码 |
| msg | 错误信息 |String| 返回错误信息 |

- 请求示例：

```
{
    "index":2,
    "data":"cmFkaXNo",
}

```

- 返回示例：

```
{
  "code": 0,
  "count": null,
  "data": "ACOxR/wFBVtbs9muIl4mV1xmN7dE4qnl5xLqUF7qN/qrLCMHPAQxZoi05vnSrok57cpJ97SctwFTRAizFHJMqjtyOzpytHc9nd0DNWi9JPws6k9VYzSgxNZ48vq9LbpcmwYAAACjsoPo6H0=",
  "msg": null
}

````

## 4、密码机内部解密

接口路径：/sm9/internal/decrypt

请求方式：HTTP POST

请求头域：Content-Type application/json

请求参数(JSON格式)：

|名称|类型|是否必须|描述|
|----|----|----|----|
|index|int|是|密钥索引|
|data|String|是|密文数据 base64编码|



- 返回数据(JSON格式)：

|ID|名称|类型|描述|
|----|----|----|----|----|
| code | 是否成功标识 |String| 0： 成功  -1： 失败 |
| count | 数据条数 |int| 数据条数 |
| data | 返回数据 |String|明文数据  base64编码 |
| msg | 错误信息 |String| 返回错误信息 |

- 请求示例：

```
{
    "index":2,
    "data":"ACOxR/wFBVtbs9muIl4mV1xmN7dE4qnl5xLqUF7qN/qrLCMHPAQxZoi05vnSrok57cpJ97SctwFTRAizFHJMqjtyOzpytHc9nd0DNWi9JPws6k9VYzSgxNZ48vq9LbpcmwYAAACjsoPo6H0=",
}

```

- 返回示例：

```
{
  "code": 0,
  "count": null,
  "data": "cmFkaXNo",
  "msg": null
}

````




## 5、密码机外送公钥验签

接口路径：/sm9/external/verifySign

请求方式：HTTP POST

请求头域：Content-Type application/json

请求参数(JSON格式)：

|名称|类型|是否必须|描述|
|----|----|----|----|
|plainData|string|是|明文数据 base64编码|
|signData|String|是|签名值 base64编码|
|userId|String|是|用户标识 base64编码|
|publicKey|String|是|外送公钥 base64编码|



- 返回数据(JSON格式)：

|ID|名称|类型|描述|
|----|----|----|----|----|
| code | 是否成功标识 |String| 0： 成功  -1： 失败 |
| count | 数据条数 |int| 数据条数 |
| data | 返回数据 |String|验签是否正确 |
| msg | 错误信息 |String| 返回错误信息 |

- 请求示例：

```
{
    "userId":"Z29tYWluX2liY19yZWdz",
    "plainData":"cmFkaXNo",
    "signData":"HxF6bqPoZxuy6Uq804tsbjY5/L2cX8InD7emD5qLjdt/rUX9RdsegmGrXo5s5i3L2vZ0psvZSjlYFdR5cnXA9C6oVoc/H9678RKxlzyevV2DZ9+43OGSN8HszXelM8dJ",
    "publicKey":"AAAAAJghsJrF0L9nW+XkqvGXfW+bwUsYK2BREbIc7Jkcw1C/scuVSuKQgbELI5+UFj1cFWfsXIuTdstjs38BkEHXVWmumkvo08260V6VkmZrGdYOfg/gpBI8w/Ao7GTLcP7PKYng/ff7KdckpsPom1kDIzF0wOQWgq47eVqJHHCku4hh"
}

```

- 返回示例：

```
{
  "code": 0,
  "count": null,
  "data": "true",
  "msg": null
}

````


## 6、密码机外送公钥加密

接口路径：/sm9/external/encrypt

请求方式：HTTP POST

请求头域：Content-Type application/json

请求参数(JSON格式)：

|名称|类型|是否必须|描述|
|----|----|----|----|
|userId|string|是|用户标识 base64编码|
|data|String|是|明文数据 base64编码|
|publicKey|String|是|外送公钥 base64编码|



- 返回数据(JSON格式)：

|ID|名称|类型|描述|
|----|----|----|----|----|
| code | 是否成功标识 |String| 0： 成功  -1： 失败 |
| count | 数据条数 |int| 数据条数 |
| data | 返回数据 |String|密文数据  base64编码 |
| msg | 错误信息 |String| 返回错误信息 |

- 请求示例：

```
{
    "data":"cmFkaXNo",
    "userId":"Z29tYWluX2liY19yZWdz",
    "publicKey":"AAAAAKQdLVYDcD2Cr0C5h9x0IHtpSsO2pdfNXWwwvAxf79ECmm+wXG4csXfxjlACyEUXuysDefybg3ootjPpBzErpPA="
}

```

- 返回示例：

```
{
  "code": 0,
  "count": null,
  "data": "AJPgWUQRCcPRh+NTfdW4DghPXIbqt5Ret0n/5UlYSlCoOqE9Mok8/WfqpW6oeW6YrXqC3faiUVdO22yewIsrYhYsvFZIyOc42OKO4zO3N3Dbz9rj242t4j/3ofDbm8sumgYAAACOXlXvc1w=",
  "msg": null
}

````


## 7、导出签名主公钥

接口路径：/sm9/export/mastSignKey

请求方式：HTTP POST

请求头域：Content-Type application/json

请求参数(JSON格式)：

|名称|类型|是否必须|描述|
|----|----|----|----|
|index|int|是|密钥索引|




- 返回数据(JSON格式)：

|ID|名称|类型|描述|
|----|----|----|----|----|
| code | 是否成功标识 |String| 0： 成功  -1： 失败 |
| count | 数据条数 |int| 数据条数 |
| data | 返回数据 |String|签名主公钥  base64编码 |
| msg | 错误信息 |String| 返回错误信息 |

- 请求示例：

```
{
    "index":2
}

```

- 返回示例：

```
{
  "code": 0,
  "count": null,
  "data": "AAAAAJghsJrF0L9nW+XkqvGXfW+bwUsYK2BREbIc7Jkcw1C/scuVSuKQgbELI5+UFj1cFWfsXIuTdstjs38BkEHXVWmumkvo08260V6VkmZrGdYOfg/gpBI8w/Ao7GTLcP7PKYng/ff7KdckpsPom1kDIzF0wOQWgq47eVqJHHCku4hh",
  "msg": null
}

````

## 8、导出加密主公钥

接口路径：/sm9/export/mastEncKey

请求方式：HTTP POST

请求头域：Content-Type application/json

请求参数(JSON格式)：

|名称|类型|是否必须|描述|
|----|----|----|----|
|index|int|是|密钥索引|




- 返回数据(JSON格式)：

|ID|名称|类型|描述|
|----|----|----|----|----|
| code | 是否成功标识 |String| 0： 成功  -1： 失败 |
| count | 数据条数 |int| 数据条数 |
| data | 返回数据 |String|加密主公钥  base64编码 |
| msg | 错误信息 |String| 返回错误信息 |

- 请求示例：

```
{
    "index":2
}

```

- 返回示例：

```
{
  "code": 0,
  "count": null,
  "data": "AAAAAJghsJrF0L9nW+XkqvGXfW+bwUsYK2BREbIc7Jkcw1C/scuVSuKQgbELI5+UFj1cFWfsXIuTdstjs38BkEHXVWmumkvo08260V6VkmZrGdYOfg/gpBI8w/Ao7GTLcP7PKYng/ff7KdckpsPom1kDIzF0wOQWgq47eVqJHHCku4hh",
  "msg": null
}

````



## 8、导出用户标识

接口路径：/sm9/export/userId

请求方式：HTTP POST

请求头域：Content-Type application/json

请求参数(JSON格式)：

|名称|类型|是否必须|描述|
|----|----|----|----|
|index|int|是|密钥索引|




- 返回数据(JSON格式)：

|ID|名称|类型|描述|
|----|----|----|----|----|
| code | 是否成功标识 |String| 0： 成功  -1： 失败 |
| count | 数据条数 |int| 数据条数 |
| data | 返回数据 |String|用户标识  base64编码 |
| msg | 错误信息 |String| 返回错误信息 |

- 请求示例：

```
{
    "index":2
}

```

- 返回示例：

```
{
  "code": 0,
  "count": null,
  "data": "Z29tYWluX2liY19yZWdz",
  "msg": null
}

````


## 9、获取随机数

接口路径：/sm9/random

请求方式：HTTP POST

请求头域：Content-Type application/json

请求参数(JSON格式)：

|名称|类型|是否必须|描述|
|----|----|----|----|
|length|int|是|随机数长度|




- 返回数据(JSON格式)：

|ID|名称|类型|描述|
|----|----|----|----|----|
| code | 是否成功标识 |String| 0： 成功  -1： 失败 |
| count | 数据条数 |int| 数据条数 |
| data | 返回数据 |String|指定长度随机数  base64编码 |
| msg | 错误信息 |String| 返回错误信息 |

- 请求示例：

```
{
    "length":10
}

```

- 返回示例：

```
{
  "code": 0,
  "count": null,
  "data": "cKTrUKvkSTfx4A==",
  "msg": null
}

````


## 10、获取摘要

接口路径：/sm9/digest

请求方式：HTTP POST

请求头域：Content-Type application/json

请求参数(JSON格式)：

|名称|类型|是否必须|描述|
|----|----|----|----|
|data|string|是|待摘要数据 base64编码|




- 返回数据(JSON格式)：

|ID|名称|类型|描述|
|----|----|----|----|----|
| code | 是否成功标识 |String| 0： 成功  -1： 失败 |
| count | 数据条数 |int| 数据条数 |
| data | 返回数据 |String|摘要  base64编码 |
| msg | 错误信息 |String| 返回错误信息 |

- 请求示例：

```
{
    "data":"cmFkaXNo"
}

```

- 返回示例：

```
{
  "code": 0,
  "count": null,
  "data": "WGkDLmPjFImrsQit1o2FFrUTlalt/MDPZMipYxFsFRc=",
  "msg": null
}

````




----------------------------

<dependency>
	<groupId>gomain</groupId>
	<artifactId>platform</artifactId>
	<version>1.0.1</version>
</dependency>





