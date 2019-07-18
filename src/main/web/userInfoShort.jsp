<%--
  Created by IntelliJ IDEA.
  User: jacksparroy
  Date: 18.07.19
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-sm-3">
    <h2>${user.login}</h2>
    <div class="fakeimg">user avantar</div>
    <p>user.about()</p>
    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a class="nav-link active" href="#">My confences</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="controller?command=profile">Edit profile</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Add conference</a>
        </li>
    </ul>
    <hr class="d-sm-none">
</div>
