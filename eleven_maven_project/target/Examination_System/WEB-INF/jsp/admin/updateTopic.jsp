<%--
  Created by IntelliJ IDEA.
  User: 莫林立
  Date: 2018/1/8
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="top.jsp"></jsp:include>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">修改课题信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <!-- 这个表单要慎重！！每一个输入信息就对应一个topic表元素 -->
                    <form class="form-horizontal" role="form" action="/admin/updateTopic" id="editfrom" method="post">
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">课题号</label>
                            <div class="col-sm-10">
                                <input  readonly="readonly" type="number" class="form-control" id="inputEmail3" name="topicId" placeholder="请输入课题号" value="${topicInfo.topicId}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">课题名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputPassword3" name="topicTitle" placeholder="请输入课题名称" value="${topicInfo.topicTitle}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label" name="grade">老师编号</label>
                            <div class="col-sm-10">
                                <%--<select class="form-control" name="teacherid">--%>
                                <%--<c:forEach items="${teacherList}" var="item">--%>
                                <%--<option value="${item.userid}">${item.username}</option>--%>
                                <%--</c:forEach>--%>
                                <%--</select>--%>
                                <input  readonly="readonly" type="text" name="teaId" class="form-control" id="inputPassword3" value="${teachaerId}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">课题类型</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="topicType" placeholder="请输入课题类型" value="${topicInfo.topicType}">
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                        <%--<label for="inputPassword3" class="col-sm-2 control-label">课题题目</label>--%>
                        <%--<div class="col-sm-10">--%>
                        <%--<input type="text" class="form-control" name="topicTitle" placeholder="课题题目">--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">课题要求</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="topicRequest" placeholder="请输入课题要求" value="${topicInfo.topicRequest}">
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                        <%--<label for="inputPassword3" class="col-sm-2 control-label" name="coursetype">课程的类型：</label>--%>
                        <%--<div class="col-sm-10">--%>
                        <%--<select class="form-control" name="coursetype">--%>
                        <%--<option value="必修课">必修课</option>--%>
                        <%--<option value="选修课">选修课</option>--%>
                        <%--<option value="公共课">公共课</option>--%>
                        <%--</select>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label" name="topicMajor">所属院系</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="topicMajor">
                                    <c:forEach items="${collegeList}" var="item">
                                        <option value="${item.collegeid}">${item.collegename}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">课题简介</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="topicContent" placeholder="请输入课题简介" value="${topicInfo.topicContent}">
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                            <%--<button class="btn btn-default" type="reset">重置</button>--%>
                        </div>
                    </form>
                </div>

            </div>

        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(1)").addClass("active")
</script>
</html>


