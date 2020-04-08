package com.digimaple.eims.model.sqlModel;

import java.util.Date;

public class CheckCount {
    private Integer id;
    private Date statusStaDate;
    private Date statusEndDate;
    private String userName;
    private String warehouseName;
    private String InOrOutStatus;

    @Override
    public String toString() {
        return "CheckCount{" +
                "id=" + id +
                ", statusStaDate=" + statusStaDate +
                ", statusEndDate=" + statusEndDate +
                ", userName='" + userName + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", InOrOutStatus=" + InOrOutStatus +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStatusStaDate() {
        return statusStaDate;
    }

    public void setStatusStaDate(Date statusStaDate) {
        this.statusStaDate = statusStaDate;
    }

    public Date getStatusEndDate() {
        return statusEndDate;
    }

    public void setStatusEndDate(Date statusEndDate) {
        this.statusEndDate = statusEndDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getInOrOutStatus() {
        return InOrOutStatus;
    }

    public void setInOrOutStatus(String inOrOutStatus) {
        InOrOutStatus = inOrOutStatus;
    }
}
