package com.digimaple.eims.model.sqlModel;

import javax.validation.constraints.Pattern;
import java.util.Date;

public class OutTable {

    private Integer id;

    @Pattern(regexp="^\\d+$",message="非负整数")
    private Integer InOrOutNum;

    private Integer goodsId;

    @Pattern(regexp="^\\d+$",message="非负整数")
    private Integer InOrOutId;

    //@Pattern(regexp="^[1-2]$",message="单据状态，只能输入1或2或者不输入")
    private Integer InOrOutStatus;

    private Date statusDate;

    //@Pattern(regexp="^[0-1]$",message="单据状态，只能输入0或1或者不输入")
    private Integer  exitStatus;

    @Override
    public String toString() {
        return "OutTable{" +
                "id=" + id +
                ", InOrOutNum=" + InOrOutNum +
                ", goodsId=" + goodsId +
                ", InOrOutId=" + InOrOutId +
                ", InOrOutStatus=" + InOrOutStatus +
                ", statusDate=" + statusDate +
                ", exitStatus=" + exitStatus +
                '}';
    }

    public Integer getInOrOutStatus() {
        return InOrOutStatus;
    }

    public void setInOrOutStatus(Integer inOrOutStatus) {
        InOrOutStatus = inOrOutStatus;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Integer getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(Integer exitStatus) {
        this.exitStatus = exitStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInOrOutNum() {
        return InOrOutNum;
    }

    public void setInOrOutNum(Integer inOrOutNum) {
        InOrOutNum = inOrOutNum;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getInOrOutId() {
        return InOrOutId;
    }

    public void setInOrOutId(Integer inOrOutId) {
        InOrOutId = inOrOutId;
    }
}
