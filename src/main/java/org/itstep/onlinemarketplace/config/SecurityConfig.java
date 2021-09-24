package org.itstep.onlinemarketplace.config;


import org.itstep.onlinemarketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService service;

    public SecurityConfig(UserService service) {
        this.service = service;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/market/adverts/**").permitAll()
                .antMatchers(HttpMethod.GET, "/advert/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/adverts/**").permitAll()
                .antMatchers(HttpMethod.POST, "/adverts/delete/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/adverts/buy/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/market/adverts/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/market/adverts/**").hasRole("USER")
                .antMatchers("/swagger-ui/**").hasRole("ADMIN").and()
                .csrf().disable();

        http.formLogin().loginPage("/login").permitAll()
                .loginProcessingUrl("/auth").permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .successForwardUrl("/")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error");
        http.logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login").permitAll();
//        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }

}
