<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
      <title>Cart</title>
   </head>
   <body>
      <h1>Cart</h1>
      <br/>
      <form method="get" action="main">
         <input type="hidden" name="stage" value="main">
         <button type="submit">MainPage</button>
      </form>
      <h3>
         <c:forEach var="entry" items="${cartproducts}">
            Product:
            <c:out value="${entry.key}"/>
            Count:
            <c:out value="${entry.value}"/>
            <p>
         </c:forEach>
      </h3>
   </body>
</html>