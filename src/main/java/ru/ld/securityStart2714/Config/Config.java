package ru.ld.securityStart2714.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class Config extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/public-data").authenticated() // ресурс, доступный всем аутентифицированным пользователям
                .antMatchers("/private-data").hasRole("ADMIN") // ресурс, доступный для роли Админ
                .antMatchers("/").permitAll()
                .and()
                .formLogin()
                .loginPage("/") // форма для ввода пароля
                .permitAll() // форма доступна для всех
                .defaultSuccessUrl("/public-data") // страница, на которую пользователь попадает после успешного ввода пароля
                .and()
                .logout()
                .logoutSuccessUrl("/") // страница, на которую пользователь попадает после выхода (log out)
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/?error_resources"); // ошибка, которая вызывается при нарушении авторизации
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}password").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}password").roles("ADMIN");
//    }
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build());
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build());
        return manager;
    }
}
