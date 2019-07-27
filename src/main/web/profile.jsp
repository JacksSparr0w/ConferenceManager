<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<fmt:message bundle="${textResources}" key="home" var="home"/>
<fmt:message bundle="${textResources}" key="successful_change_profile" var="successful_change_profile"/>
<fmt:message bundle="${textResources}" key="error_find_user_info" var="error_find_user_info"/>
<fmt:message bundle="${textResources}" key="verify_password_is_incorrect" var="verify_password_is_incorrect"/>
<fmt:message bundle="${textResources}" key="account" var="account"/>
<fmt:message bundle="${textResources}" key="user_info" var="user_info"/>

<fmt:message bundle="${textResources}" key="login" var="login"/>
<fmt:message bundle="${textResources}" key="password" var="password"/>
<fmt:message bundle="${textResources}" key="repeat_password" var="repeat_password"/>
<fmt:message bundle="${textResources}" key="reset" var="reset"/>
<fmt:message bundle="${textResources}" key="save" var="save"/>
<fmt:message bundle="${textResources}" key="date_of_birth" var="date_of_bitrh"/>
<fmt:message bundle="${textResources}" key="date_of_registration" var="date_of_registration"/>
<fmt:message bundle="${textResources}" key="about" var="about"/>
<fmt:message bundle="${textResources}" key="edit_avatar" var="edit_avatar"/>
<fmt:message bundle="${textResources}" key="login" var="login"/>
<fmt:message bundle="${textResources}" key="first_name" var="first_name"/>
<fmt:message bundle="${textResources}" key="second_name" var="second_name"/>
<fmt:message bundle="${textResources}" key="email" var="email"/>


<link rel="stylesheet" type="text/css" href="css/avatar.css">

<c:choose>
    <c:when test="${done == true}">
        <div class="container alert alert-success fade show m-t-16" role="alert">
            ${successful_change_profile}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:when>
    <c:when test="${error_find_userInfo == true}">
        <div class="container alert alert-warning fade show m-t-16" role="alert">
            <h2>${error_find_user_info}</h2>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:when>
    <c:when test="${incorrect_verify_password  == true}">
        <div class="container alert alert-warning fade show m-t-16" role="alert">
            ${verify_password_is_incorrect}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:when>
    <c:otherwise>
        <!---->
    </c:otherwise>
</c:choose>

<script type="text/javascript" src="vendor/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="vendor/daterangepicker/moment.js"></script>
<script type="text/javascript" src="vendor/daterangepicker/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css"/>

<div class="container-fluid">
    <div class="row p-2">
        <div class="col-sm-10"><h5>${userInfo.name} ${userInfo.surname}</h5></div>
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
                                       placeholder="${user.login}" title="Edit your login.">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="login">${password}</label>
                            </div>
                            <div class="col-md-6">
                                <input type="password" class="form-control" name="password" id="password"
                                       placeholder="${password}" title="Edit your password.">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="login">${repeat_password}</label>
                            </div>
                            <div class="col-md-6">
                                <input type="password" class="form-control" name="password2" id="password2"
                                       placeholder="${repeat_password}" title="Repeat your new password.">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-12">
                                <hr>
                                <button class="btn btn-lg btn-success" type="submit"><i
                                        class="glyphicon glyphicon-ok-sign"></i>${save}
                                </button>
                                <button class="btn btn-lg" type="reset"><i class="glyphicon glyphicon-repeat"></i>${reset}
                                </button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                    <form class="form-group" action="controller?command=edit_user_info" method="POST" id="editUserInfo">
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="name">${first_name}</label>
                            </div>
                            <div class="col-md-6">
                                <input type="name" class="form-control" name="name" id="name" value="${userInfo.name}"
                                       placeholder="${userInfo.name}" title="Edit your first name.">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="surname">${second_name}</label>
                            </div>
                            <div class="col-md-6">
                                <input type="surname" class="form-control" name="surname" id="surname"
                                       value="${userInfo.surname}"
                                       placeholder="${userInfo.surname}" title="Edit your second name.">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="email">${email}</label>
                            </div>
                            <div class="col-md-6">
                                <input type="email" class="form-control" name="email" id="email"
                                       value="${userInfo.email}"
                                       placeholder="${userInfo.email}" title="Edit your email.">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="dateOfBirth">${date_of_bitrh}</label>
                            </div>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="dateOfBirth" id="dateOfBirth"
                                       placeholder="${userInfo.dateOfBirth}" title="Edit your dateOfBirth.">
                                <script>
                                    $('input[name="dateOfBirth"]').daterangepicker({
                                        "singleDatePicker": true,
                                        "autoApply": true,
                                        "startDate": "07/14/2019",
                                        "endDate": "07/20/2019",
                                        "opens": "center"
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
                                          placeholder="${userInfo.about}" title="Edit text about you."></textarea>
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <hr>
                            <button class="btn btn-lg btn-success" type="submit"><i
                                    class="glyphicon glyphicon-ok-sign"></i>${save}
                            </button>
                            <button class="btn btn-lg" type="reset"><i class="glyphicon glyphicon-repeat"></i>
                                ${reset}
                            </button>
                        </div>
                    </form>
                </div>
            </div><!--/tab-content-->

        </div><!--/col-9-->
        <div class="col-sm-3"><!--right col-->
            <div class="col-md-4 text-center">
                <div id="img-preview-block" class="img-circle avatar avatar-original center-block" style="background-size:cover;
                background-image:url(http://robohash.org/sitsequiquia.png?size=120x120)"></div>
                <br>
                <span class="btn btn-link btn-file">${edit_avatar}<input type="file" id="upload-img" form="editUserInfo" name="avatar"></span>
            </div>
            <script>
                $(function() {
                    $("#upload-img").on("change", function()
                    {
                        var files = !!this.files ? this.files : [];
                        if (!files.length || !window.FileReader) return; // no file selected, or no FileReader support

                        if (/^image/.test( files[0].type)){ // only image file
                            var reader = new FileReader(); // instance of the FileReader
                            reader.readAsDataURL(files[0]); // read the local file

                            reader.onload = function(e){ // set image data as background of div

                                $("#img-preview-block").css({'background-image': 'url('+e.target.result +')', "background-size": "cover"});
                            }
                        }
                    });
                });
            </script>
            <hr class="dropdown-divider">
        </div><!--/col-3-->
    </div>
    <!--/row-->
    <!--
        //todo think about photos
    -->
</div>





