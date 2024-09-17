<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
<head>
<style type="text/css"></style>
</head>
<body>
    <div class="login-container overlay">
        <div class="login-form ">
            <h3 class="titleadmin">ĐĂNG NHẬP HỆ THỐNG</h3>
            
            <c:if test="${ not empty statuslogin }">
            	<p style="text-align: center;color: red;">${statuslogin }</p>
            </c:if>
            
            <form action="admin-login" method="post">
                <div class="input-group">
                    <input type="text" name="email" required>
                </div>
                <div class="input-group">
                    <input type="password" name="password" required>
                </div>
              
                
                <div class="input-group">
               	 <button type="submit" class="loginForm button">Đăng nhập</button>
                </div>
                 
                <div class="input-group">
                    <p>Quên mật khẩu </p>
                </div>
               
            </form>
        </div>
    </div>
</body>
</html>
