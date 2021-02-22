package com.jp.yokado.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {

	private Long id;

	private String name;

	private String email;

	private String picture;

	private Role role;

	public User(String name, String email, String picture, Role role) {
		this.name = name;
		this.email = email;
		this.picture = picture;
		this.role = role;
	}

	public User update(String name, String picture) {
		this.name = name;
		this.picture = picture;

		return this;
	}

	public String getRoleKey() {
		return this.role.getKey();
	}

}
