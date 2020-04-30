package com.digimaple.eims.model.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class updateTeacherPwdDTO {


    private Integer jsno;

    @Size(min = 5, max = 15,message = "密码长度只能在5~15之间" )
    private String newpwd;

    @Size(min = 5, max = 15,message = "密码长度只能在5~15之间" )
    private String oldpwd;

    public Integer getJsno() {
        return jsno;
    }

    public void setJsno(Integer jsno) {
        this.jsno = jsno;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String getOldpwd() {
        return oldpwd;
    }

    public void setOldpwd(String oldpwd) {
        this.oldpwd = oldpwd;
    }
}
