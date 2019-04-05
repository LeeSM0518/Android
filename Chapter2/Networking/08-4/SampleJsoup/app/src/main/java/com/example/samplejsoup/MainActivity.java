package com.example.samplejsoup;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

        // AsyncTask 작동시킨다.(파싱)
        new Description().execute();
    }

    private class Description extends AsyncTask<Void, Void, Void> {

        // 진행바 표시
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            // 진행 다이어로그 시작
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect("https://movie.naver.com/movie/running/current.nhn").get();

                // <ul class="lst_detail_t1"> 태그의 <li> 태그를 선택한다.
                Elements mElementDataSize = doc.select("ul[class=lst_detail_t1]").select("li");

                // mElementDataSize 에 저장된 <li> 요소들을 하나씩 가져온다.
                for(Element elem : mElementDataSize){
                    // <li> 에서 다시 원하는 데이터를 추출해 낸다.

                    // <li> 안에 <dt class="tit"> 안에 <a> 태그의 text 가져옴.
                    String my_title = elem.select("li dt[class=tit] a").text();

                    // <li> 안에 <div class="thumb"> 안에 <a> 태그의 href 링크값을 가져옴
                    String my_link = elem.select("li div[class=thumb] a").attr("href");

                    // <li> 안에 <div class="thumb"> 안에 <a> 태그안에 <img> 태그의 src의 png 값을 가져옴.
                    String my_imgUrl = elem.select("li div[class=thumb] a img").attr("src");

                    // <li> 안에 <dl class="info_txt1"> 태그 들어가서 <dt> 태그 정보를 가져온다.
                    Element rElem = elem.select("dl[class=info_txt1] dt").next().first();

                    // <dt> 태그 안에 <dd> 태그의 내용(종류, 시간, 개봉일)을 가져온다.
                    String my_release = rElem.select("dd").text();

                    // <dt class="tit_t2"> 태그안으로 들어간다.
                    Element dElem = elem.select("dt[class=tit_t2]").next().first();

                    // <a> 태그의 정보를 가져온다.
                    String my_director = "감독: " + dElem.select("a").text();

                    //ArrayList에 추가한다.
                    list.add(new ItemObject(my_title, my_imgUrl, my_link, my_release, my_director));
                }

                //추출한 전체 <li> 출력해 보자.
                Log.d("debug :", "List " + mElementDataSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //ArrayList를 인자로 해서 어답터와 연결한다.
            MyAdapter myAdapter = new MyAdapter(list);

            // 리니어 레이아웃을 가져온다.
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

            // recyclerView 에 레이아웃 매니저를 대입
            recyclerView.setLayoutManager(layoutManager);

            // 어뎁터 대입
            recyclerView.setAdapter(myAdapter);
            
            progressDialog.dismiss();
        }
    }

}