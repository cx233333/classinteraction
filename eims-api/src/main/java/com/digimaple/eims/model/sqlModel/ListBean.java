package com.digimaple.eims.model.sqlModel;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ListBean<T> {

    private Integer id;

    //@Pattern(regexp="^\\d+$",message="非负整数")
    private String inOrOutId;

    private Date makeDate;

    //@Pattern(regexp="^[0-2]$",message="单据状态，只能输入1或2或者不输入")
    private Integer inOrOutStatus;

    private String userId;

    private Integer warehouseId;

    //@Pattern(regexp="^[0-1]$",message="存在状态，只能输入0或1或者不输入")
    private Integer exitStatus;

    //用于显示出入库id
    private  Integer houseName_in;
    private Integer houseName_out;

    private List<T> listComplexes;

    //@Pattern(regexp="^[0-1]$",message="提交状态，只能输入0或1或者不输入")
    private Integer status;//提交状态

    public List<T> getListComplexes() {
        return listComplexes;
    }

    public void setListComplexes(List<T> listCom) {
        this.listComplexes = listCom;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInOrOutId() {
        return inOrOutId;
    }

    public void setInOrOutId(String inOrOutId) {
        this.inOrOutId = inOrOutId;
    }

    public Date getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(Date makeDate) {
        this.makeDate = makeDate;
    }

    public Integer getInOrOutStatus() {
        return inOrOutStatus;
    }

    public void setInOrOutStatus(Integer inOrOutStatus) {
        this.inOrOutStatus = inOrOutStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(Integer exitStatus) {
        this.exitStatus = exitStatus;
    }

    public Integer getHouseName_in() {
        return houseName_in;
    }

    public void setHouseName_in(Integer houseName_in) {
        this.houseName_in = houseName_in;
    }

    public Integer getHouseName_out() {
        return houseName_out;
    }

    public void setHouseName_out(Integer houseName_out) {
        this.houseName_out = houseName_out;
    }

    @Override
    public String toString() {
        return "ListBean{" +
                "id=" + id +
                ", inOrOutId='" + inOrOutId + '\'' +
                ", makeDate=" + makeDate +
                ", inOrOutStatus=" + inOrOutStatus +
                ", userId='" + userId + '\'' +
                ", warehouseId=" + warehouseId +
                ", exitStatus=" + exitStatus +
                ", houseName_in='" + houseName_in + '\'' +
                ", houseName_out='" + houseName_out + '\'' +
                ", listComplexes=" + listComplexes +
                ", status=" + status +
                '}';
    }
}
