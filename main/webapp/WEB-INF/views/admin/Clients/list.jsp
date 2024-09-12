<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<body>

	<h2 class="master-title" >Quản lý khách hàng</h2>
	
	<div class="master-list">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Họ và tên</th>
					<th>E-mail</th>
					<th>Địa chỉ</th>
					<th>Ngày tạo</th>
					<th>Ngày cập nhật</th>
					<th colspan="2">Tác vụ</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${clients }">
				<tr>
					<td>${item.id }</td>
					<td>${item.display_name }</td>
					<td>${item.email }</td>
					<td>${item.address }</td>
					<td>${item.created_at }</td>
					<td>${item.updated_at }</td>
					<td>
						<a class="master-buttonedit" href="<c:url value='/admin/manager-client/edit/${item.id }'/>">Sửa</a>
						<a class="master-buttondelete" href="<c:url value='/admin/manager-client/delete/${item.id }'/>">Xóa</a>
					</td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
	</div>
	
	<jsp:include page="/WEB-INF/views/admin/pagination/master.jsp">
	    <jsp:param name="baseUrl" value="/management-client" />
	    <jsp:param name="currentPage" value="${currentPage}" />
	    <jsp:param name="totalPages" value="${totalPages}" />
	    <jsp:param name="pageSize" value="${pageSize}" />
	</jsp:include>

</body>
</html>