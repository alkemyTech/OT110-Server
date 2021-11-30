package com.alkemy.ong.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class MainSecurity extends WebSecurityConfigurerAdapter {


   
   /* @Autowired
    private UserDetailsService customUserDetailsService;*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.requestMatchers()
                .antMatchers("/login")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll();
    }

    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.parentAuthenticationManager(authenticationManager);
               // .userDetailsService(customUserDetailsService);
    }
}