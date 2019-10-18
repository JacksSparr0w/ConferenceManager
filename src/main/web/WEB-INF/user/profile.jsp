<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<fmt:message bundle="${textResources}" key="home" var="home"/>
<fmt:message bundle="${textResources}" key="profile.change.success" var="successful_change_profile"/>
<fmt:message bundle="${textResources}" key="user.find.error" var="error_find_user_info"/>
<fmt:message bundle="${textResources}" key="verify_password_is_incorrect" var="verify_password_is_incorrect"/>
<fmt:message bundle="${textResources}" key="tab.account" var="account"/>
<fmt:message bundle="${textResources}" key="tab.user.info" var="user_info"/>

<fmt:message bundle="${textResources}" key="user.login" var="login"/>
<fmt:message bundle="${textResources}" key="user.password" var="password"/>
<fmt:message bundle="${textResources}" key="user.password.repeat" var="repeat_password"/>
<fmt:message bundle="${textResources}" key="profile.reset" var="reset"/>
<fmt:message bundle="${textResources}" key="profile.save" var="save"/>
<fmt:message bundle="${textResources}" key="user.date.birth" var="date_of_bitrh"/>
<fmt:message bundle="${textResources}" key="user.date.registration" var="date_of_registration"/>
<fmt:message bundle="${textResources}" key="user.about" var="about"/>
<fmt:message bundle="${textResources}" key="edit.avatar" var="edit_avatar"/>
<fmt:message bundle="${textResources}" key="user.login" var="login"/>
<fmt:message bundle="${textResources}" key="name.first" var="first_name"/>
<fmt:message bundle="${textResources}" key="name.second" var="second_name"/>
<fmt:message bundle="${textResources}" key="user.email" var="email"/>
<fmt:message bundle="${textResources}" key="user.photo.fail" var="error_photo"/>
<fmt:message bundle="${textResources}" key="user.photo.success" var="success_photo"/>


<link rel="stylesheet" type="text/css" href="css/avatar.css">
<script src="js/image.js"></script>
<script src="js/main.js"></script>


<c:choose>
    <c:when test="${done != null}">
        <div class="container alert alert-success fade show m-t-16" role="alert">
            <fmt:message bundle="${textResources}" key="${done}"/>
            <button type="button" class="close" data-dismiss="alert" aria-label="Click to remove"
                    onclick="<c:remove var="done" scope="session"/>">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:when>
    <c:when test="${error != null}">
        <div class="container alert alert-warning fade show m-t-16" role="alert">
            <fmt:message bundle="${textResources}" key="${error}"/>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"
                    onclick="<c:remove var="error" scope="session"/>">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:when>
</c:choose>

<script type="text/javascript" src="vendor/daterangepicker/moment.js"></script>
<script type="text/javascript" src="vendor/daterangepicker/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css"/>

