package se.swcg.consultauction.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET,"/api/admin").permitAll()
                .antMatchers(HttpMethod.GET,"/api/admin/*").permitAll()
                .antMatchers(HttpMethod.GET,"/api/admin/email/*").permitAll()
                .antMatchers(HttpMethod.GET,"/api/admin/role/*").permitAll()
                .antMatchers(HttpMethod.GET,"/api/admin/active/*").permitAll()
                .antMatchers(HttpMethod.GET,"/api/admin/lastActive/*").permitAll()
                .antMatchers(HttpMethod.POST,"/api/admin").permitAll()
                .anyRequest().authenticated();
    }
}