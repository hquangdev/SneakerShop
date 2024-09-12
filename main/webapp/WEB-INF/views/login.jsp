<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<body>
    <div class="login-container overlay">
        <div class="login-form ">
            <h3 class="titleadmin">ĐĂNG NHẬP HỆ THỐNG</h3>
            <form action="/admin/login" method="post">
                <div class="input-group">
                    <input type="text" name="email" required>
                </div>
                <div class="input-group">
                    <input type="password" name="password" required>
                </div>
                <button type="submit" class="loginForm button">Đăng nhập</button>
                <div class="error-message" id="error-message"></div>
            </form>
        </div>
    </div>
    <script src="script.js"></script>
</body>
</html>
