package Book.book_rental;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ComponentScan("Book.book_rental.service")

public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /* @formatter:off */
        http
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .cors().disable()        //cors방지
                .csrf().disable()        //csrf방지
                .headers().frameOptions().disable();
//                .authorizeHttpRequests()
//                .requestMatchers("/", "/main", "/login", "/signup", "/css/**", "/javascript/**", "/img/**").permitAll() // 설정한 리소스의 접근을 인증절차 없이 허용
//
//                //.anyRequest().authenticated() // 그 외 모든 리소스를 의미하며 인증 필요
//                .and()
//                .formLogin()
//                .loginPage("/login") // 기본 로그인 페이지
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();




            return http.build();


        }


    }
