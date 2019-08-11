<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<fmt:message bundle="${textResources}" key="home.hey" var="hey"/>
<fmt:message bundle="${textResources}" key="home.can.1" var="can1"/>
<fmt:message bundle="${textResources}" key="home.can.2" var="can2"/>
<fmt:message bundle="${textResources}" key="home.can.3" var="can3"/>
<fmt:message bundle="${textResources}" key="home.btn.1" var="view"/>
<fmt:message bundle="${textResources}" key="home.btn.2" var="create"/>
<fmt:message bundle="${textResources}" key="home.btn.3" var="signin"/>
<fmt:message bundle="${textResources}" key="welcome" var="welcome"/>

<section class="about full-screen d-lg-flex justify-content-center align-items-center" id="about">
    <div class="container">
        <div class="row">

            <div class="col-lg-7 col-md-12 col-12 d-flex align-items-center">
                <div class="about-text">
                    <a href="home_page">
                        <small class="small-text">${welcome} <span class="mobile-block">Conferences.com site!</span>
                        </small>
                    </a>
                    <h3 class="animated animated-text">
                        <span class="mr-2">${hey}</span>
                        <div class="animated-info" style="height: 4rem;">
                            <span class="animated-item">${can1}</span>
                            <span class="animated-item">${can2}</span>
                            <span class="animated-item">${can3}</span>
                        </div>
                    </h3>
                    <p></p>
                    <div class="custom-btn-group mt-4">
                        <a href="all_events" class="btn mr-lg-2 custom-btn">${view}</a>
                        <c:if test="${user != null}">
                            <a href="add_event_page" class="btn custom-btn custom-btn-bg custom-btn-link">${create}</a>
                        </c:if>
                        <c:if test="${user == null}">
                            <a href="login_page"
                               class="btn custom-btn custom-btn-bg custom-btn-link">${signin}</a>
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="col-lg-5 col-md-12 col-12">
                <div class="about-image svg">
                    <img src="images/startImage.jpg" style="height: 300px; width: 300px"
                         class="img-fluid rounded-circle" alt="">
                </div>
            </div>

        </div>
    </div>
</section>