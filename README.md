# 简介
数据库课程设计，设计北林大学毕业设计选题系统，服务对象为老师、学生和后台管理人员。<br/>
## How to use:
<img src="https://github.com/molinli/Database/blob/master/gif/%E6%BC%94%E7%A4%BA%E5%8A%A8%E7%94%BB.gif" width="500">

# 使用技术
IOC容器：Spring

Web框架：SpringMVC

ORM框架：Mybatis

安全框架：Shiro

数据源：C3P0

日志：log4j

前端框架：Bootstrap
# SpringMVC 托管
<img src="https://github.com/molinli/Database/blob/master/picture/springManager.png" width="400">

## 项目运行硬件&软件

### 1、运行环境和所需工具
* 编译器：IntelliJ IDEA
* 项目构建工具：Maven
* 数据库：Mysql
* JDK版本：jdk1.8
* Tomcat版本：Tomcat8.05

### 2、
![image](https://github.com/molinli/Database/blob/master/picture/begin.png)
* 登录账户
  * 管理员账户：admin
  * 老师账户：1001
  * 学生账户：16

# 功能模块介绍
### 1、登录模块功能
使用Shiro权限管理框架，实现登录验证和登录信息的储存，根据不同的登录账户，分发权限角色，对不同页面url进行角色设置
### 2、管理员模块功能
管理员可对 教师信息、学生信息、课程信息 进行 增删改查 操作，管理员账户，可以重置非管理员账户的密码
* 课程管理：当课程已经有学生选课成功时，将不能删除
* 学生管理：添加学生信息时，其信息也会添加到登录表中
* 教师管理：同上
* 账户密码重置：
* 修改密码：
![image](https://github.com/molinli/Database/blob/master/picture/adminEnter.png)
![image](https://github.com/molinli/Database/blob/master/picture/adminShowResult.png)
![image](http://imgsrc.baidu.com/forum/pic/item/004a5ef082025aafccfdca60f1edab64024f1a23.jpg)
### 3、教师模块功能
教师登陆后，可以获取其，教授的课程列表，并可以给已经选择该课程的同学打分，无法对已经给完分的同学进行二次操作
* 我的课程
* 修改密码
![image](https://github.com/molinli/Database/blob/master/picture/teacherShowClass.png)

### 4、学生模块功能
学生登录后，根据学生信息，获取其已经选择的课程，和已经修完的课程
* 所有课程: 在这里选修课程，选好后，将会自动跳转到已选课程选项
* 已选课程: 这里显示的是，还没修完的课程，也就是老师还没给成绩，由于还没有给成绩，所以这里可以进行退课操作
* 已修课程: 显示已经修完，老师已经给成绩的课程
* 修改密码:
![image](https://github.com/molinli/Database/blob/master/picture/studentSelectClass.png)
