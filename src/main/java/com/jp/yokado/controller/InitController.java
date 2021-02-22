package com.jp.yokado.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jp.yokado.dto.SessionUser;

@Controller
@RequestMapping("/")
public class InitController {

	@GetMapping
	public String init() {
		return "login";
	}

	@GetMapping(value = "/select")
	public String goSelectPage(Model model, HttpSession session) {

		SessionUser member = (SessionUser) session.getAttribute("user");

		return "selectPage";
	}
}
