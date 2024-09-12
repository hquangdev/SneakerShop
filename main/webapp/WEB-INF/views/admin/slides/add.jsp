<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Slide Mới</title>
    <style>
       
    </style>
</head>
<body>
    <h2 class="master-title">Thêm Slide Mới</h2>

    <div class="master-add">
        <form method="post" enctype="multipart/form-data" action="add-slide">
            <div class="form-group">
                <label for="img">Hình ảnh</label> <br>
                <input type="file" id="img" name="img" accept="image/*" onchange="previewImage(event)" required>
            </div>
            <div class="form-group">
                <label for="caption">Tiêu đề</label>  <br>
                <input type="text" id="caption" name="caption" required>
            </div>
            <div class="form-group">
                <label for="content">Nội dung</label>  <br>
                <textarea id="content" name="content" rows="4" required></textarea>
            </div>
            <div class="form-group">
                <button class="button-edit" type="submit">Thêm Slide</button>
            </div>
        </form>
        <!-- Xem trước ảnh -->
        <div class="preview-container">
            <img id="preview" class="preview-img" alt="Ảnh xem trước"/>
        </div>
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
