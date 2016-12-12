<%-- 
    Document   : start
    Created on : Aug 22, 2016, 1:19:14 PM
    Author     : rafael
--%>

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
          <h1>Hello World!</h1>
    
          <hr>
                    <%
                    out.println("Ra!");    

                    out.println(MyClass.getToken());
                    %>
          <hr>

          <%
          ArrayList<String> arrayList = MyClass.getList();
          out.println(arrayList.toString());
          out.print("<ol>");
          for(String s : arrayList) out.println("<li>"+s+"</li>");
          out.print("</ol>");
          %>
          
          <script type="text/javascript">
                    var array = <%=MyClass.getObject() %>;
                    //alert(array[0][1]);
                    console.log(array);
                    console.log(array[0]);
                    console.log(array[1]);
                    console.log(array[0][0]);
                    console.log(array[1][0]);
                    //console.log(array[0][1]);
                    //array.forEach(function(value, index
          </script>

  </body>
</html>
