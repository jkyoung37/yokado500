package com.jp.yokado.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.jp.yokado.annotation.SocialUser;
import com.jp.yokado.model.User;
import com.jp.yokado.service.MemberService;

@Controller
public class InitController {

  @Autowired
  public HttpSession session;
  @Autowired
  private MemberService memberService;


  @GetMapping({"", "/"})
  public String getAuthorizationMessage() {
    return "login";
  }

  @GetMapping("/login")
  public String viewLoginPage() {
    return "login";
  }

  @GetMapping("/signup")
  public String viewSignupPage(Model model) {
    model.addAttribute("member", new User());
    return "signup";
  }

  @PostMapping("/signup")
  public String signup(User memberDto) {
    memberService.signUp(memberDto);

    return "redirect:/";
  }

  @GetMapping("/select")
  public String viewSelectPage() {
    return "selectPage";
  }

  @GetMapping("/nomalLoginSuccess")
  public String loginComplete() {
    return "redirect:/select";
  }

  @GetMapping("/loginSuccess")
  public String loginComplete(@SocialUser User user) {
    session.setAttribute("userName", user.getName());
    session.setAttribute("userEmail", user.getEmail());
    return "redirect:/select";
  }

}
