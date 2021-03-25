package com.jp.yokado.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests()
        .antMatchers("/", "/oauth2/**", "/login/**", "/css/**", "/images/**", "/js/**", "/fonts/**",
            "/vendor/**", "/console/**")
        .permitAll().anyRequest().authenticated().and().oauth2Login()
        .defaultSuccessUrl("/loginSuccess").failureUrl("/loginFailure").and().headers()
        .frameOptions().disable().and().exceptionHandling()
        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")).and().formLogin()
        .successForwardUrl("/board").and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
        .deleteCookies("JSESSIONID").invalidateHttpSession(true).and().csrf().disable();
  }
}
