<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="row">
		<%@include file="/WEB-INF/views/layouts/User/sidebar.jsp" %>
		
			<div class="span9">
				<div class="well np">
					<div id="myCarousel" class="carousel slide homCar">
					<!-- phần lặp slide -->
						<div class="carousel-inner">
						
							<c:forEach var="item" items="${slides}" varStatus="index">
							
									<c:if test="${index.first}">   
										<div class="item active">
									</c:if>
									
									<c:if test="${not index.first}">   
										<div class="item">
									</c:if>
									
										<img style="width: 100%" src="<c:url value='assets/user/Slide-img/${item.img}'/>"
											alt="bootstrap ecommerce templates">
											
										<div class="carousel-caption">
											<h4>${item.caption }</h4>
											<p>
												<span>${item.content}</span>
											</p>
										</div>
									</div>
							</c:forEach>
						</div>
						
						<a class="left carousel-control" href="#myCarousel"
							data-slide="prev">&lsaquo;</a> <a class="right carousel-control"
							href="#myCarousel" data-slide="next">&rsaquo;</a>
					</div>
				</div>
				<!--New Products -->

				<div class="well well-small">
					<h3>Sản phẩm mới ra mắt</h3>
					<hr class="soften" />
					<div class="row-fluid">
						<div id="newProductCar" class="carousel slide">
							<div class="carousel-inner">
							
								<div class="item ">
								<!-- Lặp sản phẩm mới -->
									<ul class="thumbnails">
									    <c:forEach var="product" items="${ProductNew}">
									        <li class="span3">
									            <div class="thumbnail">
									                <a class="zoomTool" href="product_details.html" title="add to cart">
									                    <span class="icon-search"></span> Xem thêm
									                </a>
									                <a href="#" class="tag"></a>
									                <a href="<c:url value='/product-detail/${product.id_product }'/>">
														<c:set var="imageList" value="${fn:split(product.img, ',')}" />			
														<c:if test="${not empty imageList}">
														    <img src="<c:url value='assets/user/img-product/${imageList[0]}'/>" alt="First Image" />
														</c:if>
													 </a>						               
									            </div>
									        </li>
									    </c:forEach>
									</ul>

								</div>
			
							</div>
							<a class="left carousel-control" href="#newProductCar"
								data-slide="prev">&lsaquo;</a> <a class="right carousel-control"
								href="#newProductCar" data-slide="next">&rsaquo;</a>
						</div>
					</div>
			
				</div>
				<!--Sản phẩm thường-->
	
	
				<div class="well well-small">
					<h3>
						Sản phẩm
					</h3>
					<hr class="soften" />
					<div class="row-fluid">
					
					<c:if test="${Products.size() > 0 }">
						<ul class="thumbnails">
						
						 <c:forEach var="itemPr" items="${Products}">
						 	<li class="span4">
						 		<div class="thumbnail">
							 		<a class="zoomTool" href="<c:url value='/product-detail/${itemPr.id_product }'/>"
							 		 title="Sản phẩm chi tiết">
								 		<span class="icon-search"></span> Xem thêm
								 		<p>  Sale: ${itemPr.sale }</p>
							 		</a> 
						 		
									<a href="<c:url value='/product-detail/${itemPr.id_product }'/>">	
										<c:set var="imageList" value="${fn:split(itemPr.img, ',')}" />			
										<c:if test="${not empty imageList}">
										    <img src="<c:url value='assets/user/img-product/${imageList[0]}'/>" alt="First Image" />
										</c:if>
									</a>
										
									<div class="caption">
										<h4 style="text-align: center;">${itemPr.name }</h4>
										
										 <c:set var="finalPrice" value="${itemPr.price - (itemPr.price * (itemPr.sale / 100))}" />
                    
					                    <h4 style="color: red; text-align: center;">
					                    	<fmt:formatNumber value="${finalPrice >= 0 ? finalPrice : 0}" type="number" pattern="#,##0" /> VNĐ
					                    </h4>					                    
					                    <h5>Giá gốc: 
					                        <span style="text-decoration: line-through; "> 
					                            <fmt:formatNumber value="${itemPr.price}" type="number" pattern="#,##0" />₫
					                        </span>
					                    </h5>
                    
                    
									</div>
								</div>
							</li>
						 
						 	<c:if test="${(loop.index + 1) % 3 == 0 || (loop.index + 1) == Products.size()}">
						 		</ul>
						 		<c:if test="${ (loop.index + 1) < Products.size() }">
						 			<ul class="thumbnails">
						 			
						 		</c:if>
						 	</c:if>
						 	
						 </c:forEach>
					</c:if>
						
					</div>
				</div>


			</div>
		</div>
		<!-- 
Clients 
-->