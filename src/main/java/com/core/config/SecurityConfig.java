package com.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;
    
    public SecurityConfig(@Qualifier("bankUserDetailsService") UserDetailsService uds,
                          PasswordEncoder encoder) {
        this.userDetailsService = uds;
        this.encoder = encoder;
    }
    
    @Autowired
    public void globalConfig(AuthenticationManagerBuilder managerBuilder)
            throws Exception {
        managerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }
    
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/accounts/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/accounts/transfer").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/accounts/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
