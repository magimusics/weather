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
</head>
<body>
<div class="main">
    <div class="header">
        <div class="header_intro1">
            <div class="description">
                <h2 class="h2f">Посмотрим погоду?</h2>
            </div>
        </div>
        <div class="header_intro2">
        </div>

    </div>
    <div class="content">
        <div class="searchdiv al">
            <div class="searchrow">
                <input type="text" class="css-input" id="city">
                <input type="button" class="btn" value="Посмотреть" onclick="checkWeather()">
            </div>
        </div>
        <div class="searchdiv weather-wrapper">
            <div class="weather" id="weatherpoint">
                {{user}}
            </div>
        </div>
    </div>
</div>
</body>
</html>