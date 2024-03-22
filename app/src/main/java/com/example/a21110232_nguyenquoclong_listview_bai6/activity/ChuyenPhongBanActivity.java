package com.example.a21110232_nguyenquoclong_listview_bai6.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.a21110232_nguyenquoclong_listview_bai6.R;
import com.example.a21110232_nguyenquoclong_listview_bai6.model.*;

public class ChuyenPhongBanActivity extends Activity {
    ListView lvPb;
    private static ArrayList<PhongBan> arrPhongBan = null;
    ArrayAdapter<PhongBan> adapter;
    ImageButton btnApply;
    NhanVien nv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyen_phong_ban);
        getFormWidgets();
        // lấy nhân viên từ màn hình xem danh sách nhân viên
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("DATA");
        nv = (NhanVien) b.getSerializable("NHANVIEN");
    }

    public void getFormWidgets() {
        lvPb = findViewById(com.example.a21110232_nguyenquoclong_listview_bai6.R.id.lvphongban);
        btnApply = findViewById(R.id.imapply);
        arrPhongBan = MainActivity.getListPhongBan();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, arrPhongBan);
        lvPb.setAdapter(adapter);

        lvPb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            boolean somethingChecked = false;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //hiển nhiên View arg1 là CheckedTextView
                if (somethingChecked) {
                    CheckedTextView cv = (CheckedTextView) view;
                    cv.setChecked(false);
                }
                CheckedTextView cv = (CheckedTextView) view;
                if (!cv.isChecked()) {
                    cv.setChecked(true);
                    arrPhongBan.get(position);
                }
                somethingChecked = true;
            }
        });

        //khi chọn nút Apply thì tiến hành đóng màn hình này và truyền lệnh về cho DanhSachNhanVienActivity
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doApply();
            }
        });
    }

    public void doApply() {
        setResult(MainActivity.CHUYENPHONG_THANHCONG);
        finish();
    }
}
