<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<c:choose>
    <c:when test="${sessionScope.language != null}">
        <fmt:setLocale value="${sessionScope.language}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="language" var="language"/>

<fmt:message bundle="${language}" key="home" var="home"/>
<fmt:message bundle="${language}" key="conferences" var="conferences"/>
<fmt:message bundle="${language}" key="title" var="title"/>
<fmt:message bundle="${language}" key="sign_in" var="signin"/>
<fmt:message bundle="${language}" key="sign_out" var="signout"/>


<!DOCTYPE html>
<html lang="${sessionScope.language}">
<head>
    <title>Main page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap-grid.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap-reboot.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/nav_bar.css">
    <!--===============================================================================================-->
    <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/animsition/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/bootstrap/js/popper.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/select2/select2.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/daterangepicker/moment.min.js"></script>
    <script src="vendor/daterangepicker/daterangepicker.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/countdowntime/countdowntime.js"></script>
    <!--===============================================================================================-->
    <script src="js/main.js"></script>
    <!--===============================================================================================-->
    <style>
        .fakeimg {
            height: 200px;
            background: #e1e1e2;
        }
    </style>
</head>
<body>
<%@include file="header.jsp" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light p-3">
    <div class="container">
        <a class="navbar-brand" href="controller?command=home_page">${home}</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">${conferences}</a>
                </li>
                <c:if test="${user != null}">
                    <li class="nav-item">
                        <a class="nav-link disabled" href="controller?command=logout">${signout}</a>
                    </li>
                </c:if>
            </ul>
            <li class="nav-link">
                <div class="container p-2">
                    <a href="controller?language=ru&command=change_language">Ru</a>
                    <a>|</a>
                    <a href="controller?language=en&command=change_language">En</a>
                </div>

            </li>
            <ul class="nav-item">
                <c:choose>
                    <c:when test="${user != null}">
                        <a class="nav-link active text-black" href="controller?command=profile">
                                ${user.login}
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link active" href="controller?command=login">
                            <h5>${signin}</h5>
                        </a>
                    </c:otherwise>
                </c:choose>
            </ul>
            <!--
            <form class="form-inline">
                <div class="md-form my-0">
                    <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                </div>
            </form>
            -->
        </div>
    </div>
</nav>

<div class="container" style="margin-top:30px">
    <div class="row">

        <c:if test="${user != null}">
            <div class="col-sm-2">
                <jsp:include page="menu.jsp"/>
            </div>
        </c:if>

        <div class="col">
            <!--//todo create forToken-->
            <c:choose>
                <c:when test="${includeView != null}">
                    <jsp:include page="${includeView}" flush="true"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="eventInfo.jsp" flush="true"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>


