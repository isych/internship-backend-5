package com.exadel.backendservice.config;

import com.exadel.backendservice.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/events/**/archive").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/events/**/candidates").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/events/**/edit").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/events/**/image/upload").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/events/**/publish").hasAnyRole("SUPERADMIN")
                .antMatchers(HttpMethod.GET,"/api/events/archived").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/events/create").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/events/uniqueness/**").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/interviews/").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/interviews/**").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/interviews/**/delete").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/interviews/**/edit").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/interviews/**/feedback/edit").hasAnyRole("SUPERADMIN", "ADMIN", "TECH")
                .antMatchers(HttpMethod.PUT,"/api/interviews/**/employee/**").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/interviews/employee/**").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/interviews/feedback/**").hasAnyRole("SUPERADMIN", "ADMIN", "TECH")
                .antMatchers(HttpMethod.PUT,"/api/interviews/feedback/**").hasAnyRole("SUPERADMIN", "ADMIN", "TECH")
                .antMatchers(HttpMethod.GET,"/api/candidates").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/candidates/**").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/candidates/**/accept").hasAnyRole("SUPERADMIN")
                .antMatchers(HttpMethod.PUT,"/api/candidates/**/awaiting_hr").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/candidates/**/awaiting_tс").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/candidates/**/awaiting_decision").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/candidates/**/reject").hasAnyRole("SUPERADMIN")
                .antMatchers(HttpMethod.PUT,"/api/candidates/**/edit").hasAnyRole("SUPERADMIN")
                .antMatchers(HttpMethod.POST,"/api/candidates/**/cv/upload").hasAnyRole("SUPERADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/employees/**/delete").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/employees/add").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/employees/interviewers/list/").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/employees/interviewers/roles").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/employees/roles").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/timeslots/**/delete").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/timeslots/employee/**").hasAnyRole("SUPERADMIN", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/timeslots/employee/**/add").hasAnyRole("SUPERADMIN", "ADMIN")
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}





