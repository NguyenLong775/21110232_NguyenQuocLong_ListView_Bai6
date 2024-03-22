package com.example.a21110232_nguyenquoclong_listview_bai6.model;

import java.io.Serializable;

public class NhanVien extends Infor implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean gioiTinh;
    private ChucVu chucVu;
    private PhongBan phongBan;

    public NhanVien(String ma, String ten, boolean gioiTinh, ChucVu chucVu, PhongBan phongBan) {
        super(ma, ten);
        this.gioiTinh = gioiTinh;
        this.chucVu = chucVu;
        this.phongBan = phongBan;
    }

    public NhanVien(String ma, String ten, boolean gioiTinh) {
        super(ma, ten);
        this.gioiTinh = gioiTinh;
    }

    public NhanVien() {
        super(); // Di chuyển dòng super() vào đúng vị trí
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean isGioiTinh() {
        return gioiTinh; // Thêm return vào hàm isGioiTinh()
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public ChucVu getChucVu() { // Sửa tên hàm và biến thành chucVu
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) { // Sửa tên biến thành chucVu
        this.chucVu = chucVu;
    }

    public PhongBan getPhongBan() { // Sửa tên hàm và biến thành phongBan
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) { // Sửa tên biến thành phongBan
        this.phongBan = phongBan;
    }
}
