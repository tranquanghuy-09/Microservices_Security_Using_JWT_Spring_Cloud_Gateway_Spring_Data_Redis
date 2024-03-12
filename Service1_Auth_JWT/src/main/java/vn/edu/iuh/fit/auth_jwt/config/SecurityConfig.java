package vn.edu.iuh.fit.auth_jwt.config;

import vn.edu.iuh.fit.auth_jwt.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {


    @Autowired
    private JwtRequestFilter jwtRequestFilter;

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/register", "/auth/login").permitAll()
//                .anyRequest().authenticated();
//
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .csrf().disable();
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.ignoringRequestMatchers("/auth/register", "/auth/**"));
        http.authorizeHttpRequests(auth->auth
                .requestMatchers("/auth/register", "/auth/login").permitAll()//nhung links nay khong can authenticate
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
        );
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).csrf().disable();
//        http.csrf(csrf->csrf.ignoringRequestMatchers("/h2-console/**"));
//        http.headers(headers->headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
//        http.httpBasic(Customizer.withDefaults());//cac thiet lap con lai thi theo mac dinh
        return http.build();
    }


}
