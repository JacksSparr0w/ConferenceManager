<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:message bundle="${textResources}" key="header.title" var="title"/>
<fmt:message bundle="${textResources}" key="header.subtitle" var="subtitle"/>

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
                    <h1>Create your own conference!</h1>
                    <p></p>
                    <c:choose>
                        <c:when test="${user != null}">
                            <p><a class="m-2 btn btn-lg btn-primary" href="controller?command=add_event_page" role="button">Add
                                conference</a></p>
                        </c:when>
                        <c:otherwise>
                            <p><a class="m-2 btn btn-lg btn-primary" href="controller?command=login" role="button">Sign up
                                to start</a></p>
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
                    <h1>Or sign up for existing ones</h1>
                    <p class="text-white">Some text Some text Some text Some text Some text Some text Some text Some text</p>
                </div>
            </div>
        </div>
        <div class="carousel-item active">
            <div class="third-slide" style="background-image: url('eventImages/header/conf3.jpg');
            background-size: cover; height: 300px"></div>
            <div class="container">
                <div class="carousel-caption text-right">
                    <h1>Group with other people</h1>
                    <p class="text-white">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida
                        at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
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
