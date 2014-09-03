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
        
                <div class="Cell">
                    <p>10</p>
                </div>
                <div class="Cell">
                    <div class="dropdown">
                        
                        <a id="dLabel" role="button" data-toggle="dropdown" data-target="#">
                            People <span class="caret">
                        </span></a>
                        
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                            <li role="presentation">
                                <a role="menuitem" tabindex="-1" href="http://twitter.com/fat">Action</a>
                            </li>
                            
                            <li role="presentation">
                                <a role="menuitem" tabindex="-1" href="http://twitter.com/fat">Action2</a>
                            </li>
                        </ul>
                      
                    </div>
                </div>
                <div class="Cell">
                    <div class="dropdown">
                        
                        <a id="dLabel" role="button" data-toggle="dropdown" data-target="#">
                            Level <span class="caret">
                        </span></a>
                        
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                            <li role="presentation">
                                <a role="menuitem" tabindex="-1" href="http://twitter.com/fat">Action</a>
                            </li>
                            
                            <li role="presentation">
                                <a role="menuitem" tabindex="-1" href="http://twitter.com/fat">Action2</a>
                            </li>
                        </ul>
                      
                    </div>
                </div>
                <div class="FloorShaft">
                    <span class="glyphicon glyphicon-chevron-up"></span>
                    
                </div>
                <div class="FloorShaft">
                    <span class="glyphicon glyphicon-chevron-down"></span>
                    
                </div>
                <div class="FloorShaft" style="text-align: center">
                    <span class="glyphicon glyphicon-stop"></span>
                </div>
                <div class="FloorShaft">
                    <span class="glyphicon glyphicon-stop"></span>
                </div>
            </div>
            
        </div>
        
        
        
    </body>
</html>

