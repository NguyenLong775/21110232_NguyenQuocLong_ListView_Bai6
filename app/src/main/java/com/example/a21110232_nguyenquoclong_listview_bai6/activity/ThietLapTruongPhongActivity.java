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

public class ThietLapTruongPhongActivity extends Activity {
    ListView lvTruongPhong, lvPhoPhong;
    ArrayList<NhanVien> arrNvForTP = new ArrayList<>();
    ArrayAdapter<NhanVien> adapterForTP;
    ArrayList<NhanVien> arrNvForPP = new ArrayList<>();
    ArrayAdapter<NhanVien> adapterForPP;
    ImageButton btnApply;
    int lastChecked = -1;
    PhongBan pb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thiet_lap_truong_phong);
        getFormWidgets();
    }

    public void getFormWidgets() {
        lvTruongPhong = findViewById(com.example.a21110232_nguyenquoclong_listview_bai6.R.id.lvtruongphong);
        lvTruongPhong.setTextFilterEnabled(true);
        lvTruongPhong.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvTruongPhong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            boolean somethingChecked = false;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrNvForTP.get(position).setChucVu(ChucVu.TruongPhong);
                if (somethingChecked) {
                    CheckedTextView cv = (CheckedTextView) view;
                    cv.setChecked(false);
                }
                CheckedTextView cv = (CheckedTextView) view;
                if (!cv.isChecked()) {
                    cv.setChecked(true);
                    arrNvForTP.get(position).setChucVu(ChucVu.TruongPhong);
                } else {
                    arrNvForTP.get(position).setChucVu(ChucVu.NhanVien);
                }
                lastChecked = position;
                somethingChecked = true;
            }
        });

        lvPhoPhong = findViewById(R.id.lvphophong);
        lvPhoPhong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView cv = (CheckedTextView) view;
                if (!cv.isChecked()) {
                    cv.setChecked(true);
                    arrNvForPP.get(position).setChucVu(ChucVu.PhoPhong);
                } else {
                    cv.setChecked(false);
                    arrNvForPP.get(position).setChucVu(ChucVu.NhanVien);
                }
            }
        });

        adapterForTP = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, arrNvForTP);
        adapterForPP = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, arrNvForPP);

        lvTruongPhong.setAdapter(adapterForTP);
        lvPhoPhong.setAdapter(adapterForPP);

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("DATA");
        pb = (PhongBan) bundle.getSerializable("PHONGBAN");
        addNvToListTP(pb);
        addNvToListPP(pb);
        adapterForTP.notifyDataSetChanged();
        adapterForPP.notifyDataSetChanged();

        btnApply = findViewById(R.id.imaapply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doApply();
            }
        });
    }

    /**
     * Gửi thông tin lại MainActivity sau khi thiết lập
     */
    public void doApply() {
        Intent i = getIntent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("PHONGBAN", pb);
        i.putExtra("DATA", bundle);
        setResult(MainActivity.THIET_LAP_TP_PP_THANHCONG, i);
        finish();
    }

    /**
     * Duyệt toàn bộ nhân viên vào danh sách ứng viên Trưởng phòng
     */
    public void addNvToListTP(PhongBan pb) {
        arrNvForTP.clear();
        for (NhanVien nv : pb.getListNhanVien()) {
            arrNvForTP.add(nv);
        }
    }

    /**
     * Duyệt toàn bộ nhân viên vào danh sách ứng viên phó phòng
     */
    public void addNvToListPP(PhongBan pb) {
        arrNvForPP.clear();
        for (NhanVien nv : pb.getListNhanVien()) {
            arrNvForPP.add(nv);
        }
    }
}
