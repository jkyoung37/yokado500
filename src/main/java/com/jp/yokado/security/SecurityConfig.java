package com.jp.yokado.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.jp.yokado.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().headers().frameOptions().disable().and().authorizeRequests()
				.antMatchers("/", "/css/**", "/js/**", "/fonts/**", "/jquery/**", "/vendor/**", "/images/**")
				.permitAll().anyRequest().authenticated().and().oauth2Login().defaultSuccessUrl("/select")
				.userInfoEndpoint().userService(customOAuth2UserService);
	}
}
