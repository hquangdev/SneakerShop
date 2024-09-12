<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<body>

     <h2 class="master-title">Trang sửa khách hàng</h2>
   
    <div class="master-edit">
        <form:form action="updateClient" method="post" modelAttribute="client" accept-charset="UTF-8">
        <form:hidden path="id"/>
            <div class="form-edit">
                <label for="display_name">Họ và tên</label> <br>
                <form:input type="text" id="display_name" path="display_name"  required="true"/>
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
