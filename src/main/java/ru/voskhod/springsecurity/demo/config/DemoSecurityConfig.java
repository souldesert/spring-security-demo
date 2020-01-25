package ru.voskhod.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

/*
* Configuration class for Spring Security
*/

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // add users for in-memory authentication

        UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("john").password("test123").roles("EMPLOYEE"))
                .withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"))
                .withUser(users.username("susan").password("test123").roles("EMPLOYEE", "ADMIN"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // restrict access based on HttpServletRequest
        http.authorizeRequests()
                // public landing page
                .antMatchers("/").permitAll()
                // private resources
                .antMatchers("/employees").hasRole("EMPLOYEE")
                .and()
                // customizing the form login process
                .formLogin()
                    .loginPage("/showMyLoginPage")
                    // will be created automatically
                    .loginProcessingUrl("/authenticateUser")
                    // everyone can access the login page
                    .permitAll()
                .and()
                    // automatically creates "/logout" endpoint
                    .logout()
                    .permitAll();


    }
}
