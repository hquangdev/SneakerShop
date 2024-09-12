<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Trang chủ</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href="<c:url value="/assets/admin/cssSneaker/style.css" />" rel="stylesheet" />

<!-- Bootstrap styles -->
<link href="<c:url value="/assets/user/css/bootstrap.css" />" rel="stylesheet" />
<!-- Customize styles -->
<link href="<c:url value="/assets/user/style.css" />" rel="stylesheet" />
<!-- font awesome styles -->
<link href="<c:url value="/assets/user/font-awesome/css/font-awesome.css" />" rel="stylesheet">

<!-- Favicons -->
<link rel="shortcut icon" href="<c:url value="/assets/user/ico/favicon.ico" />">
</head>
<body>
	<!-- 
	Upper Header Section 
-->
	<div class="navbar navbar-inverse navbar-fixed-top">
	
		<div class="topNav">
			<div class="container">
				<div class="alignR">
					<div class="pull-left socialNw">
						<a href="#"><span class="icon-twitter"></span></a> <a href="#"><span
							class="icon-facebook"></span></a> <a href="#"><span
							class="icon-youtube"></span></a> <a href="#"><span
							class="icon-tumblr"></span></a>
					</div>
					<a class="active" href="<c:url value='/home-page'/>"> <span class="icon-home"></span>
						Trang chủ
					</a>
					
					<c:choose>
					    <c:when test="${not empty sessionScope.LoginInfo}">
					        <!-- Hiển thị tên người dùng khi đã đăng nhập -->
					        <a href="#"><span class="icon-user"></span> 
					            <c:out value="${LoginInfo.display_name }" />
					        </a>
					        <a href='<c:url value="/log-out"/>'><span class="icon-logout"></span> Đăng xuất</a>
					    </c:when>
					    <c:otherwise>
					        <!-- Hiển thị "Tài khoản" và "Free Register" khi chưa đăng nhập -->
					        <a href='<c:url value="/registe"/>'><span class="icon-user"></span> Tài khoản</a>
					    </c:otherwise>
					</c:choose>
						
					<a href='<c:url value="/cart"/>'>
					    <span class="icon-shopping-cart"></span>
					    <c:choose>
					        <c:when test="${sessionScope.TotalSalePrice != null && sessionScope.TotalQuantyCart > 0}">
					            ${sessionScope.TotalQuantyCart} Item(s) - 
					            
					            <fmt:formatNumber value="${sessionScope.TotalSalePrice}" type="number" pattern="#,##0" /> ₫
					        </c:when>
					        
					        <c:otherwise>
					            0 Item(s) - <span class="badge badge-warning">0 ₫</span>
					        </c:otherwise>
					    </c:choose>
					</a>
					
				</div>
			</div>
		</div>
	</div>

	<!--
Lower Header Section 
-->
	<div class="container">
		<div id="gototop"></div>
		
		<!-- Body Section -->	

		<%@include file="/WEB-INF/views/layouts/User/header.jsp" %>
		
		<decorator:body/>
		
		<%@include file="/WEB-INF/views/layouts/User/footer.jsp" %>
		
		
	</div>
	<!-- /container -->

	<div class="copyright">
		<div class="container">
			<p class="pull-right">
				<a href="#"><img src="assets/img/maestro.png" alt="payment"></a>
				<a href="#"><img src="assets/img/mc.png" alt="payment"></a> <a
					href="#"><img src="assets/img/pp.png" alt="payment"></a> <a
					href="#"><img src="assets/img/visa.png" alt="payment"></a> <a
					href="#"><img src="assets/img/disc.png" alt="payment"></a>
			</p>
			<span>SneakerShop &copy; 2024<br> Hoàng Quang
			</span>
		</div>
	</div>
	<a href="#" class="gotop"><i class="icon-double-angle-up"></i></a>
	
	<!-- Placed at the end of the document so the pages load faster -->
	<script src=" <c:url value="/assets/user/js/jquery.js" />"></script>
	<script src="<c:url value="/assets/user/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/assets/user/js/jquery.easing-1.3.min.js" />"></script>
	<script src="<c:url value="/assets/user/js/jquery.scrollTo-1.4.3.1-min.js" />"></script>
	<script src="<c:url value="/assets/user/js/shop.js" /> "></script>
</body>
</html>