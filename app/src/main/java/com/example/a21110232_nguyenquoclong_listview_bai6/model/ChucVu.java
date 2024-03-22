package com.example.a21110232_nguyenquoclong_listview_bai6.model;

public enum ChucVu {
    TruongPhong("Trưởng Phòng"),
    PhoPhong("Phó Phòng"),
    NhanVien("Nhân viên");
    private String cv;
    ChucVu(String cv)
    {
        this.cv=cv;
    }
    public String getChucVu() {
        return this.cv;
    }
}