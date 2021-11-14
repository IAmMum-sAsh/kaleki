package ru.mirea.kaleki.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.mirea.kaleki.security.jwt.JwtConfigurer;
import ru.mirea.kaleki.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // AUTH ENDPOINTS
    private static final String AUTH_ENDPOINT = "/api/auth/**";
    private static final String SIGNUP_ENDPOINT = "/api/signup/**";


    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers(AUTH_ENDPOINT).permitAll()
                    .antMatchers(SIGNUP_ENDPOINT).permitAll()
                    .antMatchers("/api/signup/manager").hasRole("MANAGER")
                    .antMatchers("/api/companies").permitAll()
                    .antMatchers("/api/projects").permitAll()
                    .antMatchers("/api/workers").hasAnyRole("WORKER", "MANAGER")
                    .antMatchers("/api/my_projects").hasAnyRole("WORKER", "MANAGER")
                    .antMatchers("/api/my_projects/**").hasAnyRole("WORKER", "MANAGER")
//                    .antMatchers("/api/companies").permitAll()

                    .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
