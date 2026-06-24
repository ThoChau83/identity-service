package org.example.identityservice.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    // ĐÂY CHÍNH LÀ ĐOẠN CODE "MỞ KHÓA" ĐỂ KHÔNG BỊ LỖI 401 NỮA
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // Xin phép bảo vệ cho qua cửa hết (permitAll)
        httpSecurity.authorizeHttpRequests(request ->
                request.anyRequest().permitAll()
        );

        // Phải có dòng này thì gọi POST mới không bị chặn nhé
        httpSecurity.csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }
}