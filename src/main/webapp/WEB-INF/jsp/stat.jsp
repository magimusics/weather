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
                <a href="/">На главную</a>
                <a href="logout">Выйти</a>
            </div>
        </div>
    </c:if>

    <div class="content">
        <div class="searchdiv" id="weatherpoint" style="height:auto">
            <h2> <center>Статистика</center></h2>
            <div>
                <table class="tab" id="tabl" style="margin: 10px;">
                    <tr class="tr1">
                        <td>Запрос</td>
                        <td>Дата</td>
                        <td>Город</td>
                        <td>Погода</td>
                        <td>Температура</td>
                        <td>Влажность</td>
                        <td>Давление</td>
                    </tr>
                    <c:if test="${stats !='true' and stats != 'false'}">
                        <c:forEach var="i" items="${stats}">
                            <tr class="tr1">
                                <td style="width: auto">${i.request}</td>
                                <td style="width: auto">${i.date}</td>
                                <td style="width: auto">${i.city}</td>
                                <td style="width: auto">${i.description}</td>
                                <td style="width: auto">${i.temp}</td>
                                <td style="width: auto">${i.humidity}</td>
                                <td style="width: auto">${i.pressure}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>