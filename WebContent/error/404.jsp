<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>detta</title>

        <style type="text/css">
            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
            }

            body {
                align-content: center;
                background-color: black;
                display: flex;
                flex-direction: column;
                justify-content: center;
                min-height: 100vh;
                padding: 2rem;
                width: 100%;
            }

            img {
                width: 100%;
            }
        </style>
    </head>
    <body>
        <img src="<core:url value="/resources/img/404.gif" />">
    </body>
</html>