package com.newsproj.newsproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
RecyclerView rview;
List<DataValue> dList;
RequestQueue requestQueue;
ProgressBar pbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbar= findViewById(R.id.pbar);
        nv = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerid);
        rview= findViewById(R.id.rview);

        rview.setHasFixedSize(true);
        rview.setLayoutManager(new LinearLayoutManager(this));

        dList= new ArrayList<>();
        requestQueue= Volley.newRequestQueue(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        nv.setNavigationItemSelectedListener(this);

        getSupportActionBar().setTitle("News App");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pbar.showContextMenu();

        loadNews();
    }

    private void loadNews() {
        String newsURL="http://school3.yarshatech.com/api/schooldata/NewsAnnouncements";
//        http://school3.yarshatech.com/api/schooldata/NewsAnnouncements?$top=40&$skip=0

        StringRequest strReq= new StringRequest(Request.Method.GET, newsURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jObj= new JSONObject(response);
                    JSONArray jArray= jObj.getJSONArray("value");

                    for(int i=0;i<=jArray.length();i++){
                        DataValue data= new DataValue();
                        JSONObject jData= jArray.getJSONObject(i);
                        data.setId(jData.getString("Id"));
                        data.setDecription(jData.getString("Description"));
                        data.setTitle(jData.getString("Title"));
                        String image= jData.getString("Image");
                        if(image!=null) {
                            data.setImage(jData.getString("Image"));
                        }
                        data.setUserName(jData.getString("UserName"));
                        data.setShortdesc(jData.getString("ShortDescription"));
                        data.setPubAt(jData.getString("PublishDate"));

                        dList.add(data);

                        pbar.setVisibility(View.GONE);

                        Log.d("check",jData.getString("UserName"));
                        rview.setAdapter(new NewsAdaptor(MainActivity.this,dList));
                    }
                } catch (JSONException e) {
                    pbar.setVisibility(View.GONE);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pbar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
         requestQueue.add(strReq);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_about) {
            Toast.makeText(this, "its me about : )", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_home) {
            Toast.makeText(this, "its me Home : P", Toast.LENGTH_SHORT).show();
        }
        else {
            return false;
        }
        DrawerLayout drawer = findViewById(R.id.drawerid);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}
