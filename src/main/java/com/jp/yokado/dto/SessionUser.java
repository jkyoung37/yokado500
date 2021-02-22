package com.jp.yokado.dto;

import java.io.Serializable;

import com.jp.yokado.model.User;

import lombok.Getter;

@Getter

public class SessionUser implements Serializable {

	private String email, name, picture;

	public SessionUser(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.picture = user.getPicture();
	}
}
