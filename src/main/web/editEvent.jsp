<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<fmt:message bundle="${textResources}" key="event_name" var="name"/>
<fmt:message bundle="${textResources}" key="description" var="description"/>
<fmt:message bundle="${textResources}" key="theme" var="theme"/>
<fmt:message bundle="${textResources}" key="date" var="date"/>
<fmt:message bundle="${textResources}" key="address" var="address"/>
<fmt:message bundle="${textResources}" key="capacity" var="capacity"/>
<fmt:message bundle="${textResources}" key="reset" var="reset"/>
<fmt:message bundle="${textResources}" key="save" var="save"/>
<fmt:message bundle="${textResources}" key="edit_image" var="edit_image"/>


<link rel="stylesheet" type="text/css" href="css/image.css">
<script src="js/image.js"></script>

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
    <div class="row p-5">
        <div class="text-center">
            <form class="form-group" action="#" method="POST"
                  enctype="multipart/form-data">

                <div id="img-preview-block" class="avatar avatar-original center-block rounded rounded-circle"
                     style="background-size:cover;
                             background-image:url(eventImages/${event.pictureLink})"></div>
                <span class="btn btn-link btn-file">${edit_image}<input type="file" id="upload-img"
                                                                        name="eventPhoto"
                                                                        onchange="form.submit()"></span>
            </form>
        </div>
    </div>
    <div class="row">
        <form class="form-group" action="#" method="POST"
              enctype="multipart/form-data">
            <div class="row pt-3 pl-3 pr-3">
                <!--name-->
                <div class="col-md-6">
                    <label for="name">${name}</label>
                </div>
                <div class="col-md-6">
                    <input type="text" class="form-control" name="name" id="name"
                           value="${name}" title="Enter name of conference">
                </div>
            </div>

            <div class="row pt-3 pl-3 pr-3">
                <div class="col-md-12">
                    <label for="description">${description}</label>
                    <textarea rows="4" class="form-control rounded" name="description" id="description"
                              placeholder="${description}" title="Enter description of the event"></textarea>
                </div>
            </div>
            <div class="row pt-3 pl-3 pr-3">
                <div class="form-group">
                    <label for="selectTheme">${theme}</label>
                    <select class="form-control" id="selectTheme" name="theme">
                        <option>Business</option>
                        <option>Advertising</option>
                        <option>Science</option>
                        <option>Design</option>
                    </select>
                </div>
            </div>
            <div class="row pt-3 pl-3 pr-3">
                <div class="col-md-6">
                    <label for="date">${date}</label>
                </div>
                <div class="col-md-6">
                    <input type="text" class="form-control" name="date" id="date" value="event.date"
                           title="Edit date of conference.">
                    <script>
                        $('input[name="date"]').daterangepicker({
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
                <!--address-->
                <div class="col-md-6">
                    <label for="address">${address}</label>
                </div>
                <div class="col-md-6">
                    <input type="text" class="form-control" name="address" id="address"
                           value="${event.address}" title="Enter address">
                </div>
            </div>
            <div class="row pt-3 pl-3 pr-3">
                <!--capacity-->
                <div class="col-md-6">
                    <label for="capacity">${capacity}</label>
                </div>
                <div class="col-md-6">
                    <input type="number" class="form-control" name="capacity" id="capacity"
                           value="${event.capacity}" title="Enter capacity">
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
</div>





