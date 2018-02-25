<%--
  Created by IntelliJ IDEA.
  User: 莫林立
  Date: 2018/1/9
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <h1 style="text-align: center;">设置当前选题阶段</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form name="reset" class="form-horizontal" role="form" action="/admin/modifiedStatus" id="editfrom" method="post" onsubmit="return check()">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <input  readonly="readonly" type="hidden" class="form-control" name="courseid" id="inputEmail3" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">当前阶段</label>
                            <div class="col-sm-10">
                                <input  readonly="readonly" type="text" class="form-control" name="content" id="inputEmail3" value="${status.content}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">当前状态值</label>
                            <div class="col-sm-10">
                                <input   readonly="readonly" type="number" name="test" class="form-control" id="inputPassword3" value="${status.status}" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">设置状态值</label>
                            <div class="col-sm-10">
                                <input   type="number" name="status" class="form-control" id="inputPassword3" value="${status.status}" >
                            </div>
                        </div>

                        <%--<div class="form-group">--%>
                            <%--<label for="inputPassword3" class="col-sm-2 control-label" name="status">需要设置的状态</label>--%>
                            <%--<div class="col-sm-10">--%>
                                <%--<select class="form-control" name="status">--%>
                                    <%--<c:forEach items="${statusList}" var="item">--%>
                                        <%--<option value="${item.status}">${item.content}</option>--%>
                                    <%--</c:forEach>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <%--<div class="form-group">--%>
                            <%--<label for="inputPassword3" class="col-sm-2 control-label">留言</label>--%>
                            <%--<div class="col-sm-10">--%>
                                <%--<input type="number" name="mark" class="form-control" id="inputPassword3" placeholder="请输入成绩">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                            <%--<button class="btn btn-default">重置</button>--%>
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
<script>
    $("#nav li:nth-child(1)").addClass("active")
    function check() {
        if(reset.mark.value==""||reset.mark.value==null)
        {alert("请输入留言");return false;}
    }
</script>
</html>

