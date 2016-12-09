
<%@page import="br.inpe.lac.Mongo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <script src="js/d3.v3.min.js" type="text/javascript"></script>
    <script src="js/timeline.js" type="text/javascript"></script>
    <script src="js/jquery-3.1.1.min.js"></script>
    
    
    <title>JSP Page</title>
    
    <script>
        $(document).ready(function(){
            var id = <%=request.getParameter("docentes") %>
            console.log(id);
            
        });
    </script>
    
    
</head>
<body>
    <img src="css/logocomp.gif" alt="ggG" style="width:473px; height:82px; margin-left: 1%; margin-top: 1%;"><hr>
    <br /><br />
           
 
    
    <%
                String id = request.getParameter("docentes");
                out.print(Mongo.construaLinhaDoTempoDoDocente(id));
    %>
    
    
    
    

     
</body>
</html>