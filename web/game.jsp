<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : game
    Created on : Apr 24, 2018, 3:05:08 PM
    Author     : Productivity
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mastermind</title>
        <link rel="stylesheet" type="text/css" href="gameStyle.css"
    </head>

    <h1>Mastermind</h1>

    <form action="<c:url value='newGame'/>" method="post" class="padded">
        <c:if test="${game.won == true && game.valid == true}">
            <h2>You win!</h2>
        </c:if>
        <input type="submit" value="Start new game" name="newGame"/>
    </form>

    <form action="<c:out value='giveUp'/>" method="post" class="padded">
        <input type="submit" value="I give up, what is it?" name="giveUp"
               <c:if test="${game.won == true || game.valid == false}">disabled</c:if>/>
        </form>

        <div id="scoreboard" class="padded">
            Guesses: ${game.numGuesses}<br>
        Games won: ${gamesWon} &nbsp; Best: ${bestScore} 
        <c:if test="${not empty bestScore}">guesses</c:if>
        </div>

        <form name="guess" action="<c:url value='Game' />" method="post" class="padded">

        <select name="selection1" class="peg">
            <c:forEach var="color" items="${colorChoices}">
                <option value="${color}" style="background-color: ${color}"
                        <c:if test="${param.selection1 == color}">selected</c:if>>
                    ${color}
                </option>
            </c:forEach>
        </select>

        <select name="selection2" class="peg">
            <c:forEach var="color" items="${colorChoices}">
                <option value="${color}" style="background-color: ${color}"
                        <c:if test="${param.selection2 == color}">selected</c:if>>
                    ${color}
                </option>
            </c:forEach>
        </select>

        <select name="selection3" class="peg">
            <c:forEach var="color" items="${colorChoices}">
                <option value="${color}" style="background-color: ${color}"
                        <c:if test="${param.selection3 == color}">selected</c:if>>
                    ${color}
                </option>
            </c:forEach>
        </select>

        <select name="selection4" class="peg">
            <c:forEach var="color" items="${colorChoices}">
                <option value="${color}" style="background-color: ${color}"
                        <c:if test="${param.selection4 == color}">selected</c:if>>
                    ${color}
                </option>
            </c:forEach>
        </select>

        <input type="submit" value="Submit" name="guess" 
               <c:if test="${game.won == true || game.valid==false}">disabled</c:if>/>

        </form>

    <c:if test="${showSolution==true}">
        <div class="padded">
            Solution:
            <table border="1">
                <tbody>
                    <tr>        
                        <c:forEach var="peg" items="${game.solution}">
                            <td style="background-color: ${peg}">
                                <div class="colorPeg">${peg}</div>
                            </td>
                        </c:forEach>
                    </tr>
                </tbody>
            </table>
        </div>
    </c:if>

    Previous:
    <c:if test="${game.numGuesses >= 1}">

        <table border="1">
            <c:forEach var="turn" items="${game.history}">
                <tr>
                    <c:forEach var="peg" items="${turn.guess}">
                        <td style="background-color: ${peg}">
                            <div class="colorPeg">${peg}</div>
                        </td>
                    </c:forEach>
                    <td></td>
                    <c:forEach var="peg" items="${turn.feedback}">
                        <td style="background-color: ${peg}; 
                            <c:choose>
                                <c:when test="${peg == 'BLACK'}">
                                    color: white;
                                </c:when>
                                <c:when test="${peg == 'WHITE'}">
                                    color: black;
                                </c:when>
                                <c:otherwise>
                                    background-color: grey;
                                </c:otherwise>
                            </c:choose>">
                            <div class="feedbackPeg">${peg}</div>
                        </td>

                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</html>
