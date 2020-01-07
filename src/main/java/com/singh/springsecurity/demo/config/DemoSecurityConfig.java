package com.singh.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // add our users in memory authentication

        UserBuilder users = User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
        		.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
                .withUser(users.username("mary").password("test123").roles("EMPLOYEE","MANAGER"))
                .withUser(users.username("susan").password("test123").roles("EMPLOYEE", "ADMIN"))
                .withUser(users.username("karen").password("test123").roles("EMPLOYEE", "MANAGER", "ADMIN"));
    }
    
    // Custom Login page ~
    // need to create /showMyLoginPage (plain-login.jsp) and controller (LoginController)
    // the mapping /authenticateTheUser comes for free from Spring - it checks the authentication 
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/")
            	.permitAll()  // allow public access to home page
            .antMatchers("/employees").hasRole("EMPLOYEE")
            .antMatchers("/leaders/**").hasRole("MANAGER")
            .antMatchers("/systems/**").hasRole("ADMIN")
            	.and()
            .formLogin()
            	.loginPage("/showMyLoginPage")
            	.loginProcessingUrl("/authenticateTheUser")
            		.permitAll()
            .and()
            	.logout()
            	.logoutSuccessUrl("/")  // after logout then redirect to landing page (root)
            		.permitAll()
        	.and()
        	.exceptionHandling()
        		.accessDeniedPage("/access-denied");
    }
}
