<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<fmt:message bundle="${textResources}" key="exist" var="existing"/>
<fmt:message bundle="${textResources}" key="new.permission" var="new.permission"/>
<fmt:message bundle="${textResources}" key="add" var="add"/>
<fmt:message bundle="${textResources}" key="add_permission" var="add_permission"/>
<fmt:message bundle="${textResources}" key="name" var="name"/>
<fmt:message bundle="${textResources}" key="modify_any_event" var="modify"/>
<fmt:message bundle="${textResources}" key="change_user_permission" var="change"/>
<fmt:message bundle="${textResources}" key="all_users" var="all_users"/>
<fmt:message bundle="${textResources}" key="delete_user" var="delete_user"/>
<fmt:message bundle="${textResources}" key="add_theme" var="add_theme"/>
<fmt:message bundle="${textResources}" key="add_role" var="add_role"/>
<fmt:message bundle="${textResources}" key="add_permission" var="add_permission"/>

<script src="js/main.js"></script>

<div class="container-fluid pb-5 pl-2 pr-2">
    <div class="row">
        <div class="col-7 m-2">
            <div class="container">
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

            </div>
            <strong class="d-inline-block mb-2 text-black">${add_permission}</strong>
            <form action="add_permission" method="post">
                <div>
                    <label for="name">${name}</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="New permission" onchange="checkNotNull(this)">
                </div>
                <div class="container m-2">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="modify_any_event" id="modifyEvent">
                        <label class="form-check-label" for="modifyEvent">
                            ${modify}
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="change_user_permission"
                               id="changePermission">
                        <label class="form-check-label" for="changePermission">
                            ${change}
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="all_users" id="allUsers">
                        <label class="form-check-label" for="allUsers">
                            ${all_users}
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="delete_user" id="deleteUser">
                        <label class="form-check-label" for="deleteUser">
                            ${delete_user}
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="add_theme" id="addTheme">
                        <label class="form-check-label" for="addTheme">
                            ${add_theme}
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="add_role" id="addRole">
                        <label class="form-check-label" for="addRole">
                            ${add_role}
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="add_permission" id="addPermission">
                        <label class="form-check-label" for="addPermission">
                            ${add_permission}
                        </label>
                    </div>
                    <div class="justify-content-end">
                        <button type="submit" class="btn btn-outline-success mt-2" name="submit"
                        >${add}
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="m-2">
            <strong class="d-inline-block mb-2 text-black">${existing}</strong>
            <c:forEach items="${existPermissions}" var="_permission">
                <div class="mb-3 rounded p-2" style="background-color: #f1f1f1">
                    <p class="p-1 h5">${_permission.name}</p>
                    <hr>
                    <c:set var="rules" value="${_permission.rules}"/>
                    <c:forEach items="${rules}" var="rule">
                        <p><fmt:message bundle="${textResources}" key="${rule.value}"/></p>
                    </c:forEach>
                </div>
            </c:forEach>
        </div>
    </div>
</div>