package com.digimaple.eims.model.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PwdDTO {

    @Size(min = 2,max = 10,message = "姓名或账号不能小于2大于10")
    private String sname;

    @Size(min = 5, max = 15,message = "密码长度只能在5~15之间" )
    private String stpwd;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getStpwd() {
        return stpwd;
    }

    public void setStpwd(String stpwd) {
        this.stpwd = stpwd;
    }

    public PwdDTO(String sname, String stpwd) {
        this.sname = sname;
        this.stpwd = stpwd;
    }
}
