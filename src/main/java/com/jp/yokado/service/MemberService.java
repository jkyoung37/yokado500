package com.jp.yokado.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.jp.yokado.model.Role;
import com.jp.yokado.model.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

  private UserRepository memberRepository;

  @Transactional
  public Long signUp(com.jp.yokado.model.User memberDto) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

    return memberRepository.save(memberDto).getIdx();
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    com.jp.yokado.model.User member = memberRepository.findByEmail(email);
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));

    return new User(member.getName(), member.getPassword(), authorities);
  }

}
