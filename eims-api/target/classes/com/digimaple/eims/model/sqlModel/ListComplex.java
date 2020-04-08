package com.digimaple.eims.model.sqlModel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class ListComplex {
    private  Integer id;
    @Excel(name = "物资编号" ,orderNum = "0")
    private Integer goodsId;

    @Excel(name = "物资条形码" ,orderNum = "1")
    @Pattern(regexp="^[a-zA-Z]\\w{5,17}$",message="以字母开头，长度在6~18之间，只能包含字符、数字和下划线")
    private String    goodsBarcode;

    @Excel(name = "名称" ,orderNum = "2")
    @Size(min = 0, max = 10,message = "长度只能在0~10之间" )
    private String    goodsName;

    @Excel(name = "单位" ,orderNum = "3")
    @Pattern(regexp="^[\\u4e00-\\u9fa5]{0,2}$",message="只能输入0-2个汉字")
    private String    goodUnit;

    @Excel(name = "规格" ,orderNum = "4")
    @Size(min = 0, max = 10,message = "长度只能在0~10之间" )
    private String    goodsSpec;

    @Excel(name = "颜色" ,orderNum = "5")
    @Size(min = 0, max = 3,message = "长度只能在0~3之间" )
    private String    goodColor;

    @Excel(name = "品牌" ,orderNum = "6")
    @Size(min = 0, max = 10,message = "长度只能在0~10之间" )
    private String    goodsBrand;

    @Excel(name = "出库数量" ,orderNum = "7")
    @Pattern(regexp="^[0-9]*$",message="只能输入数字")
    private Integer    inOrOutNum;//InOrOutNum

    @Excel(name = "库存量" ,orderNum = "8")
    @Pattern(regexp="^[0-9]*$",message="只能输入数字")
    private Integer   goodsStock;

    @Excel(name = "处理人" ,orderNum = "9")
    private String    userName;

    @Excel(name = "仓库名称" ,orderNum = "10")
    private String    houseName;

    @Excel(name = "出库时间" ,orderNum = "11",databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd")
    private Date      makeDate;

    private Integer warnNum;

    public Integer getWarnNum() {
        return warnNum;
    }

    public void setWarnNum(Integer warnNum) {
        this.warnNum = warnNum;
    }

    @Pattern(regexp="^\\d+$",message="非负整数")
    private String    inOrOutId;

    //用于存放剩余库存
    private Integer InNum;
    private Integer OutNum;
    //用于判断状态
    private String InOrOutStatus;

    //用于存放第二个仓库
    private String houseName_out;

    public String getHouseName_out() {
        return houseName_out;
    }
    //
    private Integer houseName_Id;
    private Integer houseName_outId;

    public Integer getHouseName_Id() {
        return houseName_Id;
    }

    public void setHouseName_Id(Integer houseName_Id) {
        this.houseName_Id = houseName_Id;
    }

    public Integer getHouseName_outId() {
        return houseName_outId;
    }

    public void setHouseName_outId(Integer houseName_outId) {
        this.houseName_outId = houseName_outId;
    }

    public void setHouseName_out(String houseName_out) {
        this.houseName_out = houseName_out;
    }

    public String getInOrOutStatus() {
        return InOrOutStatus;
    }

    public void setInOrOutStatus(String inOrOutStatus) {
        InOrOutStatus = inOrOutStatus;
    }

    //用于统计图表

    public Integer getInNum() {
        return InNum;
    }

    public void setInNum(Integer inNum) {
        InNum = inNum;
    }

    public Integer getOutNum() {
        return OutNum;
    }

    public void setOutNum(Integer outNum) {
        OutNum = outNum;
    }



    public ListComplex(){
        super();
    }

    public ListComplex(Integer goodsId, String goodsBarcode, String goodsName, String goodUnit, String goodsSpec, String goodColor, String goodsBrand, Integer inOrOutNum, Integer goodsStock, String userName, String houseName, Date makeDate, String inOrOutId,Integer InNum, Integer OutNum) {
        this.goodsId = goodsId;
        this.goodsBarcode = goodsBarcode;
        this.goodsName = goodsName;
        this.goodUnit = goodUnit;
        this.goodsSpec = goodsSpec;
        this.goodColor = goodColor;
        this.goodsBrand = goodsBrand;
        this.inOrOutNum = inOrOutNum;//this.InOrOutNum
        this.goodsStock = goodsStock;
        this.userName = userName;
        this.houseName = houseName;
        this.makeDate = makeDate;
        this.inOrOutId = inOrOutId;
        this.InNum = InNum;
        this.OutNum = OutNum;
    }

    @Override
    public String toString() {
        return "ListComplex{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", goodsBarcode='" + goodsBarcode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodUnit='" + goodUnit + '\'' +
                ", goodsSpec='" + goodsSpec + '\'' +
                ", goodColor='" + goodColor + '\'' +
                ", goodsBrand='" + goodsBrand + '\'' +
                ", InOrOutNum=" + inOrOutNum +
                ", goodsStock=" + goodsStock +
                ", userName='" + userName + '\'' +
                ", houseName='" + houseName + '\'' +
                ", makeDate=" + makeDate +
                ", warnNum=" + warnNum +
                ", inOrOutId='" + inOrOutId + '\'' +
                ", InNum=" + InNum +
                ", OutNum=" + OutNum +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsBarcode() {
        return goodsBarcode;
    }

    public void setGoodsBarcode(String goodsBarcode) {
        this.goodsBarcode = goodsBarcode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodUnit() {
        return goodUnit;
    }

    public void setGoodUnit(String goodUnit) {
        this.goodUnit = goodUnit;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public String getGoodColor() {
        return goodColor;
    }

    public void setGoodColor(String goodColor) {
        this.goodColor = goodColor;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public Integer getInOrOutNum() {
        return inOrOutNum;
    }

    public void setInOrOutNum(Integer inOrOutNum) {
        this.inOrOutNum = inOrOutNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Date getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(Date makeDate) {
        this.makeDate = makeDate;
    }

    public String getInOrOutId() {
        return inOrOutId;
    }

    public void setInOrOutId(String inOrOutId) {
        this.inOrOutId = inOrOutId;
    }
}
