<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<body>

	<h2 class="master-title" >Quản lý nhân viên</h2>
	
	<div class="button-add">
		<a href="<c:url value='add-personnel'/>">Thêm nhân viên</a>
	</div>
	
	<div class="master-title">
       <!-- Hiển thị thông báo lỗi nếu có -->
    <c:if test="${not empty status}">
        <p style="color:red;"><c:out value="${status}"/></p>
    </c:if>
    </div>
	
	<div class="master-list">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Họ và tên</th>
					<th>Tài khoản</th>
					<th>Số điện thoại</th>
					<th>Địa chỉ</th>
					<th>Chức vụ</th>
					<th colspan="2">Tác vụ</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${personnel }">
				<tr>
					<td>${item.id }</td>
					<td>${item.username }</td>
					<td>${item.email }</td>
					<td>+84: 0${item.phone }</td>
					<td>${item.address }</td>
					<c:if test="${item.role == 1 }">
						<td>Quản lý</td>
					</c:if>
					<c:if test="${item.role == 2 }">
						<td>Nhân viên</td>
					</c:if>
					
					<td><a class="master-buttonedit" href="<c:url value='/admin/manager-personnel/edit/${item.id }'/>">Sửa</a></td>
					
					<td><a class="master-buttondelete" href="<c:url value='/admin/manager-personnel/delete/${item.id }'/>">Xóa</a></td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
	</div>
	
	<jsp:include page="/WEB-INF/views/admin/pagination/master.jsp">
	    <jsp:param name="baseUrl" value="/manager-personnel" />
	    <jsp:param name="currentPage" value="${currentPage}" />
	    <jsp:param name="totalPages" value="${totalPages}" />
	    <jsp:param name="pageSize" value="${pageSize}" />
	</jsp:include>

</body>
</html>