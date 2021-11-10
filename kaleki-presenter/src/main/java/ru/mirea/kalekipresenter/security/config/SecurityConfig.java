package ru.mirea.kalekipresenter.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.mirea.kalekipresenter.security.jwt.JwtConfigurer;
import ru.mirea.kalekipresenter.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // AUTH ENDPOINTS
    private static final String AUTH_ENDPOINT = "/auth/**";
    private static final String SIGNUP_ENDPOINT = "/signup/**";


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
                    .antMatchers("/generate").permitAll()
                    .antMatchers("/signup/worker").permitAll()
                    .antMatchers(AUTH_ENDPOINT).permitAll()
                    .antMatchers(SIGNUP_ENDPOINT).permitAll()
                    .antMatchers("/signup/manager").hasRole("MANAGER")

                    .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
