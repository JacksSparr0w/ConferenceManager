<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<fmt:message bundle="${textResources}" key="error.405.title" var="title"/>
<fmt:message bundle="${textResources}" key="back_to_homepage" var="back"/>
<!DOCTYPE html>
<html lang="${sessionScope.language}">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>405</title>

    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">

    <link type="text/css" rel="stylesheet" href="css/error.css"/>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>

<div id="notfound">
    <div class="notfound">
        <div class="notfound-404">
            <h1>4<span></span>5</h1>
        </div>
        <h2>${title}</h2>
        <a href="home_page">${back}</a>
    </div>
</div>

</body>

</html>

