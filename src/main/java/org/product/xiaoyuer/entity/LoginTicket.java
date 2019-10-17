package org.product.xiaoyuer.entity;


public class LoginTicket {

  private int id;
  private int userId;
  private String ticket;
  private int status;
  private java.util.Date expired;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }


  public String getTicket() {
    return ticket;
  }

  public void setTicket(String ticket) {
    this.ticket = ticket;
  }


  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }


  public java.util.Date getExpired() {
    return expired;
  }

  public void setExpired(java.util.Date expired) {
    this.expired = expired;
  }

}
