<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<fmt:message bundle="${textResources}" key="user.date.birth" var="date_of_bitrh"/>
<fmt:message bundle="${textResources}" key="user.date.registration" var="date_of_registration"/>
<fmt:message bundle="${textResources}" key="user.delete" var="delete"/>
<fmt:message bundle="${textResources}" key="user.delete.sure" var="sure"/>
<fmt:message bundle="${textResources}" key="yes" var="yes"/>
<fmt:message bundle="${textResources}" key="no" var="no"/>



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
                    <strong class="d-inline-block mb-2 text-primary">${user.permission.name}</strong>
                    <div class="d-inline-block">
                        <strong class="d-inline-block mb-2 text-secondary">${user.login}</strong>
                        <c:if test="${user.id == sessionScope.user.userId}">
                            <strong class="rounded d-inline-block mb-2 text-white bg-success pr-2 pl-2">me</strong>
                        </c:if>
                    </div>
                    <div class="mb-1">
                        <h3 class="text-black">${info.name} ${info.surname}</h3>
                    </div>
                    <div class="mb-1 text-muted">
                        <h6 class="card-text mb-auto mb-2"><a href="mailto:${info.email}">${info.email}</a></h6>
                    </div>
                    <div class="mb-1 text-muted">
                        <label class="card-text mb-auto mb-2">${date_of_bitrh}: </label>
                        <fmt:formatDate value="${info.dateOfBirth}" pattern="yyyy-MM-dd"/>
                    </div>
                    <div class="mb-1 text-muted">
                        <label class="card-text mb-auto mb-2">${date_of_registration}: </label>
                        <fmt:formatDate value="${info.dateOfRegistration}" pattern="yyyy-MM-dd"/>
                    </div>
                    <p class="card-text mb-auto mb-2">${info.about}</p>
                    <c:if test="${sessionScope.user.userId != pageScope.user.id && sessionScope.permission.checkRule('DELETE_USER') == true}">
                        <div class="text-muted d-inline-flex">
                            <form>
                                <c:url value="controller" var="deleteUrl">
                                    <c:param name="command" value="delete_user"/>
                                    <c:param name="userId" value="${user.id}"/>
                                </c:url>
                                <button type="button" class="btn btn-outline-dark"
                                        onclick="window.location.href='${deleteUrl}'"
                                >${delete}</button>
                            </form>

                        </div>
                    </c:if>
                </div>
                <c:if test="${sessionScope.user.userId != pageScope.user.id && sessionScope.permission.checkRule('CHANGE_USER_PERMISSION') == true}">
                    <div class="col-4">
                        <div class="container mt-2 mr-1">
                            <form action="controller?command=change_user_permission" method="post">
                                <input type="hidden" value="${user.id}" name="userId">
                                <label class="text-dark" for="selectPermission"><h6>Change permission to</h6></label>
                                <select class="form-control" id="selectPermission" name="permissionId" onchange="this.form.submit()">
                                    <c:forEach var="_permission" items="${permissions}">
                                        <c:choose>
                                            <c:when test="${user.permission.id == _permission.id}">
                                                <option value="${_permission.id}" selected>${_permission.name}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${_permission.id}">${_permission.name}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </form>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </c:forEach>
</div>
