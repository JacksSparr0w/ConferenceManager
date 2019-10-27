<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<c:choose>
    <c:when test="${sessionScope.language != null}">
        <fmt:setLocale value="${sessionScope.language}" variant="en"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="textResources" var="textResources" scope="session"/>

<fmt:message bundle="${textResources}" key="home" var="home"/>
<fmt:message bundle="${textResources}" key="sign.in" var="signin"/>
<fmt:message bundle="${textResources}" key="sign.out" var="signout"/>
<fmt:message bundle="${textResources}" key="conferences" var="conferences"/>


<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>${conferences}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="vendor/daterangepicker/moment.min.js"></script>
    <script src="js/smoothscroll.js"></script>
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <script src="vendor/animsition/js/animsition.min.js"></script>
    <script src="vendor/select2/select2.min.js"></script>
    <script src="vendor/countdowntime/countdowntime.js"></script>
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/tooplate-style.css">

    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">

</head>
<body>
<%@include file="header.jsp" %>

<nav class="navbar navbar-expand-sm navbar-light bg-light">
    <div class="container">
        <c:choose>
            <c:when test="${user != null}">
                <a class="navbar-brand" href="profile">${user.login}</a>

            </c:when>
            <c:otherwise>
                <a class="navbar-brand" href="login_page">${signin}</a>
            </c:otherwise>
        </c:choose>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            <span class="navbar-toggler-icon"></span>
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav container-fluid">
                <li class="nav-item">
                    <a href="home_page" class="nav-link"><span
                            data-hover="${home}">${home}</span></a>
                </li>
                <li class="nav-item">
                    <a href="all_events" class="nav-link"><span
                            data-hover="${conferences}">${conferences}</span></a>
                </li>
                <c:if test="${user != null}">
                    <li class="nav-item">
                        <a href="logout" class="nav-link"><span
                                data-hover="${signout}">${signout}</span></a>
                    </li>
                </c:if>
                <li class="nav-item disabled" style="padding: 0 20px;">
                    <a href="controller?language=ru&command=change_language"><span>Ru</span></a>
                    <span>|</span>
                    <a href="controller?language=en&command=change_language"><span>En</span></a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container rounded mb-5" style="margin-top:30px;">
    <div class="row">

        <c:if test="${user != null}">
            <div class="menu col-sm-1 col-md-2">
                <jsp:include page="menu.jsp"/>
            </div>
        </c:if>

        <div class="col">
            <c:choose>
                <c:when test="${includeView != null}">
                    <jsp:include page="${includeView}"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="homePage.jsp"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>


