package com.jp.yokado;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.jp.yokado.resolver.UserArgumentResolver;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class YokadoApplication implements WebMvcConfigurer {
  private final UserArgumentResolver userArgumentResolver;

  public static void main(String[] args) {
    SpringApplication.run(YokadoApplication.class, args);
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(userArgumentResolver);
  }
}
