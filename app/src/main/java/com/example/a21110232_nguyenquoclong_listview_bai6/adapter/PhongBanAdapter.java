package com.example.a21110232_nguyenquoclong_listview_bai6.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import com.example.a21110232_nguyenquoclong_listview_bai6.R;
import com.example.a21110232_nguyenquoclong_listview_bai6.model.NhanVien;
import com.example.a21110232_nguyenquoclong_listview_bai6.model.PhongBan;

public class PhongBanAdapter extends ArrayAdapter<PhongBan> {
    Activity context;
    int layoutId;
    ArrayList<PhongBan> arrPhongBan;

    public PhongBanAdapter(Activity context, int textViewResourceId, ArrayList<PhongBan> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.layoutId = textViewResourceId;
        this.arrPhongBan = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Gán layout vào coding
        convertView = context.getLayoutInflater().inflate(layoutId, null);

        // Lấy các control ra theo id
        TextView txtpb = convertView.findViewById(com.example.a21110232_nguyenquoclong_listview_bai6.R.id.txtShortInfo);
        TextView txtmotapb = convertView.findViewById(R.id.txtDetailInfo);

        // Lấy phòng ban thứ position
        PhongBan pb = arrPhongBan.get(position);
        txtpb.setText(pb.toString());

        // Các dòng lệnh dưới để kiểm tra Trưởng phòng, phó phòng
        String strMota = "";
        String tp = "Trưởng Phòng : [Chưa có]";
        NhanVien nv = pb.getTruongPhong();
        if (nv != null) {
            tp = "Trưởng Phòng: [" + nv.getTen() + "]";
        }

        ArrayList<NhanVien> dsPp = pb.getPhoPhong();
        String pp = "Phó Phòng : [Chưa có]";
        if (dsPp.size() > 0) {
            pp = "Phó phòng:\n";
            for (int i = 0; i < dsPp.size(); i++) {
                pp += (i + 1) + ", " + dsPp.get(i).getTen() + "\n";
            }
        }

        strMota = tp + "\n" + pp;
        // Gán thông tin cho phần chi tiết
        txtmotapb.setText(strMota);

        return convertView;
    }
}
