<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Chi Tiết Hóa Đơn</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 20px;
}
</style>
</head>
<body>
	<h1 class="page-title">Chi Tiết Hóa Đơn</h1>

	<table>
		<thead>
			<tr>
			<th>Id</th>
				<th>Tài khoản</th>
				<th>Họ và tên</th>
				<th>Số điện thoại</th>
				<th>Tên sản phẩm</th>
				<th>Giá (1 sản phẩm)</th>
				<th>Số lượng</th>
				<th>Kích cỡ (size)</th>
				<th>Tổng tiền</th>
				<th>Địa chỉ</th>

				<th>Trạng Thái</th>
			</tr>
		</thead>
		<tbody>
			<form action="updateStatus" method="post">
			    <input type="hidden" name="status" value="2" />
			    <table>
			        <c:forEach var="billDetail" items="${listBill}">
			            <tr>
			                <td>${billDetail.id_bills}</td>
			                <td>${billDetail.user}</td>
			                <td>${billDetail.display_name}</td>
			                <td>${billDetail.phone}</td>
			                <td>${billDetail.productName}</td>
			                <td><fmt:formatNumber value="${billDetail.price}" type="number" pattern="#,##0" />₫</td>
			                <td>${billDetail.quanty}</td>
			                <td>${billDetail.size}</td>
			                <td><fmt:formatNumber value="${billDetail.total}" type="number" pattern="#,##0" />₫</td>
			                <td>${billDetail.address}</td>
			                <td>
			                    <c:choose>
			                        <c:when test="${billDetail.status == 1}">Đang Xử Lý</c:when>
			                        <c:otherwise>Đã Xử Lý</c:otherwise>
			                    </c:choose>
			                </td>
			                <input type="hidden" name="id" value="${billDetail.id_bills}" />
			            </tr>
			        </c:forEach>
			    </table>
			    <button type="submit" class="update-button">Cập Nhật Trạng Thái</button>
			</form>

		</tbody>
	</table>

	<div class="butttonReturn">
		<a class="back-link" href="bill">Quay lại danh sách hóa đơn</a>

	</div>
	
	
</body>
</html>
