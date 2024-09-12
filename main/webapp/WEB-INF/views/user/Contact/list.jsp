<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- contact.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Liên Hệ với Công ty</title>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css' />">
</head>
<body>

<div class="container">
	
	
    <div class="contact-info">
    <h3>Thông tin liên hệ</h3>
    <c:forEach var="item" items="${contact }">
        <p><strong>Địa chỉ:</strong> ${item.content }</p>
        <p><strong>Số điện thoại:</strong> ${item.office_Hotline}</p>
        <p><strong>Hotline Chăm sóc khách hàng:</strong> ${item.customer_care_Hotline}</p>
        <p><strong>Hotline cửa hàng:</strong> ${item.purchase_Hotline}</p>
        <p><strong>Email:</strong> ${item.email}</p>
    </c:forEach>
    </div>
</div>

</body>
</html>
