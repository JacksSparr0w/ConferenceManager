<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<fmt:message bundle="${textResources}" key="event.leave" var="leave"/>

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
                            <c:url value="leave_event" var="unregisterToEvent">
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
