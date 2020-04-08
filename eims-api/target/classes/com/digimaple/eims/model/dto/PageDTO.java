package com.digimaple.eims.model.dto;

import javax.validation.constraints.Pattern;

public class PageDTO {
    @Pattern(regexp="^\\d+$",message="非负整数")
    private Integer CurrPage;

    @Pattern(regexp="^\\d+$",message="非负整数")
    private Integer pageSize;

    public PageDTO(Integer CurrPage, Integer pageSize) {
        this.CurrPage = CurrPage;
        this.pageSize = pageSize;
    }

    public Integer getCurrPage() {
        return CurrPage;
    }

    public void setCurrPage(Integer CurrPage) {
        this.CurrPage = CurrPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
