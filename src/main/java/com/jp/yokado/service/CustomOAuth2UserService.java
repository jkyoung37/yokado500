package com.jp.yokado.service;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.jp.yokado.dto.SessionUser;
import com.jp.yokado.model.OAuthAttributes;
import com.jp.yokado.model.Role;
import com.jp.yokado.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final HttpSession session;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = service.loadUser(userRequest);

		String registrationId = userRequest.getClientRegistration().getRegistrationId();

		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
				.getUserNameAttributeName();

		OAuthAttributes oAuthAttributes = OAuthAttributes.of(registrationId, userNameAttributeName,
				oAuth2User.getAttributes());

		User user = saveOrUpdate(oAuthAttributes);

		System.out.println("***********************info ");

		session.setAttribute("user", new SessionUser(user));

		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
				oAuthAttributes.getAttributes(), oAuthAttributes.getNameAttributeKey());
	}

	private User saveOrUpdate(OAuthAttributes attributes) {
		User user = new User("name", "email", "picture", Role.USER);
		return user;
	}

}
