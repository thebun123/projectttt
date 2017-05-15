package com.example.nguyenxuantruong.myproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenxuantruong.myproject.Data.GetData;
import com.example.nguyenxuantruong.myproject.Data.Money;
import com.example.nguyenxuantruong.myproject.Graph.GraphActivity;
import com.example.nguyenxuantruong.myproject.Time.Day;
import com.example.nguyenxuantruong.myproject.targets.Targets;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView date;
    private ImageView setting;
    private Button thongKe, chiTieu, guiTK, thoat;
    SharedPreferences mySetting;
    Money money1,money2;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = getIntent();
//        type = intent.getStringExtra("typemoney");
//        Log.e("TAG",type);

        mySetting = getApplicationContext().getSharedPreferences(GetData.fileName,0);

        money1 = new Money();
        money2 = new Money();
        money1.setType("USD (Dưới 5 USD)");
        money1.setValue(mySetting.getFloat("USD (Dưới 5 USD)",(float) 34.43));
        money2.setType("EUR");
        money2.setValue(194);
        Toast.makeText(this,"money1 :"+ money1.getValue() +"\n" +
                        "money2 :"+ money2.getValue() + "\n" + new Money().exchange(money1,money2)+"",
                Toast.LENGTH_LONG).show();
        initView();
        showToday();
    }

    private void showToday() {
        Day day = new Day();
        date.setText(day.today());
    }

    private void initView() {
        //findView
        setting = (ImageView) findViewById(R.id.setting);
        date = (TextView) findViewById(R.id.tvToday);
        thongKe = (Button) findViewById(R.id.bThong_ke);
        chiTieu = (Button) findViewById(R.id.bChi_tieu);
        guiTK = (Button) findViewById(R.id.bGui_tk);
        thoat = (Button) findViewById(R.id.bOut);

        //setOnclick
        setting.setOnClickListener(this);
        thongKe.setOnClickListener(this);
        chiTieu.setOnClickListener(this);
        guiTK.setOnClickListener(this);
        thoat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.setting :
                Intent t = new Intent("android.intent.action.PREF");
                startActivity(t);
                break;

            case R.id.bThong_ke :
                Intent i = new Intent(MainActivity.this,GraphActivity.class);
                startActivity(i);
                break;

            case R.id.bChi_tieu :
                Intent intent = new Intent(MainActivity.this, Targets.class);
                intent.putExtra("type","add");
                startActivity(intent);
                break;

            case R.id.bGui_tk :
                Intent intent1=new Intent(MainActivity.this,ShowBills.class);
                startActivity(intent1);
                break;

            case R.id.bOut :
                finish();
                break;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode==RESULT_OK){
//            if(requestCode==1){
//                Log.e("da",data.getStringExtra("noidung"));
//
//                Bill bill = new Bill(19, 8, 2017, true, "mua Mac", "26000000", "Macbook pro 2015", "atm");
//                bill.setSoTien(data.getStringExtra("tien"));
//                bill.setSoTien(data.getStringExtra("ghichu"));
//                bill.setSoTien(data.getStringExtra("nam") +"");
//                bill.setSoTien(data.getStringExtra("ngay")+"");
//                bill.setSoTien(data.getStringExtra("thang")+"");
//                bill.setSoTien(data.getStringExtra("noidung"));
//                bill.setSoTien(data.getStringExtra("chitieu"));
//
////                Log.e("data","tien" + " : " + data.getStringExtra("tien"));
////                Log.e("data","ghichu" + " : " + data.getStringExtra("ghichu"));
////                Log.e("data","nam" + " : " + data.getStringExtra("nam") +"");
////                Log.e("data","ngay" + " : " + data.getStringExtra("ngay")+"");
////                Log.e("data","thang" + " : " + data.getStringExtra("thang")+"");
////                Log.e("data","noidung" + " : " + data.getStringExtra("noidung"));
////                Log.e("data","chitieu" + " : " + data.getStringExtra("chitieu"));
////                Log.e("data","kyhan" + " : " + data.getStringExtra("kyhan"));
//
////                Log.e("done",bill.getNgayPhatSinh()+"");
////                Log.e("done",bill.getThangPhatSinh() +"");
////                Log.e("done",bill.getNamPhatSinh() +"");
////                Log.e("done",bill.getSoTien());
////                Log.e("done",bill.getNoiDung());
////                Log.e("done",bill.getGhiChu());
////                Log.e("done",bill.isChiTieu() +"");
//            }
//        }
//
//    }
}
