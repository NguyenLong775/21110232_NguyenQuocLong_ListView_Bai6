package com.example.a21110232_nguyenquoclong_listview_bai6.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.a21110232_nguyenquoclong_listview_bai6.R;
import com.example.a21110232_nguyenquoclong_listview_bai6.model.NhanVien;

public class SuaNhanVienActivity extends Activity {
    EditText editMa, editTen;
    RadioButton radNam;
    Button btnClear, btnSave;
    NhanVien nv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.a21110232_nguyenquoclong_listview_bai6.R.layout.activity_them_nhan_vien);
        getFormWidgets();
        getDefalultData();
        addEvents();
    }

    public void getFormWidgets() {
        editMa = findViewById(R.id.editMaNV);
        editTen = findViewById(R.id.editTenNV);
        radNam = findViewById(R.id.radNam);
        editMa.setEnabled(false);
        editTen.requestFocus();
        btnClear = findViewById(R.id.btnxoatrang);
        btnSave = findViewById(R.id.btnluunv);
    }

    /**
     * Hàm lấy giá trị từ DanhSachNhanVienActivity truyền qua để hiển thị
     */
    public void getDefalultData() {
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("DATA");
        nv = (NhanVien) b.getSerializable("NHANVIEN");
        editMa.setText(nv.getMa());
        editTen.setText(nv.getTen());
        radNam.setChecked(true);
        if (nv.isGioiTinh())
            radNam.setChecked(false);
    }

    public void addEvents() {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTen.setText("");
                editTen.requestFocus();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                nv.setTen(editTen.getText().toString());
                nv.setGioiTinh(!radNam.isChecked());
                Bundle b = new Bundle();
                b.putSerializable("NHANVIEN", nv);
                i.putExtra("DATA", b);
                setResult(MainActivity.SUA_NHAN_VIEN_THANHCONG, i);
                finish();
            }
        });
    }
}
