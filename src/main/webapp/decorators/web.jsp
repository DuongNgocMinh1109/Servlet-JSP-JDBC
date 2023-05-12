<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><dec:title default="Duong Ngoc Minh" /></title>

    <!-- css -->
    <link href="<c:url value='/templates/web/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" type="text/css" media="all"/>
    <link href="<c:url value='/templates/web/css/style.css' />" rel="stylesheet" type="text/css" media="all"/>
    <!-- css -->

</head>
<body>
    <!-- header -->
    <%@ include file="/commons/web/header.jsp" %>
    <!-- header -->

    <div class="container">
        <dec:body/>
    </div>

    <!-- footer -->
    <%@ include file="/commons/web/footer.jsp" %>
    <!-- footer -->

    <!-- jquery -->
    <script type="text/javascript" src="<c:url value='/templates/web/jquery/jquery.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/templates/web/bootstrap/js/bootstrap.bundle.min.js' />"></script>
    <!-- jquery -->

</body>
</html>