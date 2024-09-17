<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>

	<c:forEach var="contact" items="${contact}">
		<div class="container22">
			<h1>Thông Tin Liên Hệ</h1>
			<div class="contact-info">
				<div class="form-group">
					<label>ID:</label> <span>${contact.id}</span>
				</div>
				<div class="form-group">
					<label>Nội Dung:</label> <span>${contact.content}</span>
				</div>
				<div class="form-group">
					<label>Hotline Văn Phòng:</label> <span>${contact.office_Hotline}</span>
				</div>
				<div class="form-group">
					<label>Hotline Chăm Sóc Khách Hàng:</label> <span>${contact.customer_care_Hotline}</span>
				</div>
				<div class="form-group">
					<label>Hotline Mua Hàng:</label> <span>${contact.purchase_Hotline}</span>
				</div>
				<div class="form-group">
					<label>Địa Chỉ:</label> <span>${contact.address}</span>
				</div>
				<div class="form-group">
					<label>Email:</label> <span>${contact.email}</span>
				</div>
				<div class="form-group">
					<a class="master-buttonedit"
						href="<c:url value='/admin/management-contact/edit/${contact.id }'/>">Sửa liên hệ</a>
				</div>
			</div>
		</div>
	</c:forEach>

</body>
</html>
