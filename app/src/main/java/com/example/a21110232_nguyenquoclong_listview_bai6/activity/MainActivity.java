package com.example.a21110232_nguyenquoclong_listview_bai6.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import com.example.a21110232_nguyenquoclong_listview_bai6.R;
import com.example.a21110232_nguyenquoclong_listview_bai6.adapter.*;
import com.example.a21110232_nguyenquoclong_listview_bai6.model.*;

public class MainActivity extends AppCompatActivity {
    public static final int MO_ACTIVITY_THEM_NHAN_VIEN = 1;
    public static final int MO_ACTIVITY_SUA_NHAN_VIEN = 2;
    public static final int THEM_NHAN_VIEN_THANHCONG = 3;
    public static final int SUA_NHAN_VIEN_THANHCONG = 4;
    public static final int XEM_DS_NHAN_VIEN = 5;
    public static final int CAPNHAT_DS_NHAN_VIEN_THANHCONG = 6;
    public static final int MO_ACTIVITY_THIET_LAP_TP_PP = 7;
    public static final int THIET_LAP_TP_PP_THANHCONG = 8;
    public static final int MO_ACTIVITY_CHUYENPHONG = 9;
    public static final int CHUYENPHONG_THANHCONG = 10;

    private Button btnLuuPb;
    private EditText editMapb, editTenpb;
    private ListView lvpb;
    private static ArrayList<PhongBan> arrPhongBan = new ArrayList<PhongBan>();
    private PhongBanAdapter adapterPb = null;
    private PhongBan pbSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.a21110232_nguyenquoclong_listview_bai6.R.layout.activity_main);
        getFormWidgets();
        addEvents();
        fakeData();
    }

    public void fakeData() {
        NhanVien nv = null;
        PhongBan pb = new PhongBan("pb1", "Kỹ thuật");
        nv = new NhanVien("nv1", "Nguyễn Thị Ánh Tuyết", true);
        nv.setChucVu(ChucVu.TruongPhong);
        pb.themNV(nv);
        nv = new NhanVien("nv2", "Trần Hoàng Đức Thiện", false);
        nv.setChucVu(ChucVu.PhoPhong);
        pb.themNV(nv);
        nv = new NhanVien("nv3", "Nguyễn Trọng Đại", false);
        nv.setChucVu(ChucVu.TruongPhong);
        pb.themNV(nv);
        arrPhongBan.add(pb);
        pb = new PhongBan("pb2", "Dịch vụ");
        arrPhongBan.add(pb);
        pb = new PhongBan("pb3", "Truyền thông");
        arrPhongBan.add(pb);
        nv = new NhanVien("m1", "Nhanh Như Chớp", false);
        nv.setChucVu(ChucVu.NhanVien);
        pb.themNV(nv);
        nv = new NhanVien("m2", "Chậm như Rùa", true);
        nv.setChucVu(ChucVu.TruongPhong);
        pb.themNV(nv);
        nv = new NhanVien("m3", "Lê Thị Lết", false);
        nv.setChucVu(ChucVu.NhanVien);
        pb.themNV(nv);
        adapterPb.notifyDataSetChanged();
    }

    public void getFormWidgets() {
        btnLuuPb = findViewById(R.id.btnluupb);
        editMapb = findViewById(R.id.editmapb);
        editTenpb = findViewById(R.id.editTenpb);
        lvpb = findViewById(R.id.lvphongban);

        adapterPb = new PhongBanAdapter(this, R.layout.layout_item_custom, arrPhongBan);
        lvpb.setAdapter(adapterPb);

        registerForContextMenu(lvpb);
    }

    public void addEvents() {
        btnLuuPb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLuuPhongBan();
            }
        });

        lvpb.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                pbSelected = arrPhongBan.get(position);
                return false;
            }
        });
    }

    public void doLuuPhongBan() {
        String mapb = editMapb.getText().toString();
        String tenpb = editTenpb.getText().toString();
        PhongBan pb = new PhongBan(mapb, tenpb);
        arrPhongBan.add(pb);
        adapterPb.notifyDataSetChanged();
        editMapb.setText("");
        editTenpb.setText("");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu_phongban, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnuthemnv) {
            doThemNhanVien();
            return true;
        } else if (id == R.id.mnudanhsachny) {
            doDanhSachNhanVien();
            return true;
        } else if (id == R.id.mnutruongphong) {
            doThietLapLanhDao();
            return true;
        } else if (id == R.id.mnuxoapb) {
            doXoaPhongBan();
            return true;
        }
        return super.onContextItemSelected(item);
    }


    public void doThemNhanVien() {
        Intent i = new Intent(this, ThemNhanVienActivity.class);
        startActivityForResult(i, MO_ACTIVITY_THEM_NHAN_VIEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == THEM_NHAN_VIEN_THANHCONG) {
            Bundle bundle = data.getBundleExtra("DATA");
            NhanVien nv = (NhanVien) bundle.getSerializable("NHANVIEN");
            pbSelected.themNV(nv);
            adapterPb.notifyDataSetChanged();
        }
    }

    public void doDanhSachNhanVien() {
        Intent i = new Intent(this, DanhSachNhanVienActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PHONGBAN", pbSelected);
        i.putExtra("DATA", bundle);
        startActivityForResult(i, XEM_DS_NHAN_VIEN);
    }

    public void doThietLapLanhDao() {
        Intent i = new Intent(this, ThietLapTruongPhongActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PHONGBAN", pbSelected);
        i.putExtra("DATA", bundle);
        startActivityForResult(i, MO_ACTIVITY_THIET_LAP_TP_PP);
    }

    public void doXoaPhongBan() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hỏi đáp");
        builder.setMessage("Bạn có chắc chắn muốn xóa [" + pbSelected.getTen() + "]");
        builder.setIcon(android.R.drawable.ic_input_delete);
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                arrPhongBan.remove(pbSelected);
                adapterPb.notifyDataSetChanged();
            }
        });
        builder.show();
    }

    public static ArrayList<PhongBan> getListPhongBan() {
        return arrPhongBan;
    }
}
