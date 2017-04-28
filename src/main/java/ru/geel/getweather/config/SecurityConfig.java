package ru.geel.getweather.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by ivangeel on 26.04.17.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, 1 as enabled from UserLogin where username=?")
                .authoritiesByUsernameQuery("select username, 'ROLE_USER' from UserLogin where username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .anyRequest().authenticated() //all requests will checked
                .and()
                .formLogin().loginPage("/login").permitAll().usernameParameter("username")
                .passwordParameter("password").loginProcessingUrl("/signin").failureUrl("/login?error=true")
                .and()
                .httpBasic()
                .and()
                .authorizeRequests().antMatchers("/co").hasRole("USER")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and()
                .rememberMe().key("myKey").tokenValiditySeconds(300)
                .and()
                .csrf().disable();

    }
}
