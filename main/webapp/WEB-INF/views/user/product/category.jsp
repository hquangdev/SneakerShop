<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  


<body>
<div class="well well-small">

	<div class="row">
		<span style="margin-left: 25px;">	Danh sách sản phẩm</span>
		<select class="pull-right">
			<option >A - Z</option>
			<option >Cao - thấp</option>
		</select>
	</div>

		<c:if test="${ProductsPaginate.size() > 0 }">
						<div class="row-fluid">
		  					<ul class="thumbnails">
						 <c:forEach var="item" items="${ProductsPaginate}">
						 	<li class="span4">
							  <div class="thumbnail">
								<a href="product_details.html" class="overlay"></a>
								
								<a class="zoomTool" href="<c:url value='/product-detail/${item.id_product }'/>" title="sale: ${item.sale } %">
								<span class="icon-search"></span> Xem them</a>
								  	<a href="<c:url value='/product-detail/${item.id_product }'/>">	
										<c:set var="imageList" value="${fn:split(item.img, ',')}" />
							            <c:if test="${not empty imageList}">
							                <!-- Hiển thị hình ảnh đầu tiên trong danh sách -->
							                <img src="<c:url value='/assets/user/img-product/${fn:trim(imageList[0])}'/>" />
							            </c:if>

									</a>	
								
								<div class="caption cntr">
									<h4>${item.name }</h4> 
									
									<c:set var="finalPrice" value="${item.price - (item.price * (item.sale / 100))}" />

								<div class="actionList actionListCT">
								
									<!-- Hiển thị giá gốc với định dạng số và gạch ngang -->
								    <p class="pull-left" style="color: red;">
								        <span style="text-decoration: line-through;">
								            <fmt:formatNumber value="${item.price}" type="number" pattern="#,##0" /> VNĐ
								        </span>
								    </p>
								    
								    <!-- Hiển thị giá đã giảm với định dạng số -->
								    <p class="pull-left">
								        <fmt:formatNumber value="${finalPrice >= 0 ? finalPrice : 0}" type="number" pattern="#,##0" /> VNĐ
								    </p> 
								
								    
								</div>
									<br class="clr">
								</div>
							  </div>
							</li>
						 
						 	<c:if test="${(loop.index + 1) % 3 == 0 || (loop.index + 1) == ProductsPaginate.size()}">
						 		 </ul>
								</div>
								
						 		<c:if test="${ (loop.index + 1) < ProductsPaginate.size() }">
						 			 <div class="row-fluid">
		  					<ul class="thumbnails">
						 		</c:if>
						 	</c:if>
						 	
						 </c:forEach>
					</c:if>
						
	</div>
	
	<div class="pagination">
		<c:forEach var="item" begin="1" end = "${paginateInfo.totalPage }" varStatus="loop">
			<c:if test="${ (loop.index) == paginateInfo.currentPage }">
				<a href="<c:url value='/san-pham/${idCategory }/${loop.index }'/>" class="active">${loop.index }</a>
			</c:if>
			
			<c:if test="${ (loop.index) != paginateInfo.currentPage }">
				<a href="<c:url value='/san-pham/${idCategory }/${loop.index }'/>">${loop.index }</a>
			</c:if>
		
		</c:forEach>
	
	</div>

</body>
</html>