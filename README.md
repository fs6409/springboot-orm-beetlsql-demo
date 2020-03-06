#### springboot with beetlsql orm demo

> 使用beetlsql快捷操作数据库，提供用户表的增、删、改、查服务能力
>
> 集成`Swagger UI`文档，以及使用`poi`的`SXSSFWorkbook`导出用户信息

整体参照[官网文档](http://ibeetl.com/guide/#/beetlsql/)，引入依赖

```
<dependency>
    <groupId>com.ibeetl</groupId>
    <artifactId>beetl-framework-starter</artifactId>
    <version>1.2.14.RELEASE</version>
</dependency>
```

再配置`application.properties`中的MySql数据库连接和包扫描路径

项目启动后直接访问[文档页](http://localhost:8080/swagger-ui.html)即可。

