package com.example.a21110232_nguyenquoclong_listview_bai6.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.a21110232_nguyenquoclong_listview_bai6.R;
import com.example.a21110232_nguyenquoclong_listview_bai6.model.ChucVu;
import com.example.a21110232_nguyenquoclong_listview_bai6.model.NhanVien;

public class ThemNhanVienActivity extends Activity {
    private Button btnXoaTrang, btnLuuNhanVien;
    private EditText editManv, editTenNv;
    private RadioButton radNam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.a21110232_nguyenquoclong_listview_bai6.R.layout.activity_them_nhan_vien);
        getFormWidgets();
        addEvents();
    }

    public void getFormWidgets() {
        btnXoaTrang = findViewById(R.id.btnxoatrang);
        btnLuuNhanVien = findViewById(R.id.btnluunv);
        editManv = findViewById(R.id.editMaNV);
        editTenNv = findViewById(R.id.editTenNV);
        radNam = findViewById(R.id.radNam);
    }

    public void addEvents() {
        btnXoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                doXoaTrang();
            }
        });

        btnLuuNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                doLuuNhanVien();
            }
        });
    }

    public void doXoaTrang() {
        editManv.setText("");
        editTenNv.setText("");
        editManv.requestFocus();
    }

    public void doLuuNhanVien() {
        NhanVien nv = new NhanVien();
        nv.setMa(editManv.getText().toString());
        nv.setTen(editTenNv.getText().toString());
        nv.setChucVu(ChucVu.NhanVien);
        nv.setGioiTinh(!radNam.isChecked());

        Intent i = getIntent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("NHANVIEN", nv);
        i.putExtra("DATA", bundle);
        setResult(MainActivity.THEM_NHAN_VIEN_THANHCONG, i);
        finish();
    }
}
