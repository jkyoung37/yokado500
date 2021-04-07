package com.jp.yokado.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import com.jp.yokado.service.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private MemberService memberService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests()
        .antMatchers("/", "/oauth2/**", "/login/**", "/css/**", "/images/**", "/js/**", "/fonts/**",
            "/vendor/**", "/console/**", "/signup")
        .permitAll().anyRequest().authenticated().and().oauth2Login()
        .defaultSuccessUrl("/loginSuccess").failureUrl("/loginFailure").and().headers()
        .frameOptions().disable().and().exceptionHandling()
        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")).and().formLogin()
        .loginProcessingUrl("/authenticate").usernameParameter("email")
        .defaultSuccessUrl("/nomalLoginSuccess").permitAll().and().logout().logoutUrl("/logout")
        .logoutSuccessUrl("/").deleteCookies("JSESSIONID").invalidateHttpSession(true).and().csrf()
        .disable();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
  }
}
