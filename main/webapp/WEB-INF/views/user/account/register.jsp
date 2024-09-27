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
			<h3>Thông tin cá nhân của bạn</h3>
			<hr class="soft" />
			<div class="well">
				<form class="form-horizontal" action="registe" method="post">
					<h3>Nhập thông tin cá nhân của bạn</h3>
					<div class="control-group"></div>
					<div class="control-group">
						<label class="control-label" for="inputFname">Họ và tên<sup>*</sup></label>
						<div class="controls">
							<input type="text" id="display_name" name="display_name" placeholder="Xin mời nhập">
						</div>
					</div>
			
					<div class="control-group">
						<label class="control-label" for="email">Email <sup>*</sup></label>
						<div class="controls">
							<input type="text" name="email" placeholder="Email">
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="password">Mật khẩu <sup>*</sup></label>
						<div class="controls">
							<input type="password" id="password" name="password" placeholder="Xin mời nhập">
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label">Địa chỉ<sup>*</sup></label>
						<div class="controls">
							<input type="text" name="address" placeholder="Xin mời nhập">
						</div>
					</div>
					
					<div class="control-group">
						<div class="controls">
							<input type="submit" name="submitAccount" value="Đăng kí"
								class="exclusive shopBtn">
						</div>
					</div>
				</form>
			</div>

			<div class="well">
			<c:if test="${not empty statuslogin }">${statuslogin }</c:if>
			<form:form method="post" action="Login-account" modelAttribute="user">
					<h3>Đăng nhập tài khoản</h3>
					<div class="control-group">
						<label class="control-label">Email <sup>*</sup></label>
						<div class="controls">
							<form:input path="email" placeholder=" Xin mời nhập"/>
							<form:hidden path="id"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Mật khẩu <sup>*</sup></label>
						<div class="controls">
							<form:password  path="password" placeholder=" Xin mời nhập"/>
						</div>
					</div>

					<div class="control-group">
						<div class="controls">
							<input type="submit" name="submitAccount" value="Đăng nhập"
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