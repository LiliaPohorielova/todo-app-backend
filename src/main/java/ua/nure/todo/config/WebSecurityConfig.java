package ua.nure.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests()
            //.antMatchers("/open","/").permitAll()   // Открытые страницы
            //.anyRequest().authenticated()           // Все остальные требуют входа
            .anyRequest().permitAll()                 // Открываем все страницы без входа
            .and()
            .formLogin()
            .and()
            .httpBasic()
            .and().csrf().disable(); // ОТКЛЮЧИТЬ ПОТОМ - ШТУКА ПОЗВОЛЯЕТ КИДАТЬ JSON, POST-методы выполнять
        // БЕЗ АВТОРИЗАЦИИ
        return http.build();
    }
}