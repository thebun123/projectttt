package com.example.nguyenxuantruong.myproject.Data;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.nguyenxuantruong.myproject.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GetData extends AppCompatActivity {

    TextView name,svm;
    public SharedPreferences prf;
    public static String fileName ="MyString";
    Money money1,money2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        money1 = new Money();
        money2 = new Money();
        name = (TextView) findViewById(R.id.tvName);
        svm = (TextView) findViewById(R.id.tvSvm);
        prf = getSharedPreferences(fileName,0);
            new MyAsynTask().execute("http://www.24h.com.vn/ttcb/tygia/tygia.php#");

        //Log.e("connected",isconnect +"");
        //Toast.makeText(this,prf.getFloat("USD (Dưới 5 USD)", (float) 34.43) +"",Toast.LENGTH_LONG).show();
//        money1.setType("USD (Dưới 5 USD)");
//        money1.setValue(prf.getFloat("USD (Dưới 5 USD)",(float) 34.43));
//        money2.setType("EUR");
//        money2.setValue(194);
//        Toast.makeText(this,"money1 :"+ money1.getValue() +"\n" +
//                            "money2 :"+ money2.getValue() + "\n" + new Money().exchange(money1,money2)+"",
//                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public SharedPreferences getPrf() {
        return prf;
    }

    public void setPrf(SharedPreferences prf) {
        this.prf = prf;
    }

    public class MyAsynTask extends AsyncTask<String,Void,Void>{

        SharedPreferences.Editor editor = prf.edit();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            svm.setText("Loading...");
        }

        @Override
        protected Void doInBackground(String... params) {

            try {
                Document document = Jsoup.connect(params[0]).get();
                Elements elements = document.select("div.colCenter");
               // String data = document.select("mete[table class=mb-table--tygia]").first().attr("content");

                int k=0;
                for(Element table : document.select("table.mb-table--tygia")){
                    for (Element row : table.select("tr")) {
                        k++;
                        Elements tds = row.select("td");
                        //Log.e("td",tds.get(0).text() +" : " + tds.get(1).text());
//                        if (tds.size() > 4) {
//                            Log.e("hahat",tds.get(2).toString() + ":" + tds.get(3).toString());
//                        }
                        if(k>2&&k<7){
                            String type =tds.get(0).text();
                            float value = Float.valueOf((tds.get(1).text()).replace(",","."));
                            if(tds.get(0).text().toString().equals("USD (Dưới 5 USD)")){
                                //Log.e("check",tds.get(1).text());
                                editor.putFloat(type,value);
                            }

                            if(tds.get(0).text().toString().equals("EUR")){
                                //Log.e("check",tds.get(1).text());
                                editor.putFloat(type,value);
                                //Log.e("check", type + " : "  + value);
                            }
                        }

                    }
                }
                editor.commit();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                svm.setText("Data ok!");
                Thread.sleep(2000);
                Intent i = new Intent("android.intent.action.MAINACTIVITY");
                String typeMoney = prf.getString("typeMoney","VND");
                Log.e("Tysad",typeMoney);
                startActivity(i);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        }
    }
    private boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
