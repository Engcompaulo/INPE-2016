<%-- 
    Document   : teste
    Created on : Nov 4, 2016, 9:30:48 AM
    Author     : abraao
--%>

<%@page import="br.inpe.lac.Mongo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <script src="js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 
    <style type="text/css">
        fieldset{
            border: 1px solid #000;display: block;
            margin-left: 2px;
            margin-right: 2px;
            padding-top: 0.35em;
            padding-bottom: 0.625em;
            padding-left: 0.75em;
            padding-right: 0.75em;
        }
        legend{border: 0px; text-align: left;}
    </style>
          
    <script type="text/javascript">
        function carregaDocentes(){
            var docentes = [<%=Mongo.getDocentes() %>];
            console.log(docentes);
            console.log(docentes.length);
            var opcoes = "";
            
            /*
            orientadores.forEach(function(item, index){
                opcoes += "<option "
            });
            */
           
            for(var i = 0; i < docentes.length; i= i + 2){
                opcoes += "<option value='"+docentes[i]+"'>"+docentes[i+1]+"</option>";
            }
            document.getElementById('docentes').innerHTML = opcoes;
        }
    </script>
</head>
<body onload="carregaDocentes();">
    <img src="css/logocomp.gif" alt="ggG" style="width:473px; height:82px; margin-left: 1%; margin-top: 1%;"><hr>


    <form method="post" action="docente.jsp" style="margin: 0 auto; margin-top: 5%; max-width: 500px;">
        <fieldset>
        <legend>Escolha um Orientador</legend>
        <div class="form-group">
            <!--<label for="orientadores">Escolha um Orientador</label>-->
            <select id="docentes" name="docentes" class="form-control">  

            </select>
        </div>
        <button type="submit" class="btn btn-primary">Confirmar</button>
        </fieldset>
    </form>
    
    




</body>
</html>
