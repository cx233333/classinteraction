package com.digimaple.eims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {

  @Id
  @GeneratedValue
  private Integer id;

  @Column(nullable = false, unique = true)
  private Integer sno;

  @Column(nullable = false, unique = true)
  private String sname;

  @Column(nullable = false, unique = false)
  private String password;
  @Column(nullable = false, unique = false)
  private String gender;
  @Column(nullable = false, unique = false)
  private String phone;
  @Column(nullable = false, unique = false)
  private String classname;
  @Column(nullable = false, unique = false)
  private String xyname;
  @Column(nullable = false, unique = false)
  private String role;
  @Column(nullable = false, unique = false)
  private String status;

  public Integer getId() {
    return id;
  }

  public Student setId(Integer id) {
    this.id = id;
    return this;
  }

  public Integer getSno() {
    return sno;
  }

  public void setSno(Integer sno) {
    this.sno = sno;
  }

  public String getSname() {
    return sname;
  }

  public Student setSname(String sname) {
    this.sname = sname;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public Student setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getGender() {
    return gender;
  }

  public Student setGender(String gender) {
    this.gender = gender;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public Student setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public String getClassname() {
    return classname;
  }

  public Student setClassname(String classname) {
    this.classname = classname;
    return this;
  }

  public String getXyname() {
    return xyname;
  }

  public Student setXyname(String xyname) {
    this.xyname = xyname;
    return this;
  }

  public String getRole() {
    return role;
  }

  public Student setRole(String role) {
    this.role = role;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public Student setStatus(String status) {
    this.status = status;
    return this;
  }
}
