<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<body>

	<h2 class="master-title" >Quản lý danh mục</h2>
	
	<div class="add-category">
		<form action="add-category" method="post">
			<input type="text" name="name" placeholder="Nhập tên danh mục">
			<input type="text" name="description" placeholder="Nhập nội dung">
			<input type="submit" value="Thêm danh mục">
		</form>
	</div>
	
	<div class="master-list">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Tên danh mục</th>
					<th>Nội dung</th>
					<th colspan="2">Tác vụ</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${category }">
				<tr>
					<td>${item.id }</td>
					<td>${item.name }</td>
					<td>${item.description }</td>
					<td><a class="master-buttonedit" href="<c:url value='/admin/manager-category/edit/${item.id }'/>">Sửa</a></td>
					
					<td><a class="master-buttondelete" href="<c:url value='/admin/manager-category/delete/${item.id }'/>">Xóa</a></td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
	</div>
	
	<jsp:include page="/WEB-INF/views/admin/pagination/master.jsp">
	    <jsp:param name="baseUrl" value="/manager-category" />
	    <jsp:param name="currentPage" value="${currentPage}" />
	    <jsp:param name="totalPages" value="${totalPages}" />
	    <jsp:param name="pageSize" value="${pageSize}" />
	</jsp:include>

</body>
</html>