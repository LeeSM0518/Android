package com.example.problem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ItemObject> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        new Description().execute();
    }

    private class Description extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);

            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            MyAdapter myAdapter = new MyAdapter(list);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(myAdapter);

            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect("http://www.hani.co.kr/arti/society/home01.html").get();

                Elements eElementDataSize = doc.select("div[class=section-list-area]").select("div");

                int i = 0;
                for (Element element : eElementDataSize) {
                    Log.d("i", String.valueOf(i));
                    String kind;
                    String title;
                    String context;
                    String date;
                    String imgUrl;

                    if (i > 0 && i % 2 == 1) {
                        kind = element.select("strong[class=category] a").text();
                        Log.d("kind", kind);
                        title = element.select("h4[class=article-title] a").text();
                        Log.d("title", title);
                        context = element.select("p[class=article-prologue] a").text();
                        Log.d("context", context);
                        imgUrl = element.select("span[class=article-photo] a img").attr("src");
                        Log.d("imgUrl", imgUrl);
                        date = element.select("p[class=article-prologue]").select("span[class=date]").text();
                        Log.d("date", date);

                        list.add(new ItemObject(imgUrl, title, date, context, kind));
                    }

                    i++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


}
