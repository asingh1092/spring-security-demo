package com.singh.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	// add reference t our security data source
	@Autowired
	private DataSource securityDataSource;
	
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	// use JDBC authentication, no hard coding users
    	
    	auth.jdbcAuthentication().dataSource(securityDataSource);
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
