<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<body>

	<h2 class="master-title" >Quản lý hóa đơn</h2>
	
	
	<div class="master-list">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Họ và tên</th>
					<th>Số điện thoại</th>
					<th>Số lượng</th>
					<th>Tổng tiền</th>
					<th>Trạng thái</th>
					<th colspan="2">Tác vụ</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${listBill }">
				<tr>
					<td>${item.id }</td>
					<td>${item.display_name }</td>
					<td>${item.phone }</td>
					<td>${item.quanty }</td>
					<td><fmt:formatNumber value="${item.total }" type="number" pattern="#,##0" />₫</td>
					<td>
					  ${item.status == 1 ? 'Đang xử lý' : item.status == 2 ? 'Đã xử lý' : 'Trạng thái không hợp lệ'}
					</td>
					<td><a class="master-buttonedit" href="<c:url value='/admin/manager-bill/view-detail/${item.id }'/>">Xem</a>
					<a class="master-buttondelete" href="<c:url value='/admin/manager-bill/delete/${item.id }'/>">Xóa</a></td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
	</div>
	
	<jsp:include page="/WEB-INF/views/admin/pagination/master.jsp">
	    <jsp:param name="baseUrl" value="/manager-bill" />
	    <jsp:param name="currentPage" value="${currentPage}" />
	    <jsp:param name="totalPages" value="${totalPages}" />
	    <jsp:param name="pageSize" value="${pageSize}" />
	</jsp:include>

</body>
</html>