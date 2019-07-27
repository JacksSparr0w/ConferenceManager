<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<fmt:message bundle="${textResources}" key="my_conferences" var="my_conferences"/>
<fmt:message bundle="${textResources}" key="profile" var="profile"/>
<fmt:message bundle="${textResources}" key="add_conference" var="add_conference"/>



<hr class="dropdown-divider">
<ul class="nav nav-tabs flex-column">
    <li class="nav-item">
        <a class="nav-link" href="controller?command=user_events">${my_conferences}</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="controller?command=profile">${profile}</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#">${add_conference}</a>
    </li>
</ul>
