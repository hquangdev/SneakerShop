<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<body>
	<!-- 
Body Section 
-->
	<div class="row product-content">
	
		<%@include file="/WEB-INF/views/layouts/User/sidebar.jsp" %>
		
		<div class="span9">
			<ul class="breadcrumb">
				<li><a href="<c:url value='/home-page'/>">Trang chủ</a> <span class="divider">/</span></li>
				<li><a href="#">Sản phẩm</a> <span class="divider">/</span></li>
				<li class="active">Chi tiết sản phẩm</li>
			</ul>
			<div class="well well-small">
				<div class="row-fluid">
					<div class="span5">
						<div id="myCarousel" class="carousel slide cntr" data-ride="carousel">
						    <!-- Carousel Indicators -->
							    <ol class="carousel-indicators">
							        <c:forEach var="image" items="${fn:split(productDTO.img, ',')}" varStatus="loop">
							            <li data-target="#myCarousel" data-slide-to="${loop.index}" class="${loop.index == 0 ? 'active' : ''}"></li>
							        </c:forEach>
							    </ol>
							
							    <!-- Carousel Items -->
							    <div class="carousel-inner">
							        <c:forEach var="image" items="${fn:split(productDTO.img, ',')}" varStatus="loop">
							            <div class="item ${loop.index == 0 ? 'active' : ''}">
							                <a href="#">
							                    <img src="<c:url value='/assets/user/img-product/${fn:trim(image)}'/>" alt="Slide Image" style="width: 100%;" />
							                </a>
							            </div>
							        </c:forEach>
							    </div>

							    <!-- Carousel Controls -->
							    <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
							    <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
						</div>
					</div>
					<div class="span7">
						<h3 style="text-align: center;">${productDTO.name}</h3>
						<hr class="soft" />

						<form action="<c:url value='/AddCart/${productDTO.id_product }'/>" method="post" class="form-horizontal qtyFrm">
						    <div class="control-group">
						        <c:set var="finalPrice" value="${productDTO.price - (productDTO.price * (productDTO.sale / 100))}" />
						        <label class="control-label" style="text-decoration: line-through;">
						            <span>
						                <fmt:formatNumber value="${productDTO.price}" type="number" pattern="#,##0" />₫
						            </span>
						        </label>
						        <label class="control-label" style="color: red;">
						            Giá sale
						            <span>
						                <fmt:formatNumber value="${finalPrice >= 0 ? finalPrice : 0}" type="number" pattern="#,##0" />
						            </span>₫
						        </label>
						    </div>
						
						   <div class="control-group">
							    <label class="control-label"><span>Chọn số lượng</span></label>
							    <div class="controls">
							        <input type="number" id="quantityInput" name="quantity" min="1" placeholder="Chọn số lượng" 
							        required style="width: 178px;">
							    </div>
							</div>
							
							<div class="control-group">
							    <label class="control-label"><span>Số lượng giày</span></label>
							    <div class="controls">
							        <input type="number" id="stockQuantityInput" style="width: 178px;" readonly>
							        <p id="stockStatus"></p>
							    </div>
							</div>

						
						    <div class="control-group">
						        <label class="control-label"><span>Chọn kích cỡ</span></label>
						        <div class="controls">
						            <select name="size" id="sizeSelect" class="span11">
						                <c:forEach var="size" items="${productDTO.productsize}">
						                    <option value="${size.sizes}">${size.sizes}</option>
						                </c:forEach>
						            </select>
						        </div>
						    </div>
						
						    <h4>${productDTO.title }</h4>
						    <p>$ }<p>
						
						    <button type="submit" id="addToCartBtn" class="shopBtn">
						        <span class="icon-shopping-cart"></span> Thêm vào giỏ hàng
						    </button>
						</form>

					</div>
				</div>
				<hr class="softn clr" />


				<ul id="productDetail" class="nav nav-tabs">
					<li class="active">
						<a href="#home" data-toggle="tab">Chi tiết sản phẩm </a>
					</li>
					<li class=""><a href="#profile" data-toggle="tab">Sản phẩm liên quan
							 </a></li>
			
				</ul>
				<div id="myTabContent" class="tab-content tabWrapper">
					<div class="tab-pane fade active in" id="home">
						<h4>${productDTO.title }</h4>
						
						<p>${productDTO.details }</p>
				
					</div>
					<div class="tab-pane fade" id="profile">
					
					<c:forEach var="itemLQ" items="${productByIDCategory }">
					
						<div class="row-fluid">
									
							<div class="span2">
								<c:set var="imageList" value="${fn:split(itemLQ.img, ',')}" />	
								<c:if test="${not empty imageList}">
									<img src="<c:url value='/assets/user/img-product/${imageList[0]}'/>"/>
								</c:if>
							</div>
							<div class="span6">
								<h5>${itemLQ.name }</h5>
								<p>${itemLQ.details }</p>
							</div>
							<div class="span4 alignR">
								<form class="form-horizontal qtyFrm">
									<h3>$140.00</h3>
									
									<div class="btn-group">
										<a href="<c:url value='/Addcart/${itemLQ.id_product }'/>" class="defaultBtn"><span
											class=" icon-shopping-cart"></span> Thêm vào giỏ hàng</a> 
										<a href="<c:url value='/product-detail/${itemLQ.id_product }'/>" class="shopBtn">VIEW</a>
											
									</div>
								</form>
							</div>
						</div>
					</c:forEach>
						<hr class="soft">
						
					</div>
					
			
				</div>

			</div>
		</div>
	</div>
	
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const sizeSelect = document.getElementById('sizeSelect');
        const stockQuantityInput = document.getElementById('stockQuantityInput');
        const quantityInput = document.getElementById('quantityInput');
        const stockStatus = document.getElementById('stockStatus');
        const addToCartBtn = document.getElementById('addToCartBtn');

        // Dữ liệu từ JSP
        const sizeQuantities = {
            <c:forEach var="size" items="${productDTO.productsize}">
                '${size.sizes}': ${size.quantity},
            </c:forEach>
        };

        function updateQuantity() {
            const selectedSize = sizeSelect.value;
            const quantity = sizeQuantities[selectedSize] || 0;

            if (quantity > 0) {
                stockQuantityInput.value = quantity;
                stockStatus.textContent = '';
                quantityInput.removeAttribute('disabled'); // Bỏ thuộc tính disabled
                quantityInput.setAttribute('placeholder', 'Chọn số lượng'); // Đặt lại placeholder
                addToCartBtn.disabled = false; // Kích hoạt nút "Thêm vào giỏ hàng"
                addToCartBtn.textContent = 'Thêm vào giỏ hàng'; // Đặt lại văn bản của nút
            } else {
                stockQuantityInput.value = '';
                stockStatus.textContent = 'Hết hàng';
                stockStatus.style.color = 'red';
                quantityInput.setAttribute('disabled', 'true'); // Thêm thuộc tính disabled
                quantityInput.setAttribute('placeholder', 'Hết hàng'); // Đặt placeholder cho hết hàng
                addToCartBtn.disabled = true; // Cấm nút "Thêm vào giỏ hàng"
                addToCartBtn.textContent = 'Hết hàng'; // Đổi văn bản của nút để thông báo hết hàng
            }
        }

        sizeSelect.addEventListener('change', updateQuantity);

        // Cập nhật thông tin ngay khi trang được tải
        updateQuantity();
    });
</script>

</body>
</html>