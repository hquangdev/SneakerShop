<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<body>
    <h2 class="master-title">Trang sửa slide</h2>

    <div class=master-edit>
        <form:form method="post" enctype="multipart/form-data" action="slide" modelAttribute="slideAdmin">
        	<form:hidden path="id"/>
          	<div class="form-edit">
                <label for="img">Chọn ảnh để sửa</label> <br>
                <form:input type="file" id="img" path="img" accept="image/*" onchange="previewImage(event)"/>
                
                <div class="preview-container">
                	<img id="preview" class="preview-img" 
                     src="<c:url value='/assets/user/Slide-img/${slideAdmin.img}'/>" alt="Ảnh hiện tại"/>
            	</div>
            </div>
            <div class="form-edit">
                <label for="caption">Tiêu đề</label>  <br>
                <form:input type="text" id="caption" path="caption" required="true"/>
            </div>
            <div class="form-edit">
                <label for="content">Nội dung</label>  <br>
                <form:textarea id="content" path="content" rows="4" required="true"/>
            </div>
            <div class="form-edit">
                <button class="button-edit" type="submit">lưu lại</button>
            </div>
        </form:form>
       
    </div>

    <script>
        function previewImage(event) {
            var reader = new FileReader();
            var image = document.getElementById('preview');
            reader.onload = function() {
                image.src = reader.result;
            }
            reader.readAsDataURL(event.target.files[0]);
        }
    </script>
</body>
</html>
