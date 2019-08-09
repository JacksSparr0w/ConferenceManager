<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="about full-screen d-lg-flex justify-content-center align-items-center" id="about">
    <div class="container">
        <div class="row">

            <div class="col-lg-7 col-md-12 col-12 d-flex align-items-center">
                <div class="about-text">
                    <small class="small-text">Welcome to <span class="mobile-block">Conferences.com site!</span></small>
                    <h3 class="animated animated-text">
                        <span class="mr-2">Hey, there you can</span>
                        <div class="animated-info">
                            <span class="animated-item">Join to conference</span>
                            <span class="animated-item">Add your own conference</span>
                            <span class="animated-item">And something else, hah</span>
                        </div>
                    </h3>
                    <p>Some text some text some text</p>
                    <div class="custom-btn-group mt-4">
                        <a href="controller?command=all_events" class="btn mr-lg-2 custom-btn">View conferences</a>
                        <c:if test="${user != null}">
                            <a href="controller?command=add_event_page" class="btn custom-btn custom-btn-bg custom-btn-link">Create
                                new</a>
                        </c:if>
                        <c:if test="${user == null}">
                            <a href="controller?command=login_page"
                               class="btn custom-btn custom-btn-bg custom-btn-link">Sing-in, firstly</a>
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