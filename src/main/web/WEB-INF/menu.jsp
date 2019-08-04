<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<ul class="nav nav-tabs flex-column">
    <c:forEach var="menuItem" items="${menu}" varStatus="status">
        <li class="nav-item">
            <a class="nav-link" href="${menuItem.path}">
                    <fmt:message bundle="${textResources}" key="${menuItem.name}"/></a>
        </li>
    </c:forEach>
</ul>

