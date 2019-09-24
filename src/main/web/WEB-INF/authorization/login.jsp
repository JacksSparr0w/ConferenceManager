<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources" var="textResources"/>

<fmt:message bundle="${textResources}" key="sign.in" var="sign_in"/>
<fmt:message bundle="${textResources}" key="sign.up" var="sign_up"/>
<fmt:message bundle="${textResources}" key="user.login" var="login"/>
<fmt:message bundle="${textResources}" key="user.password" var="password"/>
<fmt:message bundle="${textResources}" key="continue_as_guest" var="continue_as_guest"/>
<fmt:message bundle="${textResources}" key="dont_have_an_account" var="dont_have_an_account"/>
<fmt:message bundle="${textResources}" key="have_an_account" var="have_an_account"/>
<fmt:message bundle="${textResources}" key="name.first" var="first_name"/>
<fmt:message bundle="${textResources}" key="name.second" var="second_name"/>
<fmt:message bundle="${textResources}" key="user.email" var="email"/>


<!DOCTYPE html>
<html lang="${sessionScope.language}">
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="vendor/daterangepicker/moment.min.js"></script>
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <script src="vendor/animsition/js/animsition.min.js"></script>
    <script src="vendor/select2/select2.min.js"></script>
    <script src="vendor/countdowntime/countdowntime.js"></script>
    <link rel="stylesheet" href="css/unicons.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/tooplate-style.css">

    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script src="js/main.js"></script>

</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <c:choose>
                <c:when test="${param.register != null}">

                    <!--Registration form-->
                    <form class="login100-form validate-form p-l-55 p-r-55 p-t-178" method="POST"
                          action="register">
					<span class="login100-form-title">
                            ${sign_up}
                    </span>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter username">
                            <input class="input100" type="text" name="login" placeholder="${login}">
                            <span class="focus-input100"></span>
                        </div>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter password">
                            <input class="input100" type="password" name="password" placeholder="${password}">
                            <span class="focus-input100"></span>
                        </div>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter email">
                            <input class="input100" type="text" name="email" placeholder="${email}">
                            <span class="focus-input100"></span>
                        </div>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter name">
                            <input class="input100" type="text" name="name" placeholder="${first_name}">
                            <span class="focus-input100"></span>
                        </div>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter surname">
                            <input class="input100" type="text" name="surname" placeholder="${second_name}">
                            <span class="focus-input100"></span>
                        </div>

                        <c:if test="${error != null}">
                            <div class="container alert alert-warning fade show m-t-16" role="alert">
                                <fmt:message bundle="${textResources}" key="${error}"/>
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"
                                        onclick="<c:remove var="error" scope="session"/>">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </c:if>


                        <div class="container-login100-form-btn">
                            <button type="submit" class="login100-form-btn">
                                    ${sign_up}
                            </button>
                        </div>


                        <div class="flex-col-c p-t-170 p-b-40">
						<span class="txt1 p-b-9">
                                ${have_an_account}
                        </span>

                            <a href="login_page" class="txt3">
                                    ${sign_in}
                            </a>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <form class="login100-form validate-form p-l-55 p-r-55 p-t-178" method="POST"
                          action="login">
					<span class="login100-form-title">
                            ${sign_in}
                    </span>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter username">
                            <input class="input100" type="text" name="login" placeholder="${login}">
                            <span class="focus-input100"></span>
                        </div>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter password">
                            <input class="input100" type="password" name="password" placeholder="${password}">
                            <span class="focus-input100"></span>
                        </div>

                        <c:if test="${error != null}">
                            <div class="container alert alert-warning fade show m-t-16" role="alert">
                                <fmt:message bundle="${textResources}" key="${error}"/>
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"
                                        onclick="<c:remove var="error" scope="session"/>">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </c:if>



                        <div class="container-login100-form-btn">
                            <button type="submit" class="login100-form-btn">
                                    ${sign_in}
                            </button>
                        </div>

                        <a href="home_page" class="txt2 flex-col-c p-t-16">
                                ${continue_as_guest}
                        </a>

                        <div class="flex-col-c p-t-120 p-b-40">
						<span class="txt1 p-b-9">
                                ${dont_have_an_account}
                        </span>
                            <c:url value="login.jsp" var="toRegistr">
                                <c:param name="register" value="true"/>
                            </c:url>
                            <a href="register_page" class="txt3">
                                    ${sign_up}
                            </a>
                        </div>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>