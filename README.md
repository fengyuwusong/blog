个人博客系统
===========================

### 环境依赖
- jdk v1.8.0_65+
- mysql 5.6+
- 墨刀
- idea

### 技术选型
- 后端
    - springBoot 
    - mybatis
    - pagehelper 分页插件
    - druid 数据库连接池
    - swagger2 接口文档辅助
    - lombok 基于注解完成get/set
- 前端
    - Bootstrap
    - JQuery
    - Vue
    - editormd
- 其他
    - 畅言评论

### 项目路由
- 前台
    - https://www.fengyuwusong.cn
- 后台
    - https://www.fengyuwusong.cn/html/admin.html 用户名密码 admin

### swagger接口文档
![swagger接口文档](http://www.fengyuwusong.cn/images/swagger-ui.png)


### 目录结构描述

├── src                      
│   ├── main
|           ├── java        
|               ├── cn.niriqiang.blog
|                   ├── annotation       //注解类
|                   ├── aspect           //拦截器
|                   ├── controller       
|                   ├── domain           //model
|                   ├── dto
|                   ├── enums            //返回结果枚举类
|                   ├── exception        //异常类
|                   ├── handle           //统一异常返回类
|                   ├── service
|                   ├── util             //工具包
|                   ├── BlogApplication   //启动类
|                   ├── Swagger2          // swagger2配置
|           ├── resources
|               ├── mapper               //mybatis mapper
|               ├── static               //静态文件
|               ├── application.yml      //springboot 配置文件
│   ├── test                // 单元测试代码
├── Valet.mp           //墨刀设计图
├── LICENSE                        
└── Readme.md                       

