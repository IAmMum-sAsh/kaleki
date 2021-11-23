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

/**
 * The type Security config.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // AUTH ENDPOINTS
    private static final String AUTH_ENDPOINT = "/api/auth/**";
    private static final String SIGNUP_ENDPOINT = "/api/signup/**";


    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Instantiates a new Security config.
     *
     * @param jwtTokenProvider the jwt token provider
     */
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
                    .antMatchers("/api/companies").permitAll()
                    .antMatchers("/api/companies/**").permitAll()
                    .antMatchers("/api/projects").permitAll()
                    .antMatchers("/api/projects/**").permitAll()
                    .antMatchers("/api/workers").hasAnyRole("WORKER", "MANAGER", "ADMIN")
                    .antMatchers("/api/my_projects").hasAnyRole("WORKER", "MANAGER", "ADMIN")
                    .antMatchers("/api/my_projects/**").hasAnyRole("WORKER", "MANAGER", "ADMIN")
                    .antMatchers("/api/give_manage").hasRole("ADMIN")
                    .antMatchers("/api/give_admin").hasRole("ADMIN")
                    .antMatchers("/api/create_company").hasRole("ADMIN")
                    .antMatchers("/api/create_project").hasRole("ADMIN")
                    .antMatchers("/api/set_worker_on_project").hasAnyRole("MANAGER", "ADMIN")
                    .antMatchers("/api/change_project_status").hasRole("ADMIN")
                    .antMatchers("/api/get_my_role").hasAnyRole("WORKER", "MANAGER", "ADMIN")
                    .antMatchers("/api/get_positions").hasAnyRole("MANAGER", "ADMIN")
                    .antMatchers("/api/get_name").hasAnyRole("WORKER", "MANAGER", "ADMIN")
                    .antMatchers("/api/get_id").hasAnyRole("WORKER", "MANAGER", "ADMIN")
                    .antMatchers("/api/accept_into_company").hasAnyRole("MANAGER", "ADMIN")

                    .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