<div class="container-fluid">
    <div class="row p-2">
        <strong class="d-inline-block mb-2 text-primary"><h5>${userInfo.name} ${userInfo.surname}</h5></strong>
        <!--
        <div class="col-sm-10"><h5>${userInfo.name} ${userInfo.surname}</h5></div>
        -->
    </div>
    <div class="row">
        <div class="col-sm-9">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                       aria-controls="home" aria-selected="true">${account}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                       aria-controls="profile" aria-selected="false">${user_info}</a>
                </li>
            </ul>

            <div class="tab-content profile-tab" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <form class="form-group" action="controller?command=edit_user" method="POST">

                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="login">${login}</label>
                            </div>
                            <div class="col-md-6">
                                <input type="login" class="form-control" name="login" id="login" value="${user.login}"
                                       onchange="checkLogin(this)"
                                       title="Edit your login.">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="login">${password}</label>
                            </div>
                            <div class="col-md-6">
                                <input type="password" class="form-control" name="password" id="password"
                                       placeholder="${password}" title="Edit your password."
                                onchange="checkPassword(this)">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="login">${repeat_password}</label>
                            </div>
                            <div class="col-md-6">
                                <input type="password" class="form-control" name="password2" id="password2"
                                       placeholder="${repeat_password}" title="Repeat your new password."
                                onchange="checkPassword(this)">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-12">
                                <hr>
                                <button class="btn btn-lg btn-success" type="submit" name="submit"><i
                                        class="glyphicon glyphicon-ok-sign"></i>${save}
                                </button>
                                <button class="btn btn-lg btn-outline-secondary ml-3" type="reset"><i
                                        class="glyphicon glyphicon-repeat"></i>${reset}
                                </button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                    <form class="form-group" action="edit_user_info" method="POST">
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="name">${first_name}</label>
                            </div>
                            <div class="col-md-6">
                                <input type="name" class="form-control" name="name" id="name" value="${userInfo.name}"
                                       title="Edit your first name."
                                onchange="checkName(this)">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="surname">${second_name}</label>
                            </div>
                            <div class="col-md-6">
                                <input type="surname" class="form-control" name="surname" id="surname"
                                       value="${userInfo.surname}"
                                       title="Edit your second name."
                                onchange="checkName(this)">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="email">${email}</label>
                            </div>
                            <div class="col-md-6">
                                <input type="email" class="form-control" name="email" id="email"
                                       value="${userInfo.email}"
                                       title="Edit your email."
                                onchange="checkEmail(this)">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="dateOfBirth">${date_of_bitrh}</label>
                            </div>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="dateOfBirth" id="dateOfBirth"
                                       value="${userInfo.dateOfBirth}" title="Edit your dateOfBirth."
                                onchange="checkDateOfBirth(this)">
                                <script>
                                    $('input[name="dateOfBirth"]').daterangepicker({
                                        "singleDatePicker": true,
                                        "locale": {
                                            "format": "YYYY-MM-DD",
                                            "separator": " - ",
                                            "applyLabel": "Apply",
                                            "cancelLabel": "Cancel",
                                            "fromLabel": "From",
                                            "toLabel": "To",
                                            "customRangeLabel": "Custom",
                                            "weekLabel": "W",
                                            "daysOfWeek": [
                                                "Su",
                                                "Mo",
                                                "Tu",
                                                "We",
                                                "Th",
                                                "Fr",
                                                "Sa"
                                            ],
                                            "monthNames": [
                                                "January",
                                                "February",
                                                "March",
                                                "April",
                                                "May",
                                                "June",
                                                "July",
                                                "August",
                                                "September",
                                                "October",
                                                "November",
                                                "December"
                                            ],
                                            "firstDay": 1
                                        },
                                        "linkedCalendars": false,
                                        "showCustomRangeLabel": false
                                    }, function (start, end, label) {
                                        console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
                                    });
                                </script>
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label>${date_of_registration}</label>
                            </div>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="dateOfRegistration"
                                       id="dateOfRegistration"
                                       readonly
                                       placeholder="${userInfo.dateOfRegistration}"
                                       title="Edit your dateOfRegistration.">
                            </div>
                        </div>

                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-12">
                                <label for="about">${about}</label>
                                <textarea rows="3" class="form-control rounded" name="about" id="about"
                                          title="Edit text about you.">${userInfo.about}</textarea>
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <hr>
                            <button class="btn btn-lg btn-success" type="submit" name="submit"><i
                                    class="glyphicon glyphicon-ok-sign"></i>${save}
                            </button>
                            <button class="btn btn-lg btn-outline-secondary ml-3" type="reset"><i
                                    class="glyphicon glyphicon-repeat"></i>
                                ${reset}
                            </button>
                        </div>
                    </form>
                </div>
            </div><!--/tab-content-->

        </div><!--/col-9-->
        <div class="col-sm-3"><!--right col-->
            <div class="text-center">
                <form class="form-group" action="controller?command=edit_user_photo" method="POST"
                      enctype="multipart/form-data">

                    <div id="img-preview-block" class="avatar avatar-original center-block rounded rounded-circle"
                         style="background-size:cover;
                                 background-image:url(userImages/${userInfo.pictureLink})"></div>
                    <span class="btn btn-link btn-file">${edit_avatar}<input type="file" id="upload-img"
                                                                             name="userPhoto"
                                                                             onchange="form.submit()"></span>
                </form>
            </div>
        </div><!--/col-3-->
    </div>
</div>





