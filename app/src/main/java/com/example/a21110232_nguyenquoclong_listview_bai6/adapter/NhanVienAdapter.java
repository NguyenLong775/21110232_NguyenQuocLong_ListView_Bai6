package com.example.a21110232_nguyenquoclong_listview_bai6.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import com.example.a21110232_nguyenquoclong_listview_bai6.R;
import com.example.a21110232_nguyenquoclong_listview_bai6.model.NhanVien;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int layoutId;
    ArrayList<NhanVien> arrNhanVien;

    public NhanVienAdapter(Activity context, int textViewResourceId, ArrayList<NhanVien> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.layoutId = textViewResourceId;
        this.arrNhanVien = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Gán XML Layout vào coding
        convertView = context.getLayoutInflater().inflate(layoutId, null);

        // Lấy các control ra
        TextView txtnv = convertView.findViewById(com.example.a21110232_nguyenquoclong_listview_bai6.R.id.txtShortInfo);
        TextView txtmotanv = convertView.findViewById(R.id.txtDetailInfo);
        ImageView img = convertView.findViewById(R.id.imgview);

        // Lấy nhân viên thứ position
        NhanVien nv = arrNhanVien.get(position);
        txtnv.setText(nv.toString());

        String strMota = "";
        String cv = "Chức vụ: " + nv.getChucVu().getChucVu();
        String gt = "Giới tính: " + (nv.isGioiTinh() ? "Nữ" : "Nam");

        // Kiểm tra giới tính để gán cho đúng hình đại diện
        if (!nv.isGioiTinh())
            img.setImageResource(R.drawable.department);
        else
            img.setImageResource(R.drawable.girlicon);

        strMota = cv + "\n" + gt;
        txtmotanv.setText(strMota);

        return convertView;
    }
}
