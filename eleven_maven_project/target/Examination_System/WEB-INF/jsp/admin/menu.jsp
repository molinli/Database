<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <%--<li><a href="/admin/showCourse">课题管理<span class="badge pull-right">8</span></a></li>--%>
        <li><a href="/admin/showStudent">学生管理<span class="badge pull-right">21</span></a></li>
        <li><a href="/admin/showTeacher">教师管理<span class="badge pull-right">10</span></a></li>
            <li><a href="/admin/showTopic">课题查看<span class="badge pull-right">12</span></a></li>
            <li><a href="/admin/showSelect">选题结果查看<span class="badge pull-right">8</span></a></li>

            <li><a href="/admin/userPasswordRest">账号密码重置<sapn class="glyphicon glyphicon-repeat pull-right" /></a></li>
        <li><a href="/admin/passwordRest">修改密码<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
            <li><a href="/admin/modifyStatus">修改当前状态<span class="badge pull-right">${sessionScope.currentStatus.status}</span></a></li>

            <li><a href="/logout">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>

            <li><a href="/admin/secondSelect">二轮补选<sapn class="glyphicon glyphicon-pencil pull-right"/></a></li>
        <li class="disabled"><a href="##">Responsive</a></li>
    </ul>
</div>
