<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

	<div id="sidebar" class="span3">
				<div class="well well-small">
					<ul class="nav nav-list">
					<!-- phan danh muc -->
						<c:forEach var="item" items="${categorys }">
							<li><a href="<c:url value='/san-pham/${item.id }'/>">
							<span class="icon-chevron-right"></span>${item.name }</a></li>
									
						</c:forEach>			
						<li style="border: 0">&nbsp;</li>					
						
						
						<c:choose>
					        <c:when test="${sessionScope.TotalSalePrice != null && sessionScope.TotalSalePrice > 0}">
					        <li><a class="totalInCart" href="<c:url value='/cart}'/>">
					            <strong>Tổng tiền: 
								 <span class="badge badge-warning pull-right"
								style="line-height: 18px;">
								<fmt:formatNumber value="${sessionScope.TotalSalePrice}" type="number" pattern="#,##0" /> VNĐ
								</span>
								</strong></a>
							</li>					
					        </c:when>
					    </c:choose>
						
						
						
					</ul>
					
					
				</div>

				<div class="well well-small alert alert-warning cntr">
					<h2>Sale 30%</h2>
					<p>
						Áp dụng khi tới shop <br>
						<br>
						<a class="defaultBtn" href="#">Bấm vào đây </a>
					</p>
				</div>
				<div class="well well-small">
					<a href="#"><img src="assets/img/paypal.jpg"
						alt="payment method paypal"></a>
				</div>

				<a class="shopBtn btn-block" href="#">Sản phẩm nổi bật <br>
				<small>Click to view</small></a> <br> <br>
				<ul class="nav nav-list promowrapper">
					 <c:forEach var="itemHL" items="${ProductHighlight}">
					<li>
						<div class="thumbnail">
							<a class="zoomTool" href="<c:url value='/product-detail/${itemHL.id_product }'/>"
								title="Xem chi tiết"><span class="icon-search"></span> 
							Xem thêm</a> 

								<a href="<c:url value='/product-detail/${itemHL.id_product }'/>">	
										<c:set var="imageList" value="${fn:split(itemHL.img, ',')}" />			
										<c:if test="${not empty imageList}">
										    <img src="<c:url value='/assets/user/img-product/${imageList[0]}'/>" alt="First Image" />
										</c:if>
									</a>
								
							<div class="caption">
								<h4 style="text-align: center;"> ${ itemHL.name }</h4>
							
							    <c:set var="finalPrice" value="${itemHL.price - (itemHL.price * (itemHL.sale / 100))}" />
							    <h4 style="color: red; text-align: center;">
							        <fmt:formatNumber value="${finalPrice >= 0 ? finalPrice : 0}" type="number" pattern="#,##0" /> VNĐ
							    </h4>
							    <h5>Giá gốc:
							        <span style="text-decoration: line-through;">
							            <fmt:formatNumber value="${itemHL.price}" type="number" pattern="#,##0" />₫
							        </span>
							    </h5>
							</div>

						</div>
					</li>
					
					</c:forEach>
					<li style="border: 0">&nbsp;</li>
					
				</ul>

			</div>