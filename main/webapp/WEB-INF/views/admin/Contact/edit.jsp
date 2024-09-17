
    
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<body>

     <h2 class="master-title">Sửa Thông Tin Liên Hệ</h2>
   
    <div class="master-edit">
        <form:form action="updateContact" method="post" modelAttribute="contact" >
        
        <form:hidden path="id"/>
            <div class="form-edit">
                <label for="office_Hotline">Hot line Văn phòng</label> <br>
                <form:input type="text" id="office_Hotline" path="office_Hotline"  required="true"/>
            </div>
            
             <div class="form-edit">
                <label for="customer_care_Hotline">Hotline chăm sóc khách hàng</label> <br>
                <form:input type="text" id="customer_care_Hotline" path="customer_care_Hotline"  required="true"/>
            </div>
             <div class="form-edit">
                <label for="purchase_Hotline">Hotline mua hàng</label> <br>
                <form:input type="text" id="purchase_Hotline" path="purchase_Hotline"  required="true"/>
            </div>
             <div class="form-edit">
                <label for="email">E - mail</label> <br>
                <form:input type="text" id="email" path="email"  required="true"/>
            </div>
             <div class="form-edit">
                <label for="address">Địa chỉ</label> <br>
                <form:input type="text" id="address" path="address"  required="true"/>
            </div>
            
            
            <div class="form-edit">
                <label for="content">Nội dung</label> <br>
                <form:textarea id="content" path="content"  rows="4" required="true"/>
            </div>
            <div class="form-edit">
           	 <button class="button-edit" type="submit">Lưu lại</button>
           	</div>
        </form:form>
    </div>
</body>
</html>