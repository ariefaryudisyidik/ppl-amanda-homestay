package com.excode.amandahomestay;

public class GetterSetter {
    private String tenantName, tenantDetail, roomNumber;
    private int tenantPhoto;

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantDetail() {
        return tenantDetail;
    }

    public void setTenantDetail(String tenantDetail) {
        this.tenantDetail = tenantDetail;
    }

    public int getTenantPhoto() {
        return tenantPhoto;
    }

    public void setTenantPhoto(int tenantPhoto) {
        this.tenantPhoto = tenantPhoto;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
