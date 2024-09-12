<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>

	<h2 class="master-title" >Quản lý slide</h2>
	
	<div class="button-add">
		<a href="<c:url value='add-slide'/>">Thêm slide</a>
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
					<th>Nội dung</th>
					<th>Tiêu đề</th>
					<th colspan="2">Tác vụ</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${slides }">
				<tr>
					<td>${item.id }</td>
					<td><img style="width:300px;" src="<c:url value='/assets/user/Slide-img/${item.img}'/>"></td>
					<td>${item.caption }</td>
					<td>${item.content }</td>
					<td><a class="master-buttonedit" href="<c:url value='/admin/manager-slide/edit/${item.id }'/>">Sửa</a></td>
					
					<td><a class="master-buttondelete" href="<c:url value='/admin/manager-slide/delete/${item.id }'/>">Xóa</a></td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
	</div>
	
	<jsp:include page="/WEB-INF/views/admin/pagination/master.jsp">
	    <jsp:param name="baseUrl" value="/manager-slide" />
	    <jsp:param name="currentPage" value="${currentPage}" />
	    <jsp:param name="totalPages" value="${totalPages}" />
	    <jsp:param name="pageSize" value="${pageSize}" />
	</jsp:include>

</body>
</html>