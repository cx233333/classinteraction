package com.digimaple.eims.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

  private Integer id;

  @Pattern(regexp="^[\\u4e00-\\u9fa5]{0,}$",message="只能输入汉字")
  @Size(min = 2,max = 10,message = "姓名不能小于2大于10")
  private String name;

  @Pattern(regexp="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$",message="格式为13、15、18开头的11位手机号")
  private String tel;

  @Pattern(regexp="^[\\u4e00-\\u9fa5]{0,1}$",message="只能输入一个汉字（男/女）或不输入")
  private String gender;

  private String addr;

  @Pattern(regexp="^[a-zA-Z]\\w{5,17}$",message="以字母开头，长度在6~18之间，只能包含字符、数字和下划线")
  private String code;

  private String status;

  @Size(min = 5, max = 15,message = "密码长度只能在5~15之间" )
  private String password;

  private String deptId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }


  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getDeptId() {
    return deptId;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

}
