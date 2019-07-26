<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<c:choose>
    <c:when test="${sessionScope.language != null}">
        <fmt:setLocale value="${sessionScope.language}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="language" var="language"/>

<fmt:message key="sign_in" bundle="${language}" var="sign_in"/>
<fmt:message key="sign_up" bundle="${language}" var="sign_up"/>
<fmt:message key="login" bundle="${language}" var="login"/>
<fmt:message key="password" bundle="${language}" var="password"/>
<fmt:message key="continue_as_guest" bundle="${language}" var="continue_as_guest"/>
<fmt:message key="dont_have_an_account" bundle="${language}" var="dont_have_an_account"/>
<fmt:message key="have_an_account" bundle="${language}" var="have_an_account"/>
<fmt:message key="login" bundle="${language}" var="login"/>
<fmt:message key="first_name" bundle="${language}" var="first_name"/>
<fmt:message key="second_name" bundle="${language}" var="second_name"/>
<fmt:message key="email" bundle="${language}" var="email"/>







<!DOCTYPE html>
<html lang="${sessionScope.language}">
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!--===============================================================================================-->
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <c:choose>
                <c:when test="${param.register != null}">

                    <!--Registration form-->
                    <form class="login100-form validate-form p-l-55 p-r-55 p-t-178" method="POST"
                          action="controller?command=register">
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

                        <c:if test="${error_registration eq true}">
                            <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                                User with this login already exist. Sign in or choose another login.
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

                            <a href="controller?command=login" class="txt3">
                                ${sign_in}
                            </a>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <form class="login100-form validate-form p-l-55 p-r-55 p-t-178" method="POST"
                          action="controller?command=login">
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

                        <c:if test="${error_authentification eq true}">
                            <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                                Enter <strong>invalid</strong> login or password. Please try again

                            </div>
                        </c:if>

                        <div class="container-login100-form-btn">
                            <button type="submit" class="login100-form-btn">
                                ${sign_in}
                            </button>
                        </div>

                        <a href="controller?command=home_page" class="txt2 flex-col-c p-t-16">
                            ${continue_as_guest}
                        </a>

                        <div class="flex-col-c p-t-120 p-b-40">
						<span class="txt1 p-b-9">
							${dont_have_an_account}
						</span>
                            <c:url value="login.jsp" var="toRegistr">
                                <c:param name="register" value="true"/>
                            </c:url>
                            <a href="controller?command=register" class="txt3">
                                ${sign_up}
                            </a>
                        </div>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>


<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>

</body>
</html>