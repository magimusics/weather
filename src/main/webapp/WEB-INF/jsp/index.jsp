<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Прогноз погоды</title>
    <link rel="stylesheet" href="css/design.css" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"></script>
    <script src="js/weather.js"></script>

    <script src="js/jquery.custombox.js"></script>
    <link rel="stylesheet" href="css/jquery.custombox.css">
    <script>
        $(function() {
            $('#fadein').on('click', function () {
                $.fn.custombox( this, {
                    effect: 'fadein'
                });
                return false;
            });
        });
        $(function() {
            $('#fadeinreg').on('click', function () {
                $.fn.custombox( this, {
                    effect: 'fadein'
                });
                return false;
            });
        });
    </script>

</head>
<body>
<security:authorize access="hasAnyRole('ROLE_USER')" var="isUSer"/>



<div class="main">
    <div class="header">
        <div class="header_intro1">
            <div class="description">
                <h2 class="h2f">Посмотрим погоду?</h2>
            </div>

            <c:if test="${not isUSer}">
                <div class="sign">
                    <div class="signintro">
                        <input type="button" class="signin btns" value="Войти" id="fadein" href="#modal">
                        <input type="button" class="reg btns" value="Регистрация" id="fadeinreg" href="#modal2">
                    </div>
                </div>
            </c:if>
        </div>
        <div class="header_intro2">
        </div>

    </div>

    <c:if test="${isUSer}">
        <div class="dropdown">
            <button class="dropbtn"><b><c:out value="${user}"/></b></button>
            <div class="dropdown-content">
                <a href="/stats">Статистика</a>
                <a href="logout">Выйти</a>
            </div>
        </div>
    </c:if>

    <div id="modal" style="display: none;" class="modalcontent">
        <div class="modalheader">
            <h3><center>Введите свои данные</center></h3>
        </div>
        <div class="login">

                <div class="row">
                    <div class="fl">
                        <h3>Логин</h3>
                    </div>
                    <div class="fi">
                        <input type="text" class="l css-input" id="un"><br>
                    </div>
                </div>
                <div class="row">
                    <div class="fl">
                        <h3>Пароль</h3>
                    </div>
                    <div class="fi">
                        <input type="password" class="l css-input" id="pass"><br>
                    </div>
                </div>
            <div style="display: none; margin: 5px;" id="orow">
                <div id="alarm"></div>
            </div>
                <div class="row">

                    <div align="center" style="padding: 0">

                        <div class="sub">
                            <input type="button" class="btn" onclick="$.fn.custombox('close');" value="Отмена">
                            <input type="submit" class="btn" value="Войти" onclick="checkUser()">
                        </div>
                    </div>
                </div>

        </div>
    </div>
    <div id="modal2" style="display: none;" class="modalcontent">
        <div class="modalheader">
            <h3><center>Введите свои данные</center></h3>
        </div>
        <div class="login">
                <div class="row">
                    <div class="fl">
                        <h3>Логин</h3>
                    </div>
                    <div class="fi">
                        <input type="text" class="l css-input" id="userreg"><br>
                    </div>
                </div>
                <div class="row">
                    <div class="fl">
                        <h3>Пароль</h3>
                    </div>
                    <div class="fi">
                        <input type="text" class="l css-input" id="passreg"><br>
                    </div>
                </div>
            <div style="display: none; margin: 5px;" id="orow2">
                <div id="alarm2"></div>
            </div>
                <div class="row">
                    <div align="center" style="padding: 0">
                        <div class="sub">
                            <input type="button" class="btn" onclick="$.fn.custombox('close');" value="Отмена">
                            <input type="submit" class="btn" value="Регистрация" onclick="addUser()">
                        </div>
                    </div>
                </div>
        </div>
    </div>

    <div class="content">
        <div class="searchdiv al">
            <div class="searchrow">
                <input type="text" class="css-input" id="city">
                <input type="button" class="btn" value="Посмотреть" onclick="checkWeather()">
            </div>
        </div>
        <div class="searchdiv weather-wrapper" id="weatherpoint" style="display: none">
            <div class="weather" >
                <div id="awaiting" align="center" style="display: none;">
                    <svg class="spinner" width="45px" height="45px" viewBox="0 0 66 66" xmlns="http://www.w3.org/2000/svg">
                        <circle class="path" fill="none" stroke-width="6" stroke-linecap="round" cx="33" cy="33" r="30"></circle>
                    </svg>
                </div>
                <table class="tab" id="tabl" style="display: none">
                    <tr class="tr1">
                        <td>Город</td>
                        <td id="cityname"></td>
                    </tr>
                    <tr>
                        <td>Погода</td>
                        <td id="description"></td>
                    </tr>
                    <tr class="tr1">
                        <td>Температура</td>
                        <td id="temp"></td>
                    </tr>
                    <tr>
                        <td>Давление</td>
                        <td id="pressure"></td>
                    </tr>
                    <tr class="tr1">
                        <td>Влажность</td>
                        <td id="humidity"></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>