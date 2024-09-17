<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<body>

<h1>Chi Tiết Sản Phẩm</h1>

<c:if test="${not empty error}">
    <div class="alert error">${error}</div>
</c:if>

<div class="master-list">
    <c:if test="${not empty productDTO}">
        <div class="product-detail">
            <h2 class="master-title">${productDTO.name}</h2>
            
            <!-- Display multiple images -->
            <c:if test="${not empty productDTO.img}">
                <div class="product-images">
                    <c:forEach var="image" items="${productDTO.img}">
                        <img width="200px;" src="<c:url value='/assets/user/img-product/${image }'/> " />
                    </c:forEach>
                </div>
            </c:if>

            <p><strong>Giá: </strong><fmt:formatNumber value="${productDTO.price}" type="number" pattern="#,##0" />₫</p>
            <p><strong>Loại sản phẩm: </strong> 
                <c:choose>
                    <c:when test="${productDTO.highlight == 1}">Nổi bật</c:when>
                    <c:when test="${productDTO.new_product == 1}">Sản phẩm mới</c:when>
                    <c:otherwise>Sản phẩm thường</c:otherwise>
                </c:choose>
            </p>
            <p><strong>Giảm giá:</strong> ${productDTO.sale}%</p>
            <p><strong>Chi tiết:</strong> ${productDTO.details}</p>

            <h3>Size và Số lượng:</h3>

            <!-- Display sizes and their quantities -->
            <c:forEach var="size" items="${productDTO.productsize}" varStatus="status">
                <p>Size: ${size.sizes}, Số lượng: ${size.quantity}</p>
            </c:forEach>
        </div>
    </c:if>
	
    <!-- Kiểm tra nếu productDTO có id_category -->
		<c:if test="${not empty productDTO.id_category}">
		    <h3>Danh mục sản phẩm</h3>
		    <ul>
		        <!-- Lặp qua tất cả các danh mục -->
		        <c:forEach var="category" items="${categories}">
		            <!-- Kiểm tra nếu id của danh mục trùng với id_category của sản phẩm -->
		            <c:if test="${category.id == productDTO.id_category}">
		                <li>${category.name}</li> <!-- Hiển thị tên danh mục -->
		            </c:if>
		        </c:forEach>
		    </ul>
		</c:if>

</div>
</body>
</html>
