package com.digimaple.eims.model.sqlModel;

public class CheckFind {

    private Integer id;
    private String barcode;
    private String goodsName;
    private String warehouseName;

    @Override
    public String toString() {
        return "CheckFind{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                '}';
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}
