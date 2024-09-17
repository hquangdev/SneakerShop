<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<body>

     <h2 class="master-title">Trang sửa nhân viên</h2>
   
    <div class="master-edit">
        <form:form action="update-personnel" method="post" modelAttribute="personnel" accept-charset="UTF-8">
        <form:hidden path="id"/>
            <div class="form-edit">
                <label for="display_name">Họ và tên</label> <br>
                <form:input type="text" id="username" path="username"  required="true"/>
            </div>
            <div class="form-edit">
                <label for="email">Tài khoản ( E - mail )</label> <br>
                <form:textarea id="email" path="email"  rows="4" required="true"/>
            </div>
            
              <div class="form-edit">
                <label for="password">Mật khẩu</label> <br>
                <form:textarea id="password" path="password"  rows="4" required="true"/>
            </div>
            
              <div class="form-edit">
                <label for="phone">Số điện thoại</label> <br>
                <form:textarea id="phone" path="phone"  rows="4" required="true"/>
           	 </div>
            
            <div class="form-edit">
            <label for="role">Chức vụ:</label> <br>
	            <form:select path="role" id="role" required="true" >
	                <form:option value="1">Quản lý</form:option>
	                <form:option value="2">Nhân viên</form:option>
	            </form:select>
	        </div>
            
              <div class="form-edit">
                <label for="address">Địa chỉ</label> <br>
                <form:textarea id="address" path="address"  rows="4" required="true"/>
            </div>
 
            <div class="form-edit">
           	 <button class="button-edit" type="submit">Lưu lại</button>
           	</div>
        </form:form>
    </div>
</body>
</html>
