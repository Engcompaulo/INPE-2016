
<%@page import="br.inpe.lac.Mongo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Controle Acadêmico</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	
</head>
<body>

    <%
        
        if(Mongo.login(request.getParameter("usuario"), request.getParameter("senha"))){
            session.setAttribute("login", "1");
            response.sendRedirect("docentes.jsp");
        }else{
            session.setAttribute("login", "0");
            out.println("");
        }
            
     
            
                            //request.getParameter("usuario")+"' AND usr_password = '"+request.getParameter("senha")+"'");

                                    //response.sendRedirect("index.jsp"); //HttpServletResponse
        

    %>
    <img src="css/logocomp.gif" alt="ggG" style="width:473px; height:82px; margin-left: 1%; margin-top: 1%;"><hr>
    <h2 style="text-align: center;">Controle Acadêmico</h2>
    <div class="imgcontainer"  style="text-align: center; margin-top: 1%;">
            <img src="imagens/login.png" alt="Avatar"  height="100px">
    </div>
    <form action="" method="post" style="max-width: 400px; height: 400px; margin: 0 auto; margin-top: 1%;">
            <div class="form-group">
            <label for="usuario">Usuário</label>
            <input type="text" class="form-control" name="usuario" id="usuario" placeholder="Usuário">
            </div>
            <div class="form-group">
                    <label for="senha">Senha</label>
            <input type="password" class="form-control" name="senha" id="senha" placeholder="Password">
            </div>

            <button type="submit" class="btn btn-default">Entrar</button>
    </form>
</body>
</html>