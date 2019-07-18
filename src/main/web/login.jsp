<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
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
						Sign Up
					</span>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter username">
                            <input class="input100" type="text" name="login" placeholder="Username">
                            <span class="focus-input100"></span>
                        </div>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter password">
                            <input class="input100" type="password" name="password" placeholder="Password">
                            <span class="focus-input100"></span>
                        </div>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter email">
                            <input class="input100" type="text" name="email" placeholder="Email">
                            <span class="focus-input100"></span>
                        </div>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter name">
                            <input class="input100" type="text" name="name" placeholder="Name">
                            <span class="focus-input100"></span>
                        </div>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter surname">
                            <input class="input100" type="text" name="surname" placeholder="Surname">
                            <span class="focus-input100"></span>
                        </div>

                        <c:if test="${error_registration eq true}">
                            <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                                User with this login already exist. Sign in or choose another login.
                            </div>
                        </c:if>


                        <div class="container-login100-form-btn">
                            <button type="submit" class="login100-form-btn">
                                Sign up
                            </button>
                        </div>

                        <div class="flex-col-c p-t-170 p-b-40">
						<span class="txt1 p-b-9">
							Have an account?
						</span>

                            <a href="controller?command=login" class="txt3">
                                Sign in now
                            </a>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <form class="login100-form validate-form p-l-55 p-r-55 p-t-178" method="POST"
                          action="controller?command=login">
					<span class="login100-form-title">
						Sign In
					</span>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter username">
                            <input class="input100" type="text" name="login" placeholder="Username">
                            <span class="focus-input100"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Please enter password">
                            <input class="input100" type="password" name="password" placeholder="Password">
                            <span class="focus-input100"></span>
                        </div>

                        <c:if test="${error_authentification eq true}">
                            <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                                Enter <strong>invalid</strong> login or password. Please try again

                            </div>
                        </c:if>

                        <div class="text-right p-t-13 p-b-23">
						<span class="txt1">
							Forgot
						</span>

                            <a href="#" class="txt2">
                                Username / Password?
                            </a>
                        </div>

                        <div class="container-login100-form-btn">
                            <button type="submit" class="login100-form-btn">
                                Sign in
                            </button>
                        </div>

                        <div class="flex-col-c p-t-170 p-b-40">
						<span class="txt1 p-b-9">
							Don"t have an account?
						</span>
                            <c:url value="login.jsp" var="toRegistr">
                                <c:param name="register" value="true"/>
                            </c:url>
                            <a href="controller?command=register" class="txt3">
                                Sign up now
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