<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<body>
    <h2 class="master-title">Trang thêm sản phẩm</h2>

    <div class="master-add">
        <form method="post" action="addProduct" enctype="multipart/form-data" >
            <div class="form-group">
                <label for="img">Hình ảnh</label> <br>
                <input type="file" id="img" name="img" accept="image/*" onchange="previewImages(event)" multiple required>
            </div>
            
            <div class="preview-container" style="display: flex; flex-wrap: wrap; margin-top: 10px;">
                <!-- Các hình ảnh xem trước sẽ được thêm vào đây -->
            </div>

            <div class="form-group">
                <label for="name">Tên sản phẩm</label>  <br>
                <input type="text" id="name" name="name" required>
            </div>
            
           <div class="form-group">
			    <label for="category">Danh mục</label> <br>
			    <select id="category" name="id_category" required>
			        <option value="">Chọn danh mục</option>
			        <c:forEach var="category" items="${categories}">
			            <option value="${category.id}">${category.name}</option>
			        </c:forEach>
			    </select>
			</div>

            <div class="form-group">
                <label for="sale">Giảm giá ( % )</label>  <br>
                <input type="text" id="sale" name="sale" required>
            </div>
            
            <div class="form-group">
                <label for="price">Giá sản phẩm</label>  <br>
                <input type="text" id="price" name="price" required>
            </div>
            
            <div class="form-group">
			    <label>Loại sản phẩm</label> <br>
			    <input style="width: 30px;" type="radio" id="highlight" name="type" value="highlight" required>
			    <label for="highlight">Nổi bật</label> 
			
			    <input style="width: 30px;" type="radio" id="new_product" name="type" value="new_product">
			    <label for="new_product">Sản phẩm mới</label> 
			
			    <input style="width: 30px;" type="radio" id="regular" name="type" value="regular">
			    <label for="regular">Sản phẩm thường</label> 
			</div>
			
			<!-- Hidden fields to store the actual values for highlight and new_product -->
			<input type="hidden" id="highlight-value" name="highlight" value="0">
			<input type="hidden" id="new_product-value" name="new_product" value="0">

            
                <div class="form-group size-container">
		        <div class="size-item">
		            <label for="quantity-36">Size 36</label> <br>
		            <input type="number" id="quantity-36" name="quantity" placeholder="Số lượng" min="0">
		            <input type="hidden" name="sizes" value="36">
		        </div>
		        <div class="size-item">
		            <label for="quantity-37">Size 37</label> <br>
		            <input type="number" id="quantity-37" name="quantity" placeholder="Số lượng" min="0">
		            <input type="hidden" name="sizes" value="37">
		        </div>
		        <div class="size-item">
		            <label for="quantity-38">Size 38</label> <br>
		            <input type="number" id="quantity-38" name="quantity" placeholder="Số lượng" min="0">
		            <input type="hidden" name="sizes" value="38">
		        </div>
		        <div class="size-item">
		            <label for="quantity-39">Size 39</label> <br>
		            <input type="number" id="quantity-39" name="quantity" placeholder="Số lượng" min="0">
		            <input type="hidden" name="sizes" value="39">
		        </div>
		        <div class="size-item">
		            <label for="quantity-40">Size 40</label> <br>
		            <input type="number" id="quantity-40" name="quantity" placeholder="Số lượng" min="0">
		            <input type="hidden" name="sizes" value="40">
		        </div>
		        <div class="size-item">
		            <label for="quantity-41">Size 41</label> <br>
		            <input type="number" id="quantity-41" name="quantity" placeholder="Số lượng" min="0">
		            <input type="hidden" name="sizes" value="41">
		        </div>
		        <div class="size-item">
		            <label for="quantity-42">Size 42</label> <br>
		            <input type="number" id="quantity-42" name="quantity" placeholder="Số lượng" min="0">
		            <input type="hidden" name="sizes" value="42">
		        </div>
		    </div>


            <div class="form-group">
                <label for="title">Tiêu đề</label>  <br>
                <input type="text" id="title" name="title" required>
            </div>
            

            <div class="form-group">
                <label for="details">Mô tả sản phẩm</label>  <br>
                <textarea id="details" name="details" rows="9" required></textarea>
            </div>
            <div class="form-group">
                <button class="button-edit" type="submit">Thêm sản phẩm</button>
            </div>
        </form>
    </div>

    <script>
        function previewImages(event) {
            var files = event.target.files; // Lấy tất cả các tệp tin từ input
            var previewContainer = document.querySelector('.preview-container');
            
            // Xóa các hình ảnh trước đó
            previewContainer.innerHTML = '';

            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                var reader = new FileReader();
                
                reader.onload = (function(file) {
                    return function(e) {
                        var img = document.createElement('img');
                        img.src = e.target.result;
                        img.classList.add('preview-img');
                        img.style.maxWidth = '200px'; // Chiều rộng tối đa của hình ảnh
                        img.style.margin = '5px'; // Khoảng cách giữa các hình ảnh
                        previewContainer.appendChild(img);
                    };
                })(file);
                
                reader.readAsDataURL(file);
            }
        }
        
        document.querySelectorAll('input[name="type"]').forEach(function(elem) {
            elem.addEventListener('change', function() {
                var typeValue = this.value;
                
                if (typeValue === 'highlight') {
                    document.getElementById('highlight-value').value = '1';
                    document.getElementById('new_product-value').value = '0';
                } else if (typeValue === 'new_product') {
                    document.getElementById('highlight-value').value = '0';
                    document.getElementById('new_product-value').value = '1';
                } else if (typeValue === 'regular') {
                    document.getElementById('highlight-value').value = '3';
                    document.getElementById('new_product-value').value = '3';
                }
            });
        });
    </script>
</body>
</html>
