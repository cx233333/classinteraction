package com.digimaple.eims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Teacher {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer jsno;

    @Column(nullable = false, unique = true)
    private String jsname;

    @Column(nullable = false, unique = false)
    private String password;
    @Column(nullable = false, unique = false)
    private String gender;
    @Column(nullable = false, unique = false)
    private String phone;
  
    @Column(nullable = false, unique = false)
    private String xyname;
    @Column(nullable = false, unique = false)
    private String role;
    @Column(nullable = false, unique = false)
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJsno() {
        return jsno;
    }

    public void setJsno(Integer jsno) {
        this.jsno = jsno;
    }

    public String getJsname() {
        return jsname;
    }

    public Teacher setJsname(String jsname) {
        this.jsname = jsname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Teacher setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Teacher setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Teacher setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getXyname() {
        return xyname;
    }

    public Teacher setXyname(String xyname) {
        this.xyname = xyname;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Teacher setRole(String role) {
        this.role = role;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Teacher setStatus(String status) {
        this.status = status;
        return this;
    }
}
