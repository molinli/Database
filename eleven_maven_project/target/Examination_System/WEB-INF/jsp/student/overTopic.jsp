<%--
  Created by IntelliJ IDEA.
  User: 莫林立
  Date: 2018/1/5
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>课题信息显示</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

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
                        <h1 class="col-md-5">在修课题</h1>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>课题名称</th>
                        <th>老师</th>
                        <th>课题类型</th>
                        <th>所属院系</th>
                        <th>成绩</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${selectedTopicList!=null}">
                    <c:forEach  items="${selectedTopicList}" var="item">
                        <%--输出已修完的课程--%>
                        <%--<c:if test="${item.}">--%>
                            <tr>
                                <td>${item.topicTitle}</td>
                                <td>${item.teacherName}</td>
                                <td>${item.topicType}</td>
                                <td>${item.topicMajor}</td>
                                <!-- 展示成绩 -->
                                <c:choose>
                                    <c:when test="${score!=null}">
                                        <td>${score}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>未打分</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                    </c:forEach>
                    </c:if>
                    </tbody>
                </table>

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
    $("#nav li:nth-child(3)").addClass("active")
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