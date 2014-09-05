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
            var commandaddress = "${pageContext.request.contextPath}/api/elevators/command";
            var liftupdateaddress = "${pageContext.request.contextPath}/api/elevators/";
            var floorTo = [0,0,0,0,0,0,0,0,0,0,0];
            var people =  [0,0,0,0,0,0,0,0,0,0,0];
            var interval = 400;
            
            var lastKnowLocation = {};
            var lastKnowDirection = {};
                    
            <c:forEach items="${elevators}" var="elevator">
                lastKnowLocation["${elevator.name}"]= ${elevator.currentFloor};
                lastKnowDirection["${elevator.name}"]= "${elevator.direction}";
            </c:forEach>
            
            var statusCurrent;

            window.onload = function()
                {
            timer = setInterval(
                    function () { updateLifts() }, 
                    interval);
                }
            
            function updateLifts(){
                $.ajax({
                    url: liftupdateaddress,
                    type: "GET",
                    dataType: "json",
                    success: function (data) {
                        updatePage(data);
                        }
                    });
            }
            
            function updatePage(liftStatus){
                statusCurrent = liftStatus;
                for (i = 0; i < liftStatus.length; i++) {
                    
                    if(lastKnowLocation[liftStatus[i].name] !== liftStatus[i].currentFloor ||
                       lastKnowDirection[liftStatus[i].name] !== liftStatus[i].direction ){
                        var s= document.getElementById("F"+liftStatus[i].currentFloor+"E"+liftStatus[i].name);
                        var iconLBL = "F"+liftStatus[i].currentFloor+"E"+liftStatus[i].name + "Icon";
                        if(liftStatus[i].direction === "DOWN"){
                            s.innerHTML = liftStatus[i].numPeople + " <span class='glyphicon glyphicon-chevron-down' id='"+iconLBL+"'></span>";
                           
                        } else if(liftStatus[i].direction === "UP"){
                            s.innerHTML = liftStatus[i].numPeople + "<span class='glyphicon glyphicon-chevron-up' id='"+iconLBL+"'></span>";
                            
                        } else {
                            s.innerHTML = liftStatus[i].numPeople + "<span class='glyphicon glyphicon-stop' id='"+iconLBL+"'></span>";
                            
                        }

                        fadeLastKnow(liftStatus[i]);
                        lastKnowLocation[liftStatus[i].name] = liftStatus[i].currentFloor;
                        lastKnowDirection[liftStatus[i].name] = liftStatus[i].direction;
                    }
                }
            }
            
            function fadeLastKnow(liftStatus){
                if(lastKnowLocation[liftStatus.name] !== liftStatus.currentFloor){
                    var s= document.getElementById("F"+lastKnowLocation[liftStatus.name]+"E"+liftStatus.name);
                    $("#F"+lastKnowLocation[liftStatus.name]+"E"+liftStatus.name+"Icon").fadeOut( "slow", function() {
                        s.innerHTML = "";
                    });
                }
            }
            
            
            function selectPeople(peoplenum, floor) {
                document.getElementById("selectPeopleLvl" + floor).innerHTML = peoplenum;
                people[floor]=peoplenum;
                sendCommandIfSet(floor);
            }
            
            function selectFloor(floortonum, floor) {
                document.getElementById("floorSelectLvl" + floor).innerHTML = floortonum;
                floorTo[floor]=floortonum;
                sendCommandIfSet(floor);
            }
            
            function sendCommandIfSet(floor){
                if ( floorTo[floor] !== 0 &&  people[floor] !== 0){
                    var amtPeople = people[floor];
                    var lvlFrom = floor;
                    var lvlTo = floorTo[floor];
                    var command = {people:amtPeople, levelFrom:lvlFrom, levelTo:lvlTo};
                    $.ajax({
                       type: "POST",
                       url: commandaddress,
                       data: JSON.stringify(command),
                       contentType: "application/json; charset=utf-8",
                       dataType: "json"});
                   
                   floorTo[floor] = 0;
                   people[floor] = 0;
                   document.getElementById("floorSelectLvl" + floor).innerHTML = "Level <span class='caret'></span>";
                   document.getElementById("selectPeopleLvl" + floor).innerHTML = "People <span class='caret'></span>";
               }
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
                                <c:if test="${floor != floornum}">
                                    <li role="presentation">
                                        <a role="menuitem" tabindex="-1" href="#" href="#" onclick="selectFloor(${floornum}, ${floor});return false;">${floornum}</a>
                                    </li>
                                </c:if>
                                    
                            </c:forEach>
                        </ul>

                    </div>
                </div>
               
                <c:forEach items="${elevators}" var="elevator">
                    <div class="FloorShaft" id="F${floor}E${elevator.name}">
                    <c:if test="${elevator.currentFloor == floor}" >
                        ${elevator.numPeople}
                        <c:choose>
                            <c:when test="${elevator.direction == 'UP'}">
                                <span class="glyphicon glyphicon-chevron-up" id="F${floor}E${elevator.name}Icon"></span>
                            </c:when>
                            <c:when test="${elevator.direction == 'DOWN'}">
                                <span class="glyphicon glyphicon-chevron-down" id="F${floor}E${elevator.name}Icon"></span>
                            </c:when>
                            <c:when test="${elevator.direction == 'STOPPED'}">
                                <span class="glyphicon glyphicon-stop"  id="F${floor}E${elevator.name}Icon"></span>
                            </c:when>
                        </c:choose>
                    </c:if>
                    </div>
                </c:forEach>
                
            </div>
            </c:forTokens>
        </div>

    </div>

</body>
</html>

