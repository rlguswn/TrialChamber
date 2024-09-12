package rlguswn.trial_chamber;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(
                                "/", 
                                "/members/new", 
                                "/members/login",
                                "/css/**"
                        ).permitAll()
                        .requestMatchers("/admin").hasRole("USER")
                        .anyRequest().authenticated()
                );

        http
                .formLogin((auth) -> auth
                        .loginPage("/members/login")
                        .loginProcessingUrl("/members/login")
                        .failureUrl("/members/login?error=true")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                );

        http
                .logout(logout -> logout
                        .logoutUrl("/members/logout")
                        .logoutSuccessUrl("/members/login")
                );

        http
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}