<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:message bundle="${language}" key="title" var="title"/>
<fmt:message bundle="${language}" key="subtitle" var="subtitle"/>

<div class="jumbotron text-center" style="margin-bottom:0">
    <h2 class="text-primary">${title}</h2>
    <h5 class="text-secondary">${subtitle}</h5>
</div>
