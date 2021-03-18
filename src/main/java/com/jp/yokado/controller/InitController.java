package com.jp.yokado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.jp.yokado.annotation.SocialUser;
import com.jp.yokado.model.User;

@Controller
public class InitController {

  @GetMapping({"", "/"})
  public String getAuthorizationMessage() {
    return "login";
  }

  @GetMapping("/loginSuccess")
  public String loginComplete(@SocialUser User user) {
    return "redirect:/point";
  }

}
