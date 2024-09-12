<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<body>

     <h2 class="master-title">Trang sửa danh mục</h2>
   
    <div class="master-edit">
        <form:form action="editcategory" method="post" modelAttribute="category" accept-charset="UTF-8">
        <form:hidden path="id"/>
            <div class="form-edit">
                <label for="name">Tên danh mục</label> <br>
                <form:input type="text" id="name" path="name"  required="true"/>
            </div>
            <div class="form-edit">
                <label for="description">Nội dung</label> <br>
                <form:textarea id="description" path="description"  rows="4" required="true"/>
            </div>
            <div class="form-edit">
           	 <button class="button-edit" type="submit">Lưu lại</button>
           	</div>
        </form:form>
    </div>
</body>
</html>
