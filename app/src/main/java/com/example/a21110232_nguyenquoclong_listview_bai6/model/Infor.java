package com.example.a21110232_nguyenquoclong_listview_bai6.model;

import java.io.Serializable;

public class Infor implements Serializable {
    private static final long serialVersionUID = 1L; // Sửa tên biến thành serialVersionUID

    private String ma;
    private String ten;

    public Infor(String ma, String ten) {
        super();
        this.ma = ma;
        this.ten = ten;
    }
    public Infor() {
        // super(); // Xóa dòng này
    }

    @Override
    public String toString() {
        return this.ma + " - " + this.ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
