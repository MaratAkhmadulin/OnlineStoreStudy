<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
      <title>Online Store</title>
   </head>
   <body>
      <c:if test="${sessionScope.authStatus!=1}">
         <form method="get" action="auth.jsp">
            <button type="submit">Sign In</button>
         </form>
      </c:if>
      <c:if test="${sessionScope.authStatus==1}">
         <c:out value="User = ${sessionScope.username}" />
      </c:if>
      <form method="get" action="main">
         <input type="hidden" name="stage" value="cart">
         <button type="submit">Cart</button>
      </form>
      <h1><%= "Catalog Products" %></h1>
      <br/>
      <h3>
         <c:forEach var="product" items="${products}">
            <form method="get" action="main">
               <input type="hidden" name="stage" value="main">
               <input type="hidden" name="product" value=
               <c:out value="${product.name}"/>
               >
               <button type="submit">
                  <c:out value="${product.name}"/>
               </button>
               <c:set var="sessionproductname" value="cartproducts${product.name}" />
               <c:out value="${sessionScope[sessionproductname]}" />
            </form>
         </c:forEach>
      </h3>
   </body>
</html>