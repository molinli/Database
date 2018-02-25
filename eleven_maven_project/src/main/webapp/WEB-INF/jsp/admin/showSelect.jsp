<%--
  Created by IntelliJ IDEA.
  User: 莫林立
  Date: 2018/1/3
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="com.system.po.TopicCustom" %>
<!DOCTYPE html>
<html>
<head>
    <title>毕业设计课题信息管理</title>

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
                        <h1 class="col-md-5">学生选题信息显示</h1>

                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>课题号</th>
                        <th>学生姓名</th>
                        <th>课题类型</th>
                        <th>课题名称</th>
                        <th>所属系别</th>
                        <th>教师姓名</th>
                        <th>成绩</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${topicStuNotNullList}" var="item">
                        <tr>
                            <td>${item.topicId}</td>
                            <td>${item.stuName}</td>
                            <td>${item.topicType}</td>
                            <td>${item.topicTitle}</td>
                            <td>${item.topicMajor}</td>
                            <td>${item.teaName}</td>
                                <c:choose>
                                    <c:when test="${item.score!=null}">
                                        <td>${item.score}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>未打分</td>
                                    </c:otherwise>
                                </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="/admin/showSelect?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/showSelect?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/showSelect?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/showSelect?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/showSelect?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a>
                                    </li>
                                </c:if>
                                <li><a href="/admin/showSelect?page=${pagingVO.totalCount}">最后一页&raquo;</a></li>
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
    }
    ;

    if (${pagingVO.curentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled")
    }
    ;
    </c:if>

    function confirmd() {
        var msg = "您真的确定要删除吗？！";
        if (confirm(msg) == true) {
            return true;
        } else {
            return false;
        }
    }

    $("#sub").click(function () {
        $("#form1").submit();
    });
</script>
</html>
