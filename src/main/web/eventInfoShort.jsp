<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<fmt:message bundle="${textResources}" key="leave" var="leave"/>

<div class="container-fluid pb-5 pl-5 pr-5">
    <c:forEach var="event" items="${events}" varStatus="status">
        <div class="container pt-5">
            <div class="row  border rounded">
                <div class="col-9">
                    <div class="container-fluid row text-left align-text-bottom pt-4 pl-4">
                        <span class="text-black h3">${event.name}</span>
                    </div>
                    <div class="row text-justify pl-2 pt-4 pr-2 pb-2">
                        <span class="text-secondary">${event.shortDescription}</span>
                    </div>
                </div>
                <div class="col-3">
                    <div class="row align-text-top pt-2">
                        <span class="text-dark h6">${event.date}</span>
                    </div>
                    <div class="row align-text-top pt-2">
                        <span class="text-dark h6">${event.address}</span>
                    </div>
                    <div class="container-fluid row align-content-end justify-content-end pr-1 pb-1">
                        <form>
                            <c:url value="/controller?command=leave_event" var="unregisterToEvent">
                                <c:param name="eventId" value="${event.id}"/>
                            </c:url>
                            <button type="button" class="btn btn-outline-dark"
                                    onclick="window.location.href='${unregisterToEvent}'">${leave}</button>
                        </form>
                    </div>
                </div>
            </div>
            <br>
        </div>
    </c:forEach>
</div>
