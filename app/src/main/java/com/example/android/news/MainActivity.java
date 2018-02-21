package com.example.android.news;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.android.news.dataProccess.Connector;
import com.example.android.news.dataProccess.DataEncap;
import com.example.android.news.dataProccess.JsonParser;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    final static String api = "https://newsapi.org/v2/top-headlines?sources=bbc-sport&apiKey=1c9cdb0e1e3c401d8d662d75be5d3fe9";
    JsonParser parser = new JsonParser();
    private RecyclerView recyclerView;
    private AdapterNews adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Check Internet Connection In your App
        if(Check_Internet_Connection())
        {
            Toast.makeText(MainActivity.this, "Loading ........", Toast.LENGTH_LONG).show();
            Connector connector = new Connector();

            try {

                ArrayList<DataEncap> arrayList = parser.JsonProcess(connector.execute(api).get());

                recyclerMain();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        else
        {
            Toast.makeText(MainActivity.this, "Check Internet Connection .", Toast.LENGTH_LONG).show();
        }


    }

    private void recyclerMain() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdapterNews(parser.getlist(), getApplicationContext(), this);

        recyclerView.setAdapter(adapter);

        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();

    }

    private boolean Check_Internet_Connection()
    {
     ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
     NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork)
        {
           return true;
        }
        else
        {
           return false;
        }
    }
}