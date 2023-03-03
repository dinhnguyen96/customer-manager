<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        #customers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even){background-color: #f2f2f2;}

        #customers tr:hover {background-color: #ddd;}

        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>
 <h1>Danh sách khách hàng</h1>
 <table id="customers">
     <tr>
         <th>Id</th>
         <th>CustomerCode</th>
         <th>Name</th>
         <th>Addres</th>
         <th>Phone</th>
         <th>Cập nhật</th>
          <th>Xóa</th>
     </tr>
     <c:forEach var="customer" items="${customers}">
             <tr>
                 <td>${customer.id}</td>
                 <td>${customer.customerCode}</td>
                 <td>${customer.customerName}</td>
                 <td>${customer.address}</td>
                 <td>${customer.phone}</td>
                 <td><a href="/customer?action=edit&id=${customer.id}">Cập nhật</a></td>
                 <td><a href="/customer?action=remove&id=${customer.id}">Xóa</a></td>
             </tr>
     </c:forEach>
 </table>
<a href="/customer?action=add">Thêm</a>
</body>
</html>