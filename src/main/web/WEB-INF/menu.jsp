<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<div class="list-group">
    <c:forEach var="menuItem" items="${menu}" varStatus="status">
        <a href="${menuItem.path}" class="list-group-item list-group-item-action">
            <fmt:message bundle="${textResources}" key="${menuItem.name}"/>
        </a>
    </c:forEach>
</div>

