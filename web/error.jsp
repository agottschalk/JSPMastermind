<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : error
    Created on : May 12, 2018, 10:05:50 PM
    Author     : Productivity
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mastermind - Error</title>
    </head>
    <body>
        <h1>:'(</h1>
        <h2>We're sorry.</h2>
        <p>It appears an error has occurred and the current game cannot continue.
            You can <a href="<c:url value ='newGame'/>">start a new game</a>, if you wish.</p>
    </body>
</html>
