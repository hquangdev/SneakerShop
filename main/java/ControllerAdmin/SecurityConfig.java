//package ControllerAdmin;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                .antMatchers("/admin/**").authenticated() // Yêu cầu xác thực cho tất cả các URL dưới /admin/
//                .antMatchers( "/", "/home-page").permitAll() // Cho phép truy cập không yêu cầu xác thực cho trang login và logout
//                .anyRequest().permitAll() // Cho phép truy cập tất cả các yêu cầu còn lại
//            )
//            .formLogin(formLogin -> formLogin
//                .loginPage("/admin") // Cấu hình URL cho trang đăng nhập
//                .permitAll() // Cho phép truy cập trang login mà không cần xác thực
//            )
//            .logout(logout -> logout
//                .permitAll() // Cho phép logout mà không cần xác thực
//            );
//
//        return http.build();
//    }
//
//
//}


