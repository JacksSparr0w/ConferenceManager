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

<script type="text/javascript" src="vendor/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="vendor/daterangepicker/moment.js"></script>
<script type="text/javascript" src="vendor/daterangepicker/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css"/>

<div class="container">
    <div class="row p-2">
        <div class="col-sm-10"><h5>${userInfo.name} ${userInfo.surname}</h5></div>
    </div>
    <div class="row">
        <div class="col-sm-9">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                       aria-controls="home" aria-selected="true">User</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                       aria-controls="profile" aria-selected="false">UserInfo</a>
                </li>
            </ul>

            <div class="tab-content profile-tab" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <form class="form-group" action="controller?command=editUser" method="POST">

                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="login">Login</label>
                            </div>
                            <div class="col-md-6">
                                <input type="login" class="form-control" name="login" id="login"
                                       placeholder="${user.login}" title="Edit your login.">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="login">Password</label>
                            </div>
                            <div class="col-md-6">
                                <input type="password" class="form-control" name="password" id="password"
                                       placeholder="New password" title="Edit your password.">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="login">Repeat password</label>
                            </div>
                            <div class="col-md-6">
                                <input type="password2" class="form-control" name="password2" id="password2"
                                       placeholder="Repeat" title="Repeat your new password.">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-12">
                                <hr>
                                <button class="btn btn-lg btn-success" type="submit"><i
                                        class="glyphicon glyphicon-ok-sign"></i> Save
                                </button>
                                <button class="btn btn-lg" type="reset"><i class="glyphicon glyphicon-repeat"></i> Reset
                                </button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                    <form class="form-group" action="controller?command=editUserInfo" method="POST">
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="name">First name</label>
                            </div>
                            <div class="col-md-6">
                                <input type="name" class="form-control" name="name" id="name"
                                       placeholder="${userInfo.name}" title="Edit your first name.">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="surname">Second name</label>
                            </div>
                            <div class="col-md-6">
                                <input type="surname" class="form-control" name="surname" id="surname"
                                       placeholder="${userInfo.surname}" title="Edit your second name.">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="email">Email</label>
                            </div>
                            <div class="col-md-6">
                                <input type="email" class="form-control" name="email" id="email"
                                       placeholder="${userInfo.email}" title="Edit your email.">
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <div class="col-md-6">
                                <label for="dateOfBirth">Date of your birth</label>
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
                                <label>Date of registration</label>
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
                                <label for="about_me">About me</label>
                                <textarea rows="3" class="form-control rounded" name="about_me" id="about_me"
                                          placeholder="${userInfo.about}" title="Edit text about you."></textarea>
                            </div>
                        </div>
                        <div class="row pt-3 pl-3 pr-3">
                            <hr>
                            <button class="btn btn-lg btn-success" type="submit"><i
                                    class="glyphicon glyphicon-ok-sign"></i> Save
                            </button>
                            <button class="btn btn-lg" type="reset"><i class="glyphicon glyphicon-repeat"></i>
                                Reset
                            </button>
                        </div>
                    </form>
                </div>
            </div><!--/tab-content-->

        </div><!--/col-9-->
        <div class="col-sm-3"><!--right col-->
            <div class="text-center figure-img">
                <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-thumbnail rounded-circle"
                     alt="avatar">
                <h6>Upload your avatar</h6>
                <input type="file" class="text-center center-block file-upload">
            </div>
            <hr class="dropdown-divider">
            <br>

            <ul class="list-group">
                <li class="list-group-item text-muted">Activity <i class="fa fa-dashboard fa-1x"></i></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong>Shares</strong></span> 125</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong>Likes</strong></span> 13</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong>Posts</strong></span> 37</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong>Followers</strong></span> 78</li>
            </ul>

        </div><!--/col-3-->
    </div>
    <!--/row-->
    <!--
        //todo create commands:
                        editUser
                        editUserInfo
        //todo think about photos
    -->
</div>





