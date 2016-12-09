
<%@page import="br.inpe.lac.Mongo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <script src="js/d3.v3.min.js" type="text/javascript"></script>
    <script src="js/timeline.js" type="text/javascript"></script>
    <script src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/jspdf.js"></script>
    <script type="text/javascript" src="js/html2canvas.js"></script>
    <script type="text/javascript" src="js/canvas2image.js"></script>
    <title>JSP Page</title>
    
    <style type="text/css">
        body{
            background-color: white;
        }
      
        .destaque{
            background: #F0F8FF	;
        }
        
        #download:hover img{
            width: 65px;
           
        }
    </style>
        

    
    <script>    
        
        $(document).ready(function(){
            $('#timeline').border = 4;
            var id = <%=request.getParameter("docentes") %>
            console.log(id);
            
            /*The first step is to call html2canvas . We need to pass an element that we'd like to take a screenshot
              then we're going to pass document.body to capture the entire page
              next step is to define a callback event after the screenshot has been taken. The event name is "onrendered"
              The canvas object will be provided to the callback function (Our captured screenshot)
              We'll use "toDataURL" function to retrive the screenshot. We'll specify the image type as "image/png"
              Next we'll create a jsPDF object as usual. And we'll use 'addImage' to add the screenshot to the jsPDF object
              with 20px margin
              jsPDF(orientation, unit, format) Creates new jsPDF document object
              orientation One of "portrait" or "landscape" (or shortcuts "p" (Default), "l")
              unit Measurement unit to be used when coordinates are specified. One of "pt" (points), "mm" (Default), "cm", "in"
              format One of 'a3', 'a4' (Default),'a5' ,'letter' ,'legal'
            */
             html2canvas(document.body, {
                onrendered: function(canvas){      
                    $('#download').attr('href', canvas.toDataURL("image/jpeg"));
                    $('#download').attr('download', 'image.jpeg');
                    $('#download').click();
                }
            });
            
            
           
            
            $('table#timeline tr').hover(
                function(){  
                    //tr.not(this).hide();
                    //$(this).css('margin-top', '40');
                    $(this).addClass('destaque');
                    
                },
                function(){
                    $(this).removeClass('destaque');

                }
            );
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

    
        <div style="position: relative; top:50%; left:50%; margin-top: 3%;">
            
            <a  id="download" ><img src="imagens/download.png" width="60"  alt=""/></a> 
    
        </div>
            

   
    
    
   
    
    
    

     
</body>
</html>