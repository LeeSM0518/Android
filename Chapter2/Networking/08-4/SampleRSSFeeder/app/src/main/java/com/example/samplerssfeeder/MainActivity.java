package com.example.samplerssfeeder;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static String rssUrl =
            "http://api.sbs.co.kr/xml/news/rss.jsp?pmDiv=entertainment";

    Button showBtn;
    ListView listView;
    ProgressDialog progressDialog;
    Handler handler = new Handler();

    RSSListView list;
    RSSListAdapter adapter;
    ArrayList<RSSNewsItem> newsItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        showBtn = findViewById(R.id.show_btn);
        listView = findViewById(R.id.lisView);

        list = new RSSListView(this);
        adapter = new RSSListAdapter(this);
        list.setAdapter(adapter);

        list.setOnDataSelectionListener(new OnDataSelectionListener() {
            public void onDataSelected(AdapterView parent, View v, int position, long id) {
                RSSNewsItem curItem = (RSSNewsItem) adapter.getItem(position);
                String curTitle = curItem.getTitle();

                Toast.makeText(getApplicationContext(), "Selected : " + curTitle,
                        Toast.LENGTH_LONG).show();
            }
        });

        newsItemList = new ArrayList<RSSNewsItem>();

        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.addView(list, params);

        final EditText edit01 = findViewById(R.id.edit01);
        edit01.setText(rssUrl);

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = edit01.getText().toString();
                showRSS(inputStr);
            }
        });
    }

    private void showRSS(String urlStr) {
        try {
            progressDialog = ProgressDialog.show(this, "RSS Refresh",
                    "RSS 정보 업데이트 중...", true, true);

            RefreshThread thread = new RefreshThread(urlStr);
            thread.start();
        } catch (Exception e) {
            Log.e(TAG, "Error", e);
        }
    }

    class RefreshThread extends Thread {

        String urlStr;

        public RefreshThread(String urlStr) {
            this.urlStr = urlStr;
        }

        @Override
        public void run() {
            try {
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = builderFactory.newDocumentBuilder();

                URL urlForHttp = new URL(urlStr);

                InputStream instream = getInputStreamUsingHTTP(urlForHttp);
                Document document = builder.parse(instream);

                int countItem = processDocument(document);
                Log.d(TAG, countItem + " news item processed.");

                handler.post(updateRSSRunnable);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public InputStream getInputStreamUsingHTTP(URL url) throws Exception{
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        int resCode = conn.getResponseCode();
        Log.d(TAG, "Response Code : " + resCode);

        InputStream instream = conn.getInputStream();

        return instream;
    }

    private int processDocument(Document doc) {
        newsItemList.clear();

        Element docEle = doc.getDocumentElement();

        NodeList nodeList = docEle.getElementsByTagName("item");
        int count = 0;

        if ((nodeList != null && nodeList.getLength() > 0)) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                RSSNewsItem newsItem = dissectNode(nodeList, i);
                if (newsItem != null) {
                    newsItemList.add(newsItem);
                    count++;
                }
            }
        }
        return count;
    }

    private RSSNewsItem dissectNode(NodeList nodeList, int index) {
        RSSNewsItem newsItem = null;

        try {
            Element entry = (Element) nodeList.item(index);

            Element title = (Element) entry.getElementsByTagName("title").item(0);
            Element link = (Element) entry.getElementsByTagName("link").item(0);
            Element description = (Element) entry.getElementsByTagName("description").item(0);
            NodeList pubDataNode = entry.getElementsByTagName("pubDate");
            if (pubDataNode == null) {
                pubDataNode = entry.getElementsByTagName("dc:date");
            }

            Element pubDate = (Element) pubDataNode.item(0);
            Element author = (Element) entry.getElementsByTagName("author").item(0);
            Element category = (Element) entry.getElementsByTagName("category").item(0);

            String titleValue = null;
            if (title != null) {
                Node firstChild = title.getFirstChild();
                if (firstChild != null) {
                    titleValue = firstChild.getNodeValue();
                }
            }

            String linkValue = null;
            if (link != null) {
                Node firstChild = link.getFirstChild();
                if (firstChild != null) {
                    linkValue = firstChild.getNodeValue();
                }
            }

            String descriptionValue = null;
            if (description != null) {
                Node firsetChild = description.getFirstChild();
                if (firsetChild != null) {
                    descriptionValue = firsetChild.getNodeValue();
                }
            }

            String pubDateValue = null;
            if (pubDate != null) {
                Node firstChild = pubDate.getFirstChild();
                if (firstChild != null) {
                    pubDateValue = firstChild.getNodeValue();
                }
            }

            String authorValue = null;
            if (author != null) {
                Node firstChild = author.getFirstChild();
                if (firstChild != null) {
                    authorValue = firstChild.getNodeValue();
                }
            }

            String categoryValue = null;
            if (category != null) {
                Node firstChild = category.getFirstChild();
                if (firstChild != null) {
                    categoryValue = firstChild.getNodeValue();
                }
            }

            newsItem = new RSSNewsItem(titleValue, linkValue, descriptionValue,
                    pubDateValue, authorValue, categoryValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newsItem;
    }

    Runnable updateRSSRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                Resources res = getResources();
                Drawable rssIcon = res.getDrawable(R.drawable.tap);
                for (int i = 0; i < newsItemList.size(); i++) {
                    RSSNewsItem newsItem = (RSSNewsItem) newsItemList.get(i);
                    newsItem.setIcon(rssIcon);
                    adapter.addItem(newsItem);
                }

                adapter.notifyDataSetChanged();

                progressDialog.dismiss();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };
}
