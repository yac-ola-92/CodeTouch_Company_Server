package com.tagmaster.codetouch.config;

//import com.tagmaster.codetouch.jwt.JWTFilter;
//import com.tagmaster.codetouch.jwt.JWTUtil;
//import com.tagmaster.codetouch.jwt.LoginFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

//    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration /*JWTUtil jwtUtil*/) {
        this.authenticationConfiguration = authenticationConfiguration;
//        this.jwtUtil = jwtUtil;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest req) {
                        CorsConfiguration config = new CorsConfiguration();

                        config.setAllowedOriginPatterns(Collections.singletonList("*"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
//                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                        source.registerCorsConfiguration("/**", config);
                        config.addExposedHeader("Authorization");
                        return config;
                    }
                })));
        http
                .csrf((auth) -> auth.disable());
        http
                .formLogin((auth) -> auth.disable());
        http
                .httpBasic((auth) -> auth.disable()); //stateless 상태일때 필요없기에 disable 시키기
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(/*"/login/**", "/api/**""/logout/**",*/ "/**").permitAll()
                        .anyRequest().authenticated());
//        http
//                .addFilterAfter(new JWTFilter(jwtUtil), LoginFilter.class); //로그인 필터 앞에 넣어준다 -> jwt 필터는 생성자에서 확인해보면 jwtutil 클래스 객체를 주입받았기 때문에 넣어준다.
//        http
//                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class); //filter생성해주기, 위치 어디로? authentication manager 메서드 갖고 와서 사용
//        http
//                .sessionManagement((session) -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //stateless 상태로 만들어주기

        return http.build();
    }
}
