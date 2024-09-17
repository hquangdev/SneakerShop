<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<header id="header">
			<div class="row">
				<div class="span4">
					<h1>
						<a class="logo" href="<c:url value='home-page'/>"><span></span>
								 
							<img src="<c:url value='assets/user/img/logo-bootstrap-shoping-cart.png'/>" alt=" shop"> </a>
							
					</h1>
				</div>
				<div class="span4">
					<div class="offerNoteWrapper">
						<h1 class="dotmark">
							<i class="icon-cut"></i>Demo - Hoàng Quang
						</h1>
					</div>
				</div>
				<div class="span4 alignR">
					<p>
						<br> <strong> Support (24/7) : 0964889723</strong><br>
						<br>
					</p>
					<span class="btn btn-mini">[ 2 ] <span
						class="icon-shopping-cart"></span></span> <span
						class="btn btn-warning btn-mini">$</span> <span
						class="btn btn-mini">&pound;</span> <span class="btn btn-mini">&euro;</span>
				</div>
			</div>
		</header>

		<!--
Navigation Bar Section 
-->
		<div class="navbar">
			<div class="navbar-inner">
				<div class="container">
					<a data-target=".nav-collapse" data-toggle="collapse"
						class="btn btn-navbar"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
					</a>
					<div class="nav-collapse">
						<ul class="nav">
							<li class="active"><a  href="<c:url value='/home-page'/>">Home </a></li>
							<li class=""><a href="<c:url value='/cart'/>">Giỏ hàng</a></li>
							<li class=""><a href="<c:url value='/contact'/>">Liên hệ</a></li>
							<li class=""><a href="#">Bài viết</a></li>
						</ul>
						<form action="search" method="post" class="navbar-search pull-left">
							<input type="text" name="query" placeholder="Tìm kiếm" class="search-query span2" >
							<input type="submit" value="Tìm"> <br>
							<c:if test="${not empty statusMessage}">
							    <p>${statusMessage}</p>
							</c:if>

						</form>
						<ul class="nav pull-right">
						<c:if test="${ empty LoginInfo }">
							<li class="dropdown"><a data-toggle="dropdown"
								class="dropdown-toggle" href="#"><span class="icon-lock"></span>
									Đăng nhập <b class="caret"></b></a>
								<div class="dropdown-menu">
								
									<form class="form-horizontal loginFrm" action="Login-account" method="post">
										<div class="control-group">
											<input type="text" class="span2" id="inputEmail" name="email"
												placeholder="Email">
										</div>
										<div class="control-group">
											<input type="password" class="span2" id="inputPassword" name="password"
												placeholder="Password">
										</div>
										<div class="control-group">
											<label class="checkbox"> <input type="checkbox">
												Nhớ mật khẩu
											</label>
											
											<button type="submit" class="shopBtn btn-block">Đăng nhập</button>
												
										</div>
									</form>
								</div></li>
							</c:if>
								
								<c:if test="${not empty LoginInfo }">
									<li><a href="#">${LoginInfo.display_name }</a>
								</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>