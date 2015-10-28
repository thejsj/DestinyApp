<%--
  Created by IntelliJ IDEA.
  User: franzsilv1
  Date: 10/19/2015
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="shortcut icon" href="../../Images/favicon.ico" type="image/x-icon">
    <link ref="icon" href="../../Images/favicon.ico" type="image/x-icon">

    <link type="text/css" rel="stylesheet" href="../../styles/styling.css">

    <link href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" rel="stylesheet" />
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

    <script src="../../JS scripts/clientCode.js" type="text/javascript"></script>
    <script src="../../JS scripts/classDivCode.js" type="text/javascript"></script>
    <script src="../../JS scripts/subclassAbilitiescode.js" type="text/javascript"></script>

    <title>Frogzsj's Destiny App</title>
</head>
<body>
    <div id="headerContainer" class="centerText">
        <a href="">
            <div id="headerContent" class="inline">
                <img src="../../Images/header_logo.jpg" height="80">
            </div>
        </a>
    </div>
    <%--<br/>--%>
    <div id="navBarContainer">
        <div id="navBarContent">
            <div id="navBar" class="centerText">
                <ul id="tabs">
                    <li id="homeTab"><a href="/spring/">Home</a></li>
                    <li id="classTab"><a href="/spring/class">Classes</a></li>
                    <li id="locationTab"><a href="#locationTab">Locations</a></li>
                    <li id="activityTab"><a href="#activityTab">Activities</a></li>
                    <li id="armorTab"><a href="#armorTab">Armors</a></li>
                    <li id="weaponTab"><a href="#weaponTab">Weapons</a></li>
                </ul>
            </div>
        </div>
    </div>
