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
    <h2 class="master-title">Trang thêm nhân viên</h2>

    <div class="master-add">
        <form method="post" action="addPersonnel">
         
            <div class="form-group">
                <label for="username">Họ và tên</label>  <br>
                <input type="text" id=username name="username" required>
            </div>
            
            <div class="form-group">
                <label for="username">Tài khoản E-mail</label>  <br>
                <input type="text" id="username" name="email" required>
            </div>
            
             <div class="form-group">
                <label for="password">Mật khẩu</label>  <br>
                <input type="password" id="password" name="password" required>
            </div>
            
            <div class="form-group">
                <label for="phone">Số điện thoại</label>  <br>
                <input type="text" id="phone" name="phone" required>
            </div>
            
            <div class="form-group">
                <label for="role">Chức vụ</label>  <br>
                <select required="required">
                	<option value="0">Chọn chức vụ</option>
                	<option value="1">Quản lí</option>
                	<option value="0">Nhân viên</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="address">Địa chỉ</label>  <br>
                <textarea id="address" name="address" rows="4" required></textarea>
            </div>
            <div class="form-group">
                <button class="button-edit" type="submit">Thêm nhân viên</button>
            </div>
        </form>
       
    </div>

</body>
</html>
