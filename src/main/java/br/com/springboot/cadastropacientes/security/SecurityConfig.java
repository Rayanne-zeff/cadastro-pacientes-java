package br.com.springboot.cadastropacientes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */


@Configuration
@EnableWebSecurity
@EnableAuthorizationServer
@EnableResourceServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Bean
    public AuthenticationManager authenticationManagerBeans() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()
//                .and()
//                .authorizeRequests()
////                .antMatchers("/h2-console/**").permitAll()
////                .antMatchers("/").permitAll()
////                .antMatchers("/books").hasRole("USER")
////                .antMatchers("/books2").hasRole("ADMIN")
//                .anyRequest().permitAll()
//                .and()
//                .csrf().disable()
//                .headers().frameOptions().disable();

        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/enfermeiro").hasRole("MEDICO")
                .antMatchers(HttpMethod.GET, "/api/paciente").hasRole("MEDICO")
                .antMatchers(HttpMethod.GET, "/api/paciente").hasRole("ENFERMEIRO")
//                .anyRequest().authenticated()
                .anyRequest().denyAll()
//                .and().formLogin().permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // TODO Auto-generated method stub
        super.configure(web);
    }
}
