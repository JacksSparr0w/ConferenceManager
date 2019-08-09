<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<fmt:message bundle="${textResources}" key="new_conferences" var="new_conferences"/>
<fmt:message bundle="${textResources}" key="event.register.success" var="successful_register_to_event"/>
<fmt:message bundle="${textResources}" key="event.join" var="join"/>
<fmt:message bundle="${textResources}" key="places_left" var="places_left"/>
<fmt:message bundle="${textResources}" key="join_now" var="join_now"/>

<div class="container">

    <c:if test="${requestScope.error_dont_find_event eq true}">
        <div class="container alert alert-danger fade show m-t-16" role="alert">
            Don't find event!
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>

    <c:if test="${requestScope.register_done eq true}">
        <div class="container alert alert-success fade show m-t-16" role="alert">
                ${successful_register_to_event}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>
    <c:if test="${requestScope.no_free_places eq true}">
        <div class="container alert alert-warning fade show m-t-16" role="alert">
            No free places left!
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>

    <c:if test="${requestScope.delete_success eq true}">
        <div class="container alert alert-success fade show m-t-16" role="alert">
            Successful delete!
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>
    <c:if test="${requestScope.error_removing_event eq true}">
        <div class="container alert alert-warning fade show m-t-16" role="alert">
            Error removing event!
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>
</div>

<c:forEach var="event" items="${events}">
    <div class="container-fluid pt-3 pl-3 pr-3">
        <h3 class="text-black">${event.name}</h3>
        <h6 class="text-right"><fmt:formatDate value="${event.date}" pattern="yyyy-MM-dd HH:mm"/></h6>
        <div class="container">
            <img src="eventImages/${event.pictureLink}" class="rounded" lass="rounded col-md-8" style="
        width: auto;
        min-width: 15rem;"
                 alt="Event picture">
        </div>
        <p class="text-dark text-right">${event.address}</p>

        <span class="text-black text-justify pt-2 pl-2 pr-2">${event.description}</span>
        <div class="w-75 pt-2 pl-2 pr-2">
            <div class="progress">
                <c:set value="${event.capacity - filling.get(event.id)}" var="numberOfFreePlaces"/>
                <div class="progress-bar bg-success align-content-center text-white"
                     style="height:25px; width:${(numberOfFreePlaces / event.capacity)*100}%">
                    <h6>${places_left}
                            ${numberOfFreePlaces}/${event.capacity}</h6>
                </div>
            </div>
        </div>
        <c:if test="${user != null}">
            <p class="text-black">${join_now}</p>
            <div class="container pt-2 pl-2 pr-2 d-inline-flex">
                <form>
                    <c:url value="/controller?command=register_to_event" var="registerToEvent">
                        <c:param name="eventId" value="${event.id}"/>
                        <c:param name="freePlaces" value="${numberOfFreePlaces}"/>
                    </c:url>
                    <input type="button" class="btn btn-outline-success" value="${join}"
                           onclick="window.location.href='${registerToEvent}'"/>
                </form>
                <c:if test="${user.permission == 'ADMINISTRATOR' or user.userId == event.author_id}">
                    <form method="post">
                        <c:url value="controller?command=edit_event_page" var="editEvent">
                            <c:param name="eventId" value="${event.id}"/>
                        </c:url>
                        <input type="button" class="btn btn-outline-warning ml-3" value="Edit"
                               onclick="window.location.href='${editEvent}'"/>
                    </form>
                    <form method="post">
                        <c:url value="controller?command=remove_event" var="deleteEvent">
                            <c:param name="eventId" value="${event.id}"/>
                        </c:url>
                        <input type="button" class="btn btn-outline-danger ml-3" value="Remove"
                               onclick="window.location.href='${deleteEvent}'"/>
                        <!--
                    <input type="button" class="btn btn-outline-danger ml-3" value="Remove"
                               data-toggle="modal" data-target="#exampleModal"/>

                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Warning!</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        You are sure to delete event?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                        </button>
                                        <button type="button" class="btn btn-primary"
                                                onclick="window.location.href='${deleteEvent}'">Yes
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    -->
                    </form>
                </c:if>
            </div>
        </c:if>
        <hr>
        <br>
    </div>
</c:forEach>


<ul class="pagination justify-content-center">
    <c:forEach var="i" begin="${1}" end="${countOfPages}">
        <c:choose>
            <c:when test="${i == page}">
                <li class="page-item active"><a class="page-link" href="#!">${i}</a></li>
            </c:when>
            <c:otherwise>
                <c:url value="controller?command=all_events" var="homeUrl">
                    <c:param name="page" value="${i}"/>
                </c:url>
                <li class="page-item"><a class="page-link" href="${homeUrl}">${i}</a></li>

            </c:otherwise>
        </c:choose>
    </c:forEach>
</ul>



