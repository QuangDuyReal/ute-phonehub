<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.utephonedhub.cart.dto.CartItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Giỏ hàng</title>
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
        }
        table, th, td {
            border: 1px solid #888;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        h2 {
            text-align: center;
        }
        .total {
            font-weight: bold;
            text-align: right;
            margin-right: 20%;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<h2>Giỏ hàng của bạn</h2>

<%
    List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
    Double totalPrice = (Double) request.getAttribute("totalPrice");
%>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Tên sản phẩm</th>
            <th>Số lượng</th>
            <th>Giá (VNĐ)</th>
            <th>Thành tiền (VNĐ)</th>
        </tr>
    </thead>
    <tbody>
    <%
        if (cartItems != null && !cartItems.isEmpty()) {
            for (CartItem item : cartItems) {
    %>
        <tr>
            <td><%= item.getId() %></td>
            <td><%= item.getProductName() %></td>
            <td><%= item.getQuantity() %></td>
            <td><%= String.format("%,.0f", item.getPrice()) %></td>
            <td><%= String.format("%,.0f", item.getPrice() * item.getQuantity()) %></td>
        </tr>
    <%
            }
        } else {
    %>
        <tr>
            <td colspan="5">Giỏ hàng trống</td>
        </tr>
    <%
        }
    %>
    </tbody>
</table>

<div class="total">
    Tổng tiền: <%= totalPrice != null ? String.format("%,.0f", totalPrice) : "0" %> VNĐ
</div>

</body>
</html>
