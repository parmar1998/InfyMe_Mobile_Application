package com.infy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Configure authentication provider, such as in-memory or database-based authentication

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser("admin").password(encoder.encode("nimda"))
                .roles("ADMIN");
//        auth.inMemoryAuthentication()
//                .withUser("javainspires")
//                .password("{bcrypt}$2a$12$xsyEmjxrehAQElVMeR0t3./4P3GBWmUH1DnBMRxopsYlsKqthJGti")
//                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/register").permitAll() // Allow registration endpoint without authentication
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable(); // Disable CSRF protection for simplicity, but enable it in a production environment
    }
}

