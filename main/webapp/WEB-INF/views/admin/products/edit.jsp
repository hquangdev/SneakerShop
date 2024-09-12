<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa Sản Phẩm</title>
    <style>
        /* Styling for edit form */
     
    </style>
</head>
<body>

<div class="container-pr">
    <h1>Sửa Sản Phẩm</h1>

    <c:if test="${not empty error}">
        <div class="alert error">${error}</div>
    </c:if>

    <form action="updateProduct" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id_product" value="${productDTO.id_product}" />
        <div class="form-groupedit">
            <label for="name">Tên sản phẩm</label>
            <input type="text" id="name" name="name" value="${productDTO.name}" required />
        </div>

        <div class="form-groupedit">
            <label for="price">Giá</label>
            <input type="number" id="price" name="price" value="${productDTO.price}" required />
        </div>

        <div class="form-groupedit">
            <label for="highlight">Loại sản phẩm</label>
            <select id="highlight" name="highlight">
                <option value="0" ${productDTO.highlight == 0 ? 'selected' : ''}>Sản phẩm thường</option>
                <option value="1" ${productDTO.highlight == 1 ? 'selected' : ''}>Nổi bật</option>
            </select>
        </div>

        <div class="form-groupedit">
            <label for="new_product">Sản phẩm mới</label>
            <select id="new_product" name="new_product">
                <option value="0" ${productDTO.new_product == 0 ? 'selected' : ''}>Không</option>
                <option value="1" ${productDTO.new_product == 1 ? 'selected' : ''}>Có</option>
            </select>
        </div>

        <div class="form-groupedit">
            <label for="sale">Giảm giá (%)</label>
            <input type="number" id="sale" name="sale" value="${productDTO.sale}" />
        </div>
        
        <div class="form-groupedit">
            <label for="title">Tiêu đề</label>
            <textarea id="title" name="title" rows="4" required>${productDTO.title}</textarea>
        </div>

        <div class="form-groupedit">
            <label for="details">Chi tiết sản phẩm</label>
            <textarea id="details" name="details" rows="4" required>${productDTO.details}</textarea>
        </div>

       <div class="form-groupedit current-images">
            <label>Ảnh sản phẩm hiện tại:</label>
            <c:forEach var="image" items="${productDTO.img}">
                <img src="<c:url value='/assets/user/img-product/${image }'/> " />
            </c:forEach>
        </div>

        <div class="form-groupedit">
            <label for="img">Chọn ảnh mới (nếu cần):</label>
            <input type="file" id="img" name="img" multiple onchange="previewImages(event)" />
            <div class="preview-images" id="preview-images"></div>
        </div>
        
        <div class="form-groupedit">
            <label for="category">Danh mục</label>
            <select id="category" name="id_category" required>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}" ${category.id == productDTO.id_category ? 'selected' : ''}>
                        ${category.name}
                    </option>
                </c:forEach>
            </select>
        </div>

		<div class="form-groupedit">
		    <label>Size và Số lượng</label>
		    <div class="size-quantity-list">
		        <!-- Lặp qua tất cả các size mặc định và thêm số lượng -->
		        <c:forEach var="size" items="${productDTO.productsize}" varStatus="status">
			        <input type="hidden" name="id_productsize" value="${size.id_productsize}" />
					<input type="hidden" name="id_tablesize" value="${size.id}" />
		            <div class="size-quantity-item">
		                <!-- Hiển thị size dưới dạng text hoặc span để không cho chỉnh sửa -->
		                <span>Size: ${size.sizes}</span>
		                <!-- Hidden input để gửi size -->
		                <input type="hidden" name="sizes" value="${size.sizes}" />
		                <!-- Input số lượng cho size -->
		                <input type="number" name="quantity" value="${size.quantity}" placeholder="Số lượng" required />
		            </div>
		        </c:forEach>
		    </div>
		</div>



        <div class="form-groupedit">
            <button class="button-edits" type="submit">Cập nhật sản phẩm</button>
        </div>
    </form>
</div>
<script>
    // JavaScript to preview selected images before upload
    function previewImages(event) {
        const previewContainer = document.getElementById('preview-images');
        previewContainer.innerHTML = ''; // Clear previous previews
        const files = event.target.files;
        for (let i = 0; i < files.length; i++) {
            const file = files[i];
            const reader = new FileReader();
            reader.onload = function(e) {
                const img = document.createElement('img');
                img.src = e.target.result;
                previewContainer.appendChild(img);
            };
            reader.readAsDataURL(file);
        }
    }
</script>
</body>
</html>
