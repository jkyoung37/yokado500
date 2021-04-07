package com.jp.yokado.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class User implements Serializable {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idx;

  @Column
  private String name;

  @Column
  private String password;

  @Column
  private String email;

  @Column
  private String principal;

  @Column
  @Enumerated(EnumType.STRING)
  private SocialType socialType;

  @Column
  private LocalDateTime createdDate;

  @Column
  private LocalDateTime updatedDate;

  @Builder
  public User(String name, String password, String email, String principal, SocialType socialType,
      LocalDateTime createdDate, LocalDateTime updatedDate) {
    this.name = name;
    this.password = password;
    this.email = email;
    this.principal = principal;
    this.socialType = socialType;

    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
  }

  @Builder
  public User(String name, String password, String email) {
    this.name = name;
    this.password = password;
    this.email = email;
  }
}
