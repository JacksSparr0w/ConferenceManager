<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<fmt:message bundle="${textResources}" key="event.name" var="name"/>
<fmt:message bundle="${textResources}" key="event.description" var="description"/>
<fmt:message bundle="${textResources}" key="event.theme" var="theme"/>
<fmt:message bundle="${textResources}" key="event.date" var="date"/>
<fmt:message bundle="${textResources}" key="event.country" var="country"/>
<fmt:message bundle="${textResources}" key="event.city" var="city"/>
<fmt:message bundle="${textResources}" key="event.street" var="street"/>
<fmt:message bundle="${textResources}" key="event.building" var="building"/>
<fmt:message bundle="${textResources}" key="event.capacity" var="capacity"/>
<fmt:message bundle="${textResources}" key="profile.reset" var="reset"/>
<fmt:message bundle="${textResources}" key="profile.save" var="save"/>
<fmt:message bundle="${textResources}" key="event.image.edit" var="edit_image"/>

<link rel="stylesheet" type="text/css" href="css/image.css">
<script src="js/image.js"></script>
<script type="text/javascript" src="vendor/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="vendor/daterangepicker/moment.js"></script>
<script type="text/javascript" src="vendor/daterangepicker/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css"/>

<c:choose>
    <c:when test="${done == true}">
        <div class="container alert alert-success fade show m-t-16" role="alert">
                ${successful_change_profile}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:when>
    <c:when test="${error_add_event == true}">
        <div class="container alert alert-warning fade show m-t-16" role="alert">
                ${error_find_user_info}
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

<div class="container-fluid">
    <div class="col-md-8 col-lg-6 justify-content-center p-3">
        <div class="text-center">
            <div id="img-preview-block" class="avatar avatar-original center-block rounded rounded-circle"
                 style="background-image:url(eventImages/${event.pictureLink});
                         background-repeat: no-repeat;
                         background-size: cover;"></div>
            <span class="btn btn-link btn-file">${edit_image}<input type="file" id="upload-img"
                                                                    name="picture" form="addForm"></span>
        </div>
    </div>
    <div class="row">
        <form class="form-group" action="controller?command=add_event" method="POST" id="addForm"
              enctype="multipart/form-data">
            <div class="row mb-3">
                <!--name-->
                <label for="name">${name}</label>
                <input type="text" class="form-control" name="name" id="name"
                       placeholder="${name}" title="Enter name of conference">
            </div>

            <div class="row mb-3">
                <label for="description">${description}</label>
                <textarea rows="4" class="form-control rounded" name="description" id="description"
                          placeholder="${description}" title="Enter description of the event"></textarea>
            </div>

            <div class="row mb-3">
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

            <div class="row mb-3">
                <label for="date">${date}</label>
                <input type="text" class="form-control" name="date" id="date"
                       title="Edit date of conference.">
                <script>
                    $('input[name="date"]').daterangepicker({
                        "singleDatePicker": true,
                        "timePicker": true,
                        "timePicker24Hour": true,
                        "locale": {
                            "format": "YYYY-MM-DD HH:mm",
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
                        }
                    }, function (start, end, label) {
                        console.log('New date range selected: ' + start.format('YYYY-MM-DD HH:mm') + ' to ' + end.format('YYYY-MM-DD HH:mm') + ' (predefined range: ' + label + ')');
                    });
                </script>
            </div>

            <div class="row">
                <div class="col-md-3 mb-3">
                    <label for="country">${country}</label>
                    <input type="text" class="form-control" name="country" id="country"
                           placeholder="${country}" title="Enter country">
                </div>
                <div class="col-md-3 mb-3">

                    <label for="city">${city}</label>
                    <input type="text" class="form-control" name="city" id="city"
                           placeholder="${city}" title="Enter city">
                </div>
                <div class="col-md-3 mb-3">
                    <label for="street">${street}</label>
                    <input type="text" class="form-control" name="street" id="street"
                           placeholder="${street}" title="Enter street">
                </div>
                <div class="col-md-3 mb-3">
                    <label for="building">${building}</label>
                    <input type="text" class="form-control" name="building" id="building"
                           placeholder="${building}" title="Enter building">
                </div>
            </div>

            <div class="row mb-3">
                <!--capacity-->
                <label for="capacity">${capacity}</label>
                <input type="number" class="form-control" name="capacity" id="capacity"
                       title="Enter capacity">
            </div>
            <hr>
            <div class="row mb-3">

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





