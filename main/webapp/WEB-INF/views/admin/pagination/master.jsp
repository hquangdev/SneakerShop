<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- WEB-INF/jsp/fragments/pagination.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="pagination">
    <!-- Previous Page Link -->
    <c:if test="${currentPage > 1}">
        <a href="${baseUrl}?page=${currentPage - 1}&size=${pageSize}">Trang trước</a>
    </c:if>
    <c:if test="${currentPage <= 1}">
       <a class="disabled">&#9664;</a> <!-- Mũi tên phải đầy đủ -->

    </c:if>

    <!-- Page Numbers -->
    <c:forEach var="i" begin="1" end="${totalPages}">
        <c:if test="${i == currentPage}">
            <a class="active">${i}</a>
        </c:if>
        <c:if test="${i != currentPage}">
            <a href="${baseUrl}?page=${i}&size=${pageSize}">${i}</a>
        </c:if>
    </c:forEach>

    <!-- Next Page Link -->
    <c:if test="${currentPage < totalPages}">
        <a href="${baseUrl}?page=${currentPage + 1}&size=${pageSize}">Trang sau</a>
    </c:if>
    <c:if test="${currentPage >= totalPages}">
        <a class="disabled">&#9654;</a> 

    </c:if>
</div>

<style type="text/css">
    /* Pagination Container */
    .pagination {
        display: flex;
        justify-content: center;
        margin: 20px 0;
    }

    /* Pagination Links */
    .pagination a {
        display: inline-block;
        padding: 8px 16px;
        margin: 0 4px;
        text-decoration: none;
        color: #007bff;
        border: 1px solid #007bff;
        border-radius: 4px;
        transition: background-color 0.3s, color 0.3s;
    }

    /* Pagination Links on Hover */
    .pagination a:hover {
        background-color: #007bff;
        color: #ffffff;
    }

    /* Active Page */
    .pagination a.active {
        background-color: #007bff;
        color: #ffffff;
        border-color: #007bff;
        pointer-events: none; /* Prevent clicking on the active page link */
    }

    /* Disabled Link */
    .pagination a.disabled {
        color: #6c757d;
        border-color: #6c757d;
        pointer-events: none; /* Prevent clicking on the disabled link */
        cursor: not-allowed; /* Change cursor to indicate it's not clickable */
    }
</style>

