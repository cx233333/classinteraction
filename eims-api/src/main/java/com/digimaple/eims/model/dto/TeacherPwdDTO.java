package com.digimaple.eims.model.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class TeacherPwdDTO {


    private Integer jsno;

    @Size(min = 5, max = 15,message = "密码长度只能在5~15之间" )
    private String password;

    public Integer getJsno() {
        return jsno;
    }

    public void setJsno(Integer jsno) {
        this.jsno = jsno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TeacherPwdDTO(Integer jsno, String password) {
        this.jsno = jsno;
        this.password = password;
    }
}
