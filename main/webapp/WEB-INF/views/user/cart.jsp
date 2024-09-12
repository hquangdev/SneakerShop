<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<body>
<div class="row">
	<div class="span12">
		<ul class="breadcrumb">
			<li><a  href="<c:url value='/home-page'/>">Trang chủ</a> <span class="divider">/</span></li>
			<li class="active">Giỏ hàng</li>
		</ul>
		<div class="well well-small">
			<h2>
				Giỏ hàng <small class="pull-right"> ${sessionScope.TotalQuantyCart } sản phẩm </small>
			</h2>
			<hr class="soften" />

			<table class="table table-bordered table-condensed">
				<thead>
					<tr>
						<th>Hình ảnh</th>
						<th>Sản phẩm</th>
						<th>Kích thước(size)</th>
						<th>Số lượng</th>
						<th>Giá</th>
						<th>Tác vụ</th>
					</tr>
				</thead>
				<tbody>
				
				<c:if test="${not empty  cart }">
				<c:forEach var="item" items="${cart }">
					<tr>
						<td>
						<c:set var="imageList" value="${fn:split(item.value.product.img, ',')}" />			
							<c:if test="${not empty imageList}">
								<img src="<c:url value='/assets/user/img-product/${imageList[0]}'/>" width="100px;" />
							</c:if>
						</td>
						<td>${item.value.product.name }</td>

						<td>${item.value.size }</td>

						<td>
						    <input class="span1" style="max-width: 34px" min="0"
						       placeholder="1" id="quanty-cart-${item.key}" size="16" type="number"
						       value="${item.value.quanty }">
						
						</td>

						<c:set var="finalPrice" value="${item.value.product.price - (item.value.product.price * (item.value.product.sale / 100))}" />	
						<td><fmt:formatNumber value="${finalPrice >= 0 ? finalPrice : 0}" type="number" pattern="#,##0" /> ₫</td>

						<td>
							<div class="input-append">
							
								<button data-id="${item.key}" class="btn btn-mini btn-danger edit-cart">
								    <span class="icon-edit"></span>
								</button>

								
								<a href="<c:url value='/DeleteCart/${item.key }'/>" class="btn btn-mini btn-danger">
									<span class="icon-remove"></span>
								</a>
							</div>
						</td>
						
						
					</tr>
				</c:forEach>
				</c:if>
				</tbody>
			</table>
			
			<h4> Tổng tiền:
				<fmt:formatNumber value="${sessionScope.TotalSalePrice}" type="number" pattern="#,##0" /> ₫
			</h4>
			<br /> 
			<a href="<c:url value='/home-page'/>" class="shopBtn btn-large">
			<span class="icon-arrow-left"></span> Quay lại Shop </a> 
			<a href="<c:url value='/checkout'/>" class="shopBtn btn-large pull-right">Thanh toán <span class="icon-arrow-right"></span></a>
				
				

		</div>
	</div>
</div>

<!-- Đảm bảo script nằm trong phần tử <script> tiêu chuẩn -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- Đảm bảo jQuery đã được load -->
<script>
    $(document).ready(function() {
        $(".edit-cart").on("click", function() {
            var id = $(this).data("id"); // Lấy id từ data-id của nút nhấn
            // Sửa lại selector để bắt đúng giá trị từ input với id tương ứng
            var quanty = $("#quanty-cart-" + id).val(); 
            window.location.href = "EditCart/" + id + "/" + quanty;
        });
    });
</script>



</body>
