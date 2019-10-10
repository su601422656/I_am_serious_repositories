package org.product.xiaoyuer.entity;


import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class User {

  private long id;
  private String username;
  private String password;
  private String salt;
  private String email;
  private long type;
  private long status;
  private String activationCode;
  private String headerUrl;

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  private Date createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public String getActivationCode() {
    return activationCode;
  }

  public void setActivationCode(String activationCode) {
    this.activationCode = activationCode;
  }


  public String getHeaderUrl() {
    return headerUrl;
  }

  public void setHeaderUrl(String headerUrl) {
    this.headerUrl = headerUrl;
  }


  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", salt='" + salt + '\'' +
            ", email='" + email + '\'' +
            ", type=" + type +
            ", status=" + status +
            ", activationCode='" + activationCode + '\'' +
            ", headerUrl='" + headerUrl + '\'' +
            ", createTime=" + createTime +
            '}';
  }
}
