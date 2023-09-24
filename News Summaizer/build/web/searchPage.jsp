<%@page import="java.io.Reader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileWriter"%>
<%@page import="jdk.jfr.events.FileWriteEvent"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String path = "C:\\Users\\Nasef Abd El-Hamid\\Documents\\NetBeansProjects\\try final gp code";
            String searchquery = String.valueOf(session.getAttribute("title")).replace(" ", "_");
            System.err.println(searchquery);
        %>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <link href="HomePageDesgin2.css" rel="stylesheet" type="text/css"/>
        <link href="pageTransaction.css" rel="stylesheet" type="text/css"/>
        <title>News Summarizer</title>
        <script>
            function history_form() {
                var x = document.getElementById("historyView");
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
            
        
            <p id="logo">News Summarizer</p>
            <p id="com">.com</p>

            <form action="index.jsp" method="get">
                <input type="textbox" id="txtbx" placeholder=".....ابحث هنا" value = <%=searchquery%> >
                <input type="submit" id="bttn" value="بحث جديد">
            </form>
        </div>
            
        <div class="container">
            <div id="historyView">
                <p>:سجل بحثك</p>                
            </div>
            
            <div class= "sentence">
                
            <%
                File file = new File(path + "\\summary.txt");
                
                ArrayList<String> sentences = new ArrayList<>();    
                if(file.exists()){
                %>    
                    <p class = "side_title">:ملخص الاخبار</p>
                <%
                    Reader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
                    BufferedReader br = new BufferedReader(reader);
                    //FileReader fr = new FileReader(file);
                    //BufferedReader br = new BufferedReader (fr);
                    String line;

                    //read from file line by line
                    while((line = br.readLine())!= null){
                        sentences.add(line);
                    }
                }else{
                
            %>
                <p class = "plswt">Please Wait...</p>
            <%
                }                
            %>
            <% 
                for(int i = 0; i < sentences.size(); i++){
            %>
                <p class = "sumsent"><% out.print(sentences.get(i));%></p>
            <%
                }
            %>
            </div>
            <%
                if(session.getAttribute("username") != null){
            %>
                <div class="rate">
                    <form action="rate">
                        <label>:قيم جودة الخبر</label><br>
                        <input type="range" id="raterng" name="rate" min="0" max="10">
                        <input id="ratebttn" type="submit" value="Rate">
                        <input type="hidden" name="title" value="<%=searchquery%>">
                    </form>
                </div>
                    
                <div id = "history" onclick="history_form()">
                    <div class="lgntxt">سجل البحث </div>
                </div>
            <%
                }
            %>
                
        </div>


        <div class="footer">

        </div>

    </body>
</html>
