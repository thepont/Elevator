<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/elevator.css" >
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

        <script>
            function myFunction() {
                document.getElementById("demo").innerHTML = "Paragraph changed.";
            }
        </script>


        <title>Elevator</title>
    </head>
    <body>
        <h1 class="heading">Elevator!</h1>
        <div class="Table">
            <div class="Row" id="floor">
                <div class="Cell">
                    <p>Floor</p>
                </div>
                <div class="Cell">
                    <p>No. People</p>
                </div>
                <div class="Cell">
                    <p>Level to go to</p>
                </div>
                <div class="Cell">
                    <p>Elevator A</p>
                </div>
                <div class="Cell">
                    <p>Elevator B</p>
                </div>
                <div class="Cell">
                    <p>Elevator C</p>
                </div>
                <div class="Cell">
                    <p>Elevator D</p>
                </div>
            </div>
            <c:forTokens items="10,9,8,7,6,5,4,3,2,1" delims="," var="floor">
            <!--<%// for (int floor = 10; floor >= 1; floor--) {%>-->
            <div class="Row" id="floor${floor}">
                <div class="Cell">
                    <p>${floor}</p>
                </div>
                <div class="Cell">
                    <div class="dropdown">

                        <a id="selectPeopleLvl${floor}" role="button" data-toggle="dropdown" data-target="#">
                            People <span class="caret">
                            </span></a>
                        
                        <ul class="dropdown-menu" role="menu" aria-labelledby="selectPeopleLvl${floor}">
                            <c:forEach var="peoplenum" begin="1" end="20">
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="#" href="#" onclick="selectPeople(${peoplenum}, ${floor});return false;">${peoplenum}</a>
                                </li>
                            </c:forEach>
                        </ul>

                    </div>
                </div>
                <div class="Cell">
                    <div class="dropdown">

                        <a id="floorSelectLvl${floor}" role="button" data-toggle="dropdown" data-target="#">
                            Level <span class="caret">
                            </span></a>

                        <ul class="dropdown-menu" role="menu" aria-labelledby="floorSelectLvl${floor}">
                            <c:forEach var="floornum" begin="1" end="10">
                                <li role="presentation">
                                    <a role="menuitem" tabindex="-1" href="#" href="#" onclick="selectPeople(${floornum}, ${floor});return false;">${floornum}</a>
                                </li>
                            </c:forEach>
                        </ul>

                    </div>
                </div>
               
                <c:forEach items="${elevators}" var="elevator">
                    <div class="FloorShaft">
                    <c:if test="${elevator.currentFloor == floor}" >
                        <c:choose>
                            <c:when test="${elevator.direction == 'UP'}">
                                <span class="glyphicon glyphicon-chevron-up"></span>
                            </c:when>
                            <c:when test="${elevator.direction == 'DOWN'}">
                                <span class="glyphicon glyphicon-chevron-down"></span>
                            </c:when>
                            <c:when test="${elevator.direction == 'STOPPED'}">
                                <span class="glyphicon glyphicon-stop"></span>
                            </c:when>
                        </c:choose>
                    </c:if>
                    </div>
                </c:forEach>
                
            </div>
            </c:forTokens>
            <% //} //Each Floor%> 
        </div>

    </div>

</body>
</html>

