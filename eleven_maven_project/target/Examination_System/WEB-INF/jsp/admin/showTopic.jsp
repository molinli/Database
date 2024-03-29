<%@ page import="com.system.po.TopicCustom" %><%--
  Created by IntelliJ IDEA.
  User: 莫林立
  Date: 2017/12/23
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="com.system.po.TopicCustom" %>
<!DOCTYPE html>
<html>
<head>
    <title>毕业设计课题信息显示</title>

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
                        <h1 class="col-md-5">毕业设计课题信息管理</h1>
                        <!-- 添加对课程名字的模糊检索 -->
                        <form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="/admin/selectToipc" id="form1" method="post">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="请输入课题名" name="findByName">

                                <span class="input-group-addon btn" onclick="document.getElementById('form1').submit" id="sub">搜索</span>

                            </div>
                        </form>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>课题号</th>
                        <th>教师号</th>
                        <th>课题类型</th>
                        <th>课题名称</th>
                        <th>所属系别</th>
                        <th>课题要求</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach  items="${topicList}" var="item">
                        <tr>
                            <td>${item.topicId}</td>
                            <td>${item.teaId}</td>
                            <td>${item.topicType}</td>
                            <td>${item.topicTitle}</td>
                            <td>${item.topicMajor}</td>
                            <td>${item.topicRequest}</td>
                                <td>
                                <button class="btn btn-default btn-xs btn-info" onClick="location.href='/admin/updateTopic?id=${item.topicId}'">修改</button>
                                <button class="btn btn-default btn-xs btn-danger btn-primary" onClick="location.href='/admin/deleteTopic?id=${item.topicId}'">删除</button>
                                <!--弹出框-->
                                </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="/admin/showTopic?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li><a href="/admin/showTopic?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li><a href="/admin/showTopic?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                    <li><a href="/admin/showTopic?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
                                    <li><a href="/admin/showTopic?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a></li>
                                </c:if>
                                <li><a href="/admin/showTopic?page=${pagingVO.totalCount}">最后一页&raquo;</a></li>
                            </ul>
                        </nav>
                    </c:if>
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
    <%--设置菜单中--%>
    $("#nav li:nth-child(1)").addClass("active")
    <c:if test="${pagingVO != null}">
    if (${pagingVO.curentPageNo} == ${pagingVO.totalCount}) {
        $(".pagination li:last-child").addClass("disabled")
    };

    if (${pagingVO.curentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled")
    };
    </c:if>

    function confirmd() {
        var msg = "您真的确定要删除吗？！";
        if (confirm(msg)==true){
            return true;
        }else{
            return false;
        }
    }

    $("#sub").click(function () {
        $("#form1").submit();
    });
</script>
</html>
