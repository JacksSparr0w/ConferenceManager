<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jacksparroy
  Date: 18.07.19
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${register_done eq true}">
    <div class="container alert alert-success fade show m-t-16" role="alert">
        Successful register to event!
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>
<p class="text-right">New conferences</p>
<hr>
<c:forEach var="event" items="${events}" varStatus="status">
    <div class="container-fluid pt-3 pl-3 pr-3">
        <h3 class="text-black">${event.name}</h3>
        <h6 class="text-right">${event.date}</h6>
        <div class="fakeimg rounded thumb text-center">conference image(optional)</div>
        <p class="text-dark text-right">${event.address}</p>

        <span class="text-black text-justify pt-2 pl-2 pr-2">${event.description}</span>
        <div class="w-75 pt-2 pl-2 pr-2">
            <div class="progress" style="height:15px">
                <div class="progress-bar bg-success" style="width:${(45 / event.capacity)*100}%">Places left:
                    45/${event.capacity}</div>
            </div>
        </div>
        <div class="container pt-2 pl-2 pr-2">
            <form action="/controller?command=register_to_event" method="get" id="join">
                <p class="text-black">There's a free places! Join to us now!</p>
                <input type="button" class="btn btn-outline-success" value="join"/>
            </form>
            <form>
                <c:url value="/controller?command=register_to_event" var="registerToEvent">
                    <c:param name="eventId" value="${event.id}"/>
                </c:url>
                <input type="button" value="Put Your Text Here"
                       onclick="window.location.href='${registerToEvent}'"/>
            </form>
        </div>
        <hr>
        <br>
    </div>
</c:forEach>

