<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<c:choose>
    <c:when test="${done != null}">
        <div class="container alert alert-success fade show m-t-16" role="alert">
            <fmt:message bundle="${textResources}" key="${done}"/>
            <button type="button" class="close" data-dismiss="alert" aria-label="Click to remove"
                    onclick="<c:remove var="done" scope="session"/>">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:when>
    <c:when test="${error != null}">
        <div class="container alert alert-warning fade show m-t-16" role="alert">
            <fmt:message bundle="${textResources}" key="${error}"/>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"
                    onclick="<c:remove var="error" scope="session"/>">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:when>
</c:choose>

<div class="container-fluid pb-1 pl-2 pr-1">
    <c:forEach var="user" items="${users}">
        <c:set var="info" value="${users_info.get(user.id)}"/>
        <div class="container">
            <div class="card flex-md-row mb-4 box-shadow h-md-250">
                <div class="card-body d-flex flex-column align-items-start">
                    <div class="d-inline-block">
                        <strong class="d-inline-block mb-2 text-secondary">${user.login}</strong>
                        <c:if test="${user.id == sessionScope.user.userId}">
                            <strong class="rounded d-inline-block mb-2 text-white bg-success pr-2 pl-2">me</strong>
                        </c:if>
                    </div>
                    <div class="mb-1">
                        <h3 class="text-black">${info.name} ${info.surname}</h3>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
