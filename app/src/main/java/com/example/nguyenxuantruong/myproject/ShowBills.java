package com.example.nguyenxuantruong.myproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nguyenxuantruong.myproject.Data.MyDB;
import com.example.nguyenxuantruong.myproject.targets.Bill;

import java.util.ArrayList;
import java.util.List;


public class ShowBills extends AppCompatActivity {

        MyDB db;
        List<Bill> model=new ArrayList<>();
        BillAdapter adapter=null;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.showbill);
            db = new MyDB(this);
            initData();
            ListView list=(ListView)findViewById(R.id.showBillB);
            adapter=new BillAdapter();
            list.setAdapter(adapter);

        }
        public void initData(){
            model = db.getData();

        }

        class BillAdapter extends ArrayAdapter<Bill> {
            BillAdapter(){
                super(ShowBills.this,R.layout.showbillrow,model);
            }
            public View getView(int position, View convertView, ViewGroup parent){
                View row= convertView;
                BillHolder holder=null;
                if(row==null){
                    LayoutInflater inflater=getLayoutInflater();
                    row=inflater.inflate(R.layout.showbillrow,parent,false);
                    holder =new  BillHolder(row);
                    row.setTag(holder);
                }
                else {
                    holder=(BillHolder) row.getTag();
                }
                holder.populateFrom(model.get(position));
                return row;
            }
        }

        static class BillHolder {
            private TextView NoidungB=null;
            private TextView dateB=null;
            private ImageView iconB=null;
            private TextView SotienB=null;
            private TextView loaiTK=null;
            private LinearLayout linearLayout=null;
            private ImageView iconHTTT=null; //icon hinh thuc thanh toan = atm or vi\
            private ImageView iTiGia=null;


            BillHolder(View row) {
                NoidungB=(TextView)row.findViewById(R.id.NoidungB);
                dateB=(TextView)row.findViewById(R.id.dateB);
                iconB=(ImageView)row.findViewById(R.id.iconB);
                SotienB=(TextView)row.findViewById(R.id.SotienB);
                loaiTK=(TextView)row.findViewById(R.id.loaiTaiKhoanB);
                linearLayout=(LinearLayout)row.findViewById(R.id.showrow);
                iconHTTT=(ImageView)row.findViewById(R.id.iconhinhthucthanhtoan);
                iTiGia=(ImageView)row.findViewById(R.id.tigia);
            }
            //yesterday once more:maxx hay
            void populateFrom(Bill r) {
                NoidungB.setText(r.getNoiDung());
                dateB.setText(r.getTime()+"");
                Log.e("dfhsdf",r.isChiTieu() +"");

                if(r.isChiTieu()){
                    switch (r.getNoiDung()){
                        case "Nhà nghỉ":iconB.setImageResource(R.drawable.hotel_r);break;
                        case "Bệnh viện":iconB.setImageResource(R.drawable.hospital_r);break;
                        case "Trường học":iconB.setImageResource(R.drawable.school_r);break;
                        case "Công việc":iconB.setImageResource(R.drawable.buy);break;
                        case "Gia đình":iconB.setImageResource(R.drawable.family_r);break;
                        case "Giải trí":iconB.setImageResource(R.drawable.entertainment_r);break;
                        case "Mua sắm":iconB.setImageResource(R.drawable.shopping_r);break;
                        case "Other":iconB.setImageResource(R.drawable.buy);break;
                    }

                    NoidungB.setTextColor(Color.parseColor("#f27b40"));
                    dateB.setTextColor(Color.parseColor("#f27b40"));
                    SotienB.setTextColor(Color.parseColor("#f27b40"));
                    loaiTK.setTextColor(Color.parseColor("#f27b40"));
                    iTiGia.setImageResource(R.drawable.dollarr);
                }
                else{
                    switch (r.getNoiDung()){

                        case "Nhà nghỉ":iconB.setImageResource(R.drawable.hotel_g);break;
                        case "Bệnh viện":iconB.setImageResource(R.drawable.hospital_g);break;
                        case "Trường học":iconB.setImageResource(R.drawable.education_g);break;
                        case "Công việc":iconB.setImageResource(R.drawable.buy);break;
                        case "Gia đình":iconB.setImageResource(R.drawable.family_g);break;
                        case "Giải trí":iconB.setImageResource(R.drawable.entertainment_g);break;
                        case "Mua sắm":iconB.setImageResource(R.drawable.shopping_g);break;
                        case "Other":iconB.setImageResource(R.drawable.buy);break;
                    }

                    NoidungB.setTextColor(Color.parseColor("#467da4"));
                    dateB.setTextColor(Color.parseColor("#467da4"));
                    SotienB.setTextColor(Color.parseColor("#467da4"));
                    loaiTK.setTextColor(Color.parseColor("#467da4"));
                    iTiGia.setImageResource(R.drawable.dollarg);
                }

//                if(r.getHinhthucthanhtoan().equals("vi")){
//                    loaiTK.setText("vi");
//                    iconHTTT.setImageResource(R.drawable.wallet);
//                }
//                else{
//                    loaiTK.setText("atm");
//                    iconHTTT.setImageResource(R.drawable.atm);
//                }

            }
        }
}
