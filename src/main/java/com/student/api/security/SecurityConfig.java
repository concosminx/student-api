package com.student.api.security;

//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;

//@Configuration
public class SecurityConfig {
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http.httpBasic(withDefaults());
//
//    http.authorizeHttpRequests((authorize) -> authorize
//        //.requestMatchers(HttpMethod.POST, "/students").permitAll()
//        .requestMatchers(PathRequest.toH2Console()).permitAll()
//        .requestMatchers("/v3/api-docs/**", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
//        .anyRequest().authenticated());
//
//    http.sessionManagement((customizer) -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//    http.csrf((csrf) -> csrf.disable());
//    http.headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()));
//
//    return http.build();
//  }
//
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Bean
//  public UserDetailsService users(PasswordEncoder passwordEncoder) {
//    UserDetails user = User.builder()
//        .username("user")
//        .password(passwordEncoder.encode("secret"))
//        .roles("USER")
//        .build();
//    UserDetails admin = User.builder()
//        .username("admin")
//        .password(passwordEncoder.encode("secret"))
//        .roles("USER", "ADMIN")
//        .build();
//    return new InMemoryUserDetailsManager(user, admin);
//  }
}
