package com.newsproj.newsproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 8/17/2019.
 */

public class NewsAdaptor extends RecyclerView.Adapter<NewsAdaptor.newsHolder> {
    Context ctx;
    List<DataValue> nlist= new ArrayList<>();

    public NewsAdaptor(Context ctx, List<DataValue> dList) {
        this.ctx= ctx;
        nlist= dList;
    }

    @NonNull
    @Override
    public newsHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(ctx).inflate(R.layout.newslist,parent,false);
        return new newsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull newsHolder newsHolder, int position) {
        DataValue dataValue= nlist.get(position);
        newsHolder.txtTitle.setText(dataValue.getTitle());
        newsHolder.pub.setText("published At : " +dataValue.getPubAt());
        newsHolder.uname.setText("Posted By : " + dataValue.getUserName());

        newsHolder.shdesc.setText(dataValue.getShortdesc());
        newsHolder.desc.setText(dataValue.getDecription());
//        Picasso.with(ctx).load(dataValue.getImage()).into(newsHolder.imgIcon);
    }

    @Override
    public int getItemCount() {
        return nlist.size();
    }

    public class newsHolder extends RecyclerView.ViewHolder {
        private ImageView imgIcon;
        private TextView txtTitle,pub,desc,uname,shdesc;
        public newsHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon= itemView.findViewById(R.id.imgIcon);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            pub= itemView.findViewById(R.id.pubAt);
            uname= itemView.findViewById(R.id.uname);
            shdesc=itemView.findViewById(R.id.shdesc);
            desc= new TextView(ctx);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // passing data from one activity to another throug intent
                    // key value must be same in next activity to get data

                    Intent intent= new Intent(ctx,NewsDetails.class);
                    intent.putExtra("desc",desc.getText().toString()); // here key  is = desc
                    intent.putExtra("pubdate",pub.getText().toString());  //key is = pubdate  ,, nd in next activity key must be same
                    intent.putExtra("title",txtTitle.getText().toString());
                    intent.putExtra("shdesc",shdesc.getText().toString());
                    intent.putExtra("postedby",uname.getText().toString());
                    ctx.startActivity(intent);
                }
            });

        }


    }
}
