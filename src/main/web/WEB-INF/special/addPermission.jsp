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


<div class="container-fluid pb-5 pl-2 pr-2">
    <div class="row">
        <div class="col-8 m-2">
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
            <strong class="d-inline-block mb-2 text-black">Add new permission</strong>
            <form action="add_permission" method="post">
                <div>
                    <label for="name">Name of new permission</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="New permission">
                </div>
                <div class="container m-2">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="modify_any_event" id="modifyEvent">
                        <label class="form-check-label" for="modifyEvent">
                            modifyEvent
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="change_user_permission"
                               id="changePermission">
                        <label class="form-check-label" for="changePermission">
                            changePermission
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="all_users" id="allUsers">
                        <label class="form-check-label" for="allUsers">
                            allUsers
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="delete_user" id="deleteUser">
                        <label class="form-check-label" for="deleteUser">
                            deleteUser
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="add_theme" id="addTheme">
                        <label class="form-check-label" for="addTheme">
                            addTheme
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="add_role" id="addRole">
                        <label class="form-check-label" for="addRole">
                            addRole
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="add_permission" id="addPermission">
                        <label class="form-check-label" for="addPermission">
                            addPermission
                        </label>
                    </div>
                    <div class="justify-content-end">
                        <button type="submit" class="btn btn-outline-success mt-2"
                        >Add
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="m-2">
            <strong class="d-inline-block mb-2 text-black">Existing permissions</strong>
            <c:forEach items="${existPermissions}" var="_permission">
                <div class="mb-3 border border-secondary rounded p-2">
                    <p class="p-1 h5">${_permission.name}</p>
                    <hr>
                    <c:set var="rules" value="${_permission.rules}"/>
                    <c:forEach items="${rules}" var="rule">
                        <p>${rule.value}</p>
                    </c:forEach>
                </div>
            </c:forEach>
        </div>
    </div>
</div>