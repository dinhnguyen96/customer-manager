<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập nhật thông tin khách hàng</title>
</head>
<body>
<c:choose>
   <c:when test="${action == 'add'}">
     <h2>Thêm khách hàng</h2>
       <c:set var="action" value="${action}"/>
   </c:when>
  <c:otherwise>
     <h2>Cập nhật khách hàng</h2>
      <c:set var="action" value="${action}"/>
  </c:otherwise>
</c:choose>
<c:url var="url" value="/customer?action=${action}"/>
<form action="${url}" method="post">
    <input type="hidden" name = "id" value="${id}"><br>
    <label for="customerCode">Customer Code</label><br>
   <input type="text" id="customerCode" name="customerCode" value="${customerCode}" placeholder="customer code" ><br>
    <label for="customerName">Customer Name</label><br>
    <input type="text" id="customerName" value="${customerName}" name="customerName" placeholder="customer name" ><br>
    <label for="customerAddress">Customer Address</label><br>
    <input type="text" id="customerAddress" value="${customerAddress}" name="customerAddress" placeholder="customer address" ><br>
    <label for="customerPhone">Customer Phone</label><br>
    <input type="text" id="customerPhone" value="${customerPhone}" name="customerPhone" placeholder="customer phone" ><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
