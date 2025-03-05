### 这是一个基于Spring ai alibaba搭建的ai接入业务的demo

1. 准备
``` text
jdk17
阿里百炼平台的key
maven
```
2. key需要配置在环境变量或者直接替换配置文件
3. 项目运[.idea](.idea)行后访问接口
```html
http://127.0.0.1:8080/chat?input=XXX
```

#### 说明
```text
项目通过本地缓存准备了一些订单数据，你可以通过直接访问ai来操作这些数据，目前提供了两种形式.
1.获取自己有哪些订单，ai会询问你的名称
2.删除订单，通过告知ai需要删除的订单名称来删除

```

## ollama_deepseek项目说明
1. 这是基于本地ollama运行deepseek-r1:8b的例子