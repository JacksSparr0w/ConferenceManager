<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jacksparroy
  Date: 19.07.19
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${error_find_userInfo eq true}">
    <h2>Error to find your info!</h2>
</c:if>
<div class="row p-b-20 p-t-10">
    <div class="col-sm-8">
        <div class="row">
            <span class="col-sm-3 text-dark">Login</span>
            <h4 class="col text-black">${user.login}</h4>
        </div>
        <div class="row">
            <span class="col-sm-3 text-dark">Name</span>
            <h4 class="col text-black">${userInfo.name}</h4>
        </div>
        <div class="row">
            <span class="col-sm-3 text-dark">Surname</span>
            <h4 class="col text-black">${userInfo.surname}</h4>
        </div>
    </div>
    <div class="col-sm-4 fakeimg">User avatar</div>
</div>




