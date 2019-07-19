<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jacksparroy
  Date: 18.07.19
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<p class="text-right">Last added conferences</p>
<c:forEach var="event" items="${events}" varStatus="status">
    <div class="container">
        <h2>${event.name}</h2>
        <!--//todo don't user get/set-->
        <h5>${event.getDate()}</h5>
        <div class="fakeimg">conference image(optional)</div>
        <p>${event.getAddress()}</p>
        <p>${event.getDescription()}</p>
        <p>Free places now :${event.getCapacity()}</p>
        <br>
    </div>
</c:forEach>

