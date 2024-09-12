<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<body>

	<h2 class="master-title" >Quản lý sản phẩm</h2>
	
	<div class="button-add">
		<a href="<c:url value='add-product'/>">Thêm sản phẩm</a>
	</div>
	
	<div class="master-title">
        <c:if test="${not empty message}">
            <div class="alert">${message}</div>
        </c:if>
        <c:if test="${not empty error}">        <!-- Hiển thị thông báo lỗi -->
            <div class="alert error">${error}</div>
        </c:if>
    </div>
	
	<div class="master-list">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Hình ảnh</th>
					<th>sản phẩm</th>
					<th>Danh mục</th>
					<th>Tiêu đề</th>
					<th>Loại sản phẩm</th>
					<th>Giá</th>
					<th colspan="3">Tác vụ</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${productDto }">
				<tr>
					<td>${item.id_product }</td>
					<td>
						<c:set var="imageList" value="${fn:split(item.img, ',')}" />			
						<c:if test="${not empty imageList}">
							<img width="200px" src="<c:url value='/assets/user/img-product/${imageList[0]}'/>" />
						</c:if>
					</td>								
					<td>${item.name }</td>
					<td>
			            <c:forEach var="category" items="${categories}">
			                <c:if test="${category.id == item.id_category}">
			                    ${category.name}
			                </c:if>
			            </c:forEach>
			        </td>
			        
			        <td>${item.title }</td>
			        <td>
					    <c:choose>
					        <c:when test="${item.highlight == 1}">
					            Sản phẩm nổi bật
					        </c:when>
					        <c:when test="${item.new_product == 0}">
					            Sản phẩm mới
					        </c:when>
					        <c:otherwise>
					            Sản phẩm thường
					        </c:otherwise>
					    </c:choose>
					</td>
					
					<td><fmt:formatNumber value="${item.price}" type="number" pattern="#,##0" />₫</td>
					<td>
						<a class="master-buttonedit" href="<c:url value='/admin/manager-product/product-detail/${item.id_product }'/>">Xem</a>
						
						<a class="master-buttonedit" href="<c:url value='/admin/manager-product/edit/${item.id_product }'/>">Sửa</a>
						
						<a class="master-buttondelete" href="<c:url value='/admin/manager-product/delete/${item.id_product }'/>">Xóa</a>
					</td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
	</div>
	
	<jsp:include page="/WEB-INF/views/admin/pagination/master.jsp">
	    <jsp:param name="baseUrl" value="/manager-products" />
	    <jsp:param name="currentPage" value="${currentPage}" />
	    <jsp:param name="totalPages" value="${totalPages}" />
	    <jsp:param name="pageSize" value="${pageSize}" />
	</jsp:include>

</body>
</html>