<center><h2>喜迎20大网页制作</h2></center>

## 1. 简介

喜迎20大网页制作主要目的用于让我归纳近期学习的知识，其中spring ioc源码的手写加深记忆，对于web项目的制作结构进行深入理解

*servlet优化 

*使用反射进行组件间的组装 

*引入DispatcherServlet中央控制器 

*IOC进行控制反转

*Filter进行网页过滤 对网页编码进行规范，以及打开网页前进行事务的开启

*事务管理的思想（trans中进行 数据库的初始化默认不提交 数据库的手动提交 数据库提交失败回滚 将多个事务绑定在一起，防止数据的单个提交造成数据库数据混乱）

*Listener监听器获取web.xml文件中application.xml地址传入IOC进行组件的组装

*thymeleaf的深入了解使用

*request作用域 session作用域 Application作用域的 理解并应用

### 1.1 基本信息

开发工具：IDEA

JDK版本：8

MySQL版本：8

Tomcat版本：8

项目编码：UTF-8

### 1.2 使用技术

*JavaEE：后台服务的实现

*Html,Css,Js：网页实现

*bootstrap,fontawesome,ajax.googleapis.com：网页组件，矢量图，字体的引用

*lib中 druid数据库连接池，dbutils工具类，mysql-connector驱动

*thymeleaf_lib中 thymeleaf的jar包

### 1.3 项目概览

*basedao：数据库连接，基本功能实现类

*filters：拦截过滤器

*ioc：IOC控制反转安装组件

*listeners：网页监听器

*myspringmvc：中央控制器

*trans：事务管理

*util：工具类

*web20：项目服务的实现

## 2 项目功能

#### 2.1 首页

