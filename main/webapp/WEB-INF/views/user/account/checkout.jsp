<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<%@include file="/WEB-INF/views/layouts/User/sidebar.jsp" %>
		<div class="span9">
			<ul class="breadcrumb">
				<li><a href="index.html">Trang chủ</a> <span class="divider">/</span></li>
				<li class="active">Thông tin cá nhân</li>
			</ul>

			<hr class="soft" />
			<div class="well">
			<form:form method="post" action="checkout" modelAttribute="bills">
					<h3>Thoong tin thanh toan</h3>
					<div class="control-group">
						<label class="control-label">Email <sup>*</sup></label>
						<div class="controls">
							<form:input path="user" placeholder=" Xin mời nhập"/>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label">Họ và Tên <sup>*</sup></label>
						<div class="controls">
							<form:input path="display_name" placeholder=" Xin mời nhập"/>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label">Số điện thoại <sup>*</sup></label>
						<div class="controls">
							<form:input path="phone" placeholder=" Xin mời nhập"/>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label">Địa chỉ <sup>*</sup></label>
						<div class="controls">
							<form:input path="address" placeholder=" Xin mời nhập"/>
							<form:hidden path="status"/>
						</div>
					</div>
					
					</div>
					<div class="control-group">
						<label class="control-label">Ghi chú <sup>*</sup></label>
						<div class="controls">
							<form:input  path="note" placeholder=" Xin mời nhập"/>
						</div>
					</div>

					<div class="control-group">
						<div class="controls">
							<input type="submit" name="submitAccount" value="Thanh toán"
								class="shopBtn exclusive">
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- 
Clients 
-->
</body>
</html>