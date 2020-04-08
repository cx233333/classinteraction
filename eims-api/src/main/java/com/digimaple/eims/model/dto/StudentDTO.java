package com.digimaple.eims.model.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class StudentDTO {

  private Integer id;

  private Integer sNo;

  @Pattern(regexp="^[\\u4e00-\\u9fa5]{0,}$",message="只能输入汉字")
  @Size(min = 2,max = 10,message = "姓名不能小于2大于10")
  private String sname;

  @Pattern(regexp="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$",message="格式为13、15、18开头的11位手机号")
  private String phone;

  @Pattern(regexp="^[\\u4e00-\\u9fa5]{0,1}$",message="只能输入一个汉字（男/女）或不输入")
  private String gender;



  @Size(min = 5, max = 15,message = "密码长度只能在5~15之间" )
  private String st_pwd;

  private String class_name;

  private String xy_name;

  private Integer type;

  private Integer status;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getsNo() {
    return sNo;
  }

  public void setsNo(Integer sNo) {
    this.sNo = sNo;
  }

  public String getSname() {
    return sname;
  }

  public void setSname(String sname) {
    this.sname = sname;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getSt_pwd() {
    return st_pwd;
  }

  public void setSt_pwd(String st_pwd) {
    this.st_pwd = st_pwd;
  }

  public String getClass_name() {
    return class_name;
  }

  public void setClass_name(String class_name) {
    this.class_name = class_name;
  }

  public String getXy_name() {
    return xy_name;
  }

  public void setXy_name(String xy_name) {
    this.xy_name = xy_name;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
