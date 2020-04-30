package com.digimaple.eims.model.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PwdDTO {


    private Integer sno;

    @Size(min = 5, max = 15,message = "密码长度只能在5~15之间" )
    private String password;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PwdDTO(Integer sno, String password) {
        this.sno = sno;
        this.password = password;
    }
}
