package com.newsproj.newsproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class NewsDetails extends AppCompatActivity {
String title,shortDesc,postedBy,pubDate,desc;
TextView tit,sdesc,pby,pdate,dsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        dsc= findViewById(R.id.desc);
        tit= findViewById(R.id.title);
        sdesc= findViewById(R.id.sdesc);
        pby= findViewById(R.id.pby);
        pdate= findViewById(R.id.pubat);

       getDataFromPrevPage();

       getSupportActionBar().setTitle(title);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void getDataFromPrevPage() {
        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            desc= bundle.getString("desc");
            title= bundle.getString("title");
            shortDesc= bundle.getString("shdesc");
            postedBy= bundle.getString("postedby");

            pubDate= bundle.getString("pubdate");

            dsc.setText(desc);
            tit.setText(title);
            sdesc.setText(shortDesc);
            pby.setText("Posted By : " + postedBy);
            pdate.setText(pubDate);
        }
    }
}
