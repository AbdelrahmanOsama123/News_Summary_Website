<!DOCTYPE html>
<%@ page pageEncoding="utf-8" %>
<html>
    <head>
        <title>News Summarizer</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="HomePageDesgin.css" rel="stylesheet" type="text/css"/>
        <link href="pageTransaction.css" rel="stylesheet" type="text/css"/>
        <script>
            function login_form() {
                var x = document.getElementById("lgnform");
                if (x.style.display === "none") {
                  x.style.display = "block";
                } else {
                  x.style.display = "none";
                }
            }
            function signup_form() {
                var x = document.getElementById("sgnpform");
                if (x.style.display === "none") {
                  x.style.display = "block";
                } else {
                  x.style.display = "none";
                }
            }
            
             
        </script>
    </head>
    <body>
        <div class="header">
            
        </div>
        <div class="container">
            <div id="lgnform">
                <form action="UserController" method="POST">
                    <label class = "loglbl">Log In</label><br><br><br><br>
                    <label class = "usrlbl">Username </label><br>
                    <input id="username" name="username" minlength="3" maxlength="20" required /> <br>
                    <br>
                    <label class = "psslbl">Password </label><br>
                    <input id="password" name="password" type="password"  minlength="3" maxlength="100" required />
                    <input type="hidden" name="regtype" value="login">
                    <br>
                    <button id="lgnBtn" type="submit">Log In</button>
                </form>
            </div>

            <div id="sgnpform">
                <form action="UserController" method="POST">
                    <label class = "loglbl">Sign Up</label><br><br><br><br>
                    <label class = "usrlbl" >Username </label><br>
                    <input id="username" name="username" minlength="3" maxlength="20" required /> <br>
                    <br>
                    <label class = "psslbl" >Password </label><br>
                    <input id="password" name="password" type="password"  minlength="3" maxlength="100" required />
                    <br><br>
                    <label class = "psslbl" >Age </label><br>
                    <input id="agebox" name="age" type="number" equired />
                    <input type="hidden" name="regtype" value="signup">
                    <br>
                    <button id="lgnBtn" type="submit">Sign Up</button>
                </form>
            </div>    
            <%
                if(session.getAttribute("username") != null){
                    String name = (String)session.getAttribute("username");
                
                
                    
            %>
            <div id="register">
                <div id="loginname">
            <%=name%>
                </div>
            </div>
            <%
                }else{
            %>
            
            <div id="register">
            
                <div id = "lgn" class="reg" onclick="login_form()">
                    <div class="cyrclimg">
                        <img src="images/Clogin.png">
                    </div>
                    <div class="lgntxt">Log In </div>
                </div>
                <div id = "sgnp" class="reg" onclick="signup_form()">
                    <div class="cyrclimg">
                        <img src="images/signup.png">
                    </div>
                    <div class="lgntxt">Sign Up </div>
                </div>
                    
            </div>
            <%
                }
            %>
                <p id="logo">News Summarizer</p>
                <p id="com">.com</p>
                
                <form action="NewsController" method="get">
                    <input name= "searchQuery" type="textbox" id="txtbx" placeholder=".....search here....." required=""><br>
                    <input type="submit" id="bttn" value="Search">
                </form>

            
            </div>
            
 

        <div class="footer">

        </div>

    </body>
</html>
