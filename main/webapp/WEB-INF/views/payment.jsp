<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thanh Toán</title>
</head>
<body>
<h2>Thanh Toán</h2>
<form action="create-payment" method="get">
    <label>Số tiền thanh toán:</label>
    <input type="number" name="amount" min="1" required />
    <button type="submit">Thanh Toán VNPAY</button>
</form>
</body>
</html>
