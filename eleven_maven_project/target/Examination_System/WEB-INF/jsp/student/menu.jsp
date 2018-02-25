<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="/student/showTopic">选题<span class="badge pull-right">5</span></a></li>
        <li><a href="/student/selectedTopic">选题记录<span class="badge pull-right">5</span></a></li>
        <c:choose>
            <c:when test="${ sessionScope.currentStatus.status!=0}">
                <li><a href="/student/overTopic">课题结果<span class="badge pull-right">5</span></a></li>
            </c:when>
            <c:otherwise>
                <li><a href="##">课题结果<span class="badge pull-right">5</span></a></li>
            </c:otherwise>
        </c:choose>
        <li><a href="/student/passwordRest">修改密码<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
        <li><a href="/logout">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>
        <li class="disabled"><a href="##">Responsive</a></li>
    </ul>
</div>