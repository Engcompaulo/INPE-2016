
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <title>JSP Page EHH</title>
          <script src="js/jquery-3.1.1.min.js"></script>
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

          <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

          <script>
                   $(document).ready(function(){
                              var ul = document.getElementById("ul");
                             $.ajax({
                                       type: "GET",
                                       url: "pessoas",
                                       headers: {
                                                Accept: "application/json; charset=utf-8", 
                                                "Content-Type": "application/json; charset=utf-8"   
                                       },
                                       async: true,
                                       cache: true,
                                       success: function(data){
                                                data = JSON.parse(data);
                                                for(index in data){
                                                          console.log(data[index]);
                                                          var li = document.createElement("li");
                                                          var a = document.createElement("a");
                                                         var texto = document.createTextNode(data[index].nome);
                                                         a.appendChild(texto);  
                                                          a.setAttribute("href", "timeline.jsp?id="+data[index]._id);
                                                          li.appendChild(a);
                                                          ul.appendChild(li);
                                                }                                            
                                       }
                                       
                             });
                             
                             
                   });
          </script>
    </head>
    <body>
     

<div class="container" style="text-align: center; margin-top: 10%; max-width: 500px;">
  <ul class="nav nav-pills nav-stacked" id="ul" >
     
 
  </ul>
</div>
        
        
    </body>
</html>
