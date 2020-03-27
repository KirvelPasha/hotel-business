package com.netcracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/comments").hasAnyAuthority("user")
                .antMatchers(HttpMethod.POST, "/comments").hasAuthority("user")
                .antMatchers(HttpMethod.GET, "/comments").hasAuthority("user")
                .antMatchers(HttpMethod.GET, "/dishorders/**").hasAnyAuthority("user")
                .antMatchers(HttpMethod.POST, "/dishorders").hasAnyAuthority("user")
                .antMatchers(HttpMethod.GET, "/bookings").hasAuthority("user")
                .antMatchers(HttpMethod.POST, "/bookings").hasAnyAuthority("user")
                .antMatchers(HttpMethod.DELETE, "/bookings").hasAuthority("user")
                .antMatchers(HttpMethod.POST, "/apartmentypes").hasAuthority("admin")
                .antMatchers(HttpMethod.GET, "/personroles/**").hasAuthority("admin")
                .antMatchers(HttpMethod.POST, "/apartments").hasAuthority("admin")
                .antMatchers(HttpMethod.GET, "/person/**").hasAuthority("admin")
                .and()
                .csrf().disable();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImp();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
}
