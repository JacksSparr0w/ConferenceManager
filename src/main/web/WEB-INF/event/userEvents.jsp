<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<fmt:message bundle="${textResources}" key="event.leave" var="leave"/>

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

<div class="container-fluid pb-5 pl-5 pr-5">
    <c:forEach var="user" items="${events}" varStatus="status">
        <div class="container">
            <div class="card flex-md-row mb-4 box-shadow h-md-250">
                <div class="card-body d-flex flex-column align-items-start">
                    <strong class="d-inline-block mb-2 text-primary">${user.theme}</strong>
                    <div class="mb-1">
                        <h3 class="text-black">${user.name}</h3>
                    </div>
                    <div class="mb-1 text-muted">
                        <fmt:formatDate value="${user.date}" pattern="yyyy-MM-dd HH:mm"/>
                    </div>
                    <div class="mb-1 text-muted">
                        <a>${user.address}</a>
                    </div>
                    <p class="card-text mb-auto mb-2">${user.shortDescription}</p>
                    <div class="text-muted">
                        <form>
                            <c:url value="controller" var="unregisterToEvent">
                                <c:param name="command" value="leave_event"/>
                                <c:param name="eventId" value="${user.id}"/>
                            </c:url>
                            <button type="button" class="btn btn-outline-dark"
                                    onclick="window.location.href='${unregisterToEvent}'">${leave}</button>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </c:forEach>
</div>
