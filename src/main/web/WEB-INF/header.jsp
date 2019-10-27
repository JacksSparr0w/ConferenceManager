<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:message bundle="${textResources}" key="header.slide.1.title" var="title1"/>
<fmt:message bundle="${textResources}" key="header.slide.2.title" var="title2"/>
<fmt:message bundle="${textResources}" key="header.slide.3.title" var="title3"/>
<fmt:message bundle="${textResources}" key="header.sign.up" var="signup"/>
<fmt:message bundle="${textResources}" key="menu.add_conference" var="add"/>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class=""></li>
        <li data-target="#myCarousel" data-slide-to="1" class=""></li>
        <li data-target="#myCarousel" data-slide-to="2" class="active"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item">
            <div class="first-slide" style="background-image: url('eventImages/header/conf1.jpg');
            background-size: cover; height: 300px"></div>

            <div class="container">
                <div class="carousel-caption text-left">
                    <h1>${title1}</h1>
                    <c:choose>
                        <c:when test="${user != null}">
                            <p><a class="m-2 btn btn-lg btn-primary" href="add_event_page" role="button">
                                ${add}</a></p>
                        </c:when>
                        <c:otherwise>
                            <p><a class="m-2 btn btn-lg btn-primary" href="login_page" role="button">
                                ${signup}</a></p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <div class="carousel-item">
            <div class="second-slide" style="background-image: url('eventImages/header/conf2.jpg');
            background-size: cover; height: 300px"></div>
            <div class="container">
                <div class="carousel-caption">
                    <h1>${title2}</h1>
                </div>
            </div>
        </div>
        <div class="carousel-item active">
            <div class="third-slide" style="background-image: url('eventImages/header/conf3.jpg');
            background-size: cover; height: 300px"></div>
            <div class="container">
                <div class="carousel-caption text-right">
                    <h1>${title3}</h1>
                </div>
            </div>
        </div>
    </div>
    <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
