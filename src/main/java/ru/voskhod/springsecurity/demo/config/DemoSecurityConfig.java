package ru.voskhod.springsecurity.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.sql.DataSource;

/*
* Configuration class for Spring Security
*/

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    // add a reference to security DataSource
    private DataSource securityDataSource;

    @Autowired
    public void setSecurityDataSource(DataSource securityDataSource) {
        this.securityDataSource = securityDataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // add users for in-memory authentication

        // use jdbc authentication
        auth.jdbcAuthentication().dataSource(securityDataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // restrict access based on HttpServletRequest
        http.authorizeRequests()
                // public landing page
                .antMatchers("/").permitAll()
                // private resources
                .antMatchers("/employees").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                .antMatchers("/leaders/**").hasAnyRole("MANAGER")
                .antMatchers("/systems/**").hasAnyRole("MANAGER", "ADMIN")
                .and()
                // customizing the form login process
                .formLogin()
                    .loginPage("/showMyLoginPage")
                    // will be created automatically
                    .loginProcessingUrl("/authenticateUser").defaultSuccessUrl("/employees")
                    // everyone can access the login page
                    .permitAll()
                .and()
                    // automatically creates "/logout" endpoint
                    .logout()
                    .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");


    }
}
