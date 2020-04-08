package com.digimaple.eims.model.sqlModel;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class GoodsList {

    private Integer id;

    @Pattern(regexp="^[a-zA-Z]\\w{5,17}$",message="以字母开头，长度在6~18之间，只能包含字符、数字和下划线")
    private String barcode;

    @Size(min = 0, max = 10,message = "长度只能在0~10之间" )
    private String name;

    @Pattern(regexp="^[\\u4e00-\\u9fa5]{0,2}$",message="只能输入0-2个汉字")
    private String unit;

    @Size(min = 0, max = 10,message = "长度只能在0~10之间" )
    private String spec;

    @Size(min = 0, max = 3,message = "长度只能在0~3之间" )
    private String color;

    @Size(min = 0, max = 10,message = "长度只能在0~10之间" )
    private String brand;

    @Pattern(regexp="^[0-9]*$",message="只能输入数字")
    private Integer stock;

    //@Pattern(regexp="^[0-1]$",message="存在状态，只能输入0或1或者不输入")
    private Integer exitStatus;

    @Pattern(regexp="^[0-9]*$",message="只能输入数字")
    private Integer warnNum;

    public Integer getWarnNum() {
        return warnNum;
    }

    public void setWarnNum(Integer warnNum) {
        this.warnNum = warnNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(Integer exitStatus) {
        this.exitStatus = exitStatus;
    }
}
