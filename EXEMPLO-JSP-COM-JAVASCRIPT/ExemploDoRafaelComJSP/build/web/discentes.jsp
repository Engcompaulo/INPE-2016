<%-- 
    Document   : discentes
    Created on : Nov 1, 2016, 10:41:50 AM
    Author     : abraao
--%>
<%@page import="utils.Mongo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.MyClass"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <title>JSP Page</title>
</head>
<body>
          <h1>Hello World!</h1><hr>
          <script type="text/javascript">
                    var discentesCPFs = <%=Mongo.getDiscentesCPFs() %>
                    
                    //console.log(discentesCPFs);
                    
                    var discentesDatas = <%= Mongo.getDiscentesDatas() %>
                    console.log(discentesDatas[0]);
                    console.log(discentesDatas[0].data_inicial);
                    console.log(discentesDatas[0].data_final);
                    console.log(discentesDatas[0].data_inicial.mes);
          </script>
          
</body>
</html>
