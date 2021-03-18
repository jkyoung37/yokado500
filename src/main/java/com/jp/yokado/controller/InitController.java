package com.jp.yokado.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.jp.yokado.annotation.SocialUser;
import com.jp.yokado.model.User;

@Controller
public class InitController {

  @Autowired
  public HttpSession session;

  @GetMapping({"", "/"})
  public String getAuthorizationMessage() {
    return "login";
  }

  @GetMapping("/select")
  public String viewSelectPage() {
    return "selectPage";
  }

  @GetMapping("/loginSuccess")
  public String loginComplete(@SocialUser User user) {
    session.setAttribute("userName", user.getName());
    session.setAttribute("userEmail", user.getEmail());
    return "redirect:/select";
  }

}
