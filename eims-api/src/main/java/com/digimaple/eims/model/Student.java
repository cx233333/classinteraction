package com.digimaple.eims.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

@Data
public class Student {

  @Excel(name = "stid", orderNum = "0")
  private Integer stid;

  @Excel(name = "学号", orderNum = "1")
  private Integer sNo;

  @Excel(name = "姓名", orderNum = "2")
  @NotNull(message = "姓名不能为空", groups = Default.class)
  private String sname;

  @Excel(name = "电话", orderNum = "3")
  @Length(min = 11, max = 11, message = "电话长度必须为11位")
  private String phone;

  @Excel(name = "性别", orderNum = "4")
  private String gender;

  @Excel(name = "专业", orderNum = "5")
  private String classname;

  @Excel(name = "学院", orderNum = "6")
  @NotNull(message = "学院不能为空", groups = Default.class)
  private String xyname;


  @Excel(name = "密码", orderNum = "7")
  @Size(min = 5, max = 20, message = "密码长度只能在5~20之间", groups = Default.class)
  private String stpwd;

  @Excel(name = "角色类型", orderNum = "8")
  private Integer types;

  @Excel(name = "登录状态", orderNum = "9")
  private Integer statuss;

  public Integer getStid() {
    return stid;
  }

  public void setStid(Integer stid) {
    this.stid = stid;
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

  public String getClassname() {
    return classname;
  }

  public void setClassname(String classname) {
    this.classname = classname;
  }

  public String getXyname() {
    return xyname;
  }

  public void setXyname(String xyname) {
    this.xyname = xyname;
  }

  public String getStpwd() {
    return stpwd;
  }

  public void setStpwd(String stpwd) {
    this.stpwd = stpwd;
  }

  public Integer getTypes() {
    return types;
  }

  public void setTypes(Integer types) {
    this.types = types;
  }

  public Integer getStatuss() {
    return statuss;
  }

  public void setStatuss(Integer statuss) {
    this.statuss = statuss;
  }
}
