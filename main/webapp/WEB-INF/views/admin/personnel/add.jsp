<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<body>
    <h2 class="master-title">Trang thêm nhân viên</h2>

    <!-- Hiển thị thông báo lỗi nếu có -->
    <c:if test="${not empty status}">
        <p class="error-message">${status}</p>
    </c:if>


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
			    <select name="role" id="role" required="required">
			        <option value="">Chọn chức vụ</option>
			        <option value="1">Quản lý</option>
			        <option value="2">Nhân viên</option>
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
