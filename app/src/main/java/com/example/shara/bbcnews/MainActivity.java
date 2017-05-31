package com.example.shara.bbcnews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FetchNewsAsyncTask.IData, BBCRecyclerViewAdapter.INewsRecyclerView {
    RecyclerView newsEntriesRecyclerView;
    String url;
    BBCRecyclerViewAdapter customAdapter;
    int recyclerLayout;
    DatabaseDataManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            dbManager = new DatabaseDataManager(this);
            Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);
            newsEntriesRecyclerView = (RecyclerView) findViewById(R.id.newsRecyclerView);
            recyclerLayout = R.layout.grid_layout_recycler_view;
            url = getResources().getString(R.string.url);
            new FetchNewsAsyncTask(this).execute(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showNews:
            case R.id.showBlocked:
            case R.id.clearBlocked:
            case R.id.sortAtoZ:
            case R.id.sortZtoA:
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setupData(ArrayList<News> news) {
        Log.d("setupdata","asynctask executed");
        newsEntriesRecyclerView = (RecyclerView) findViewById(R.id.newsRecyclerView);
        customAdapter = new BBCRecyclerViewAdapter(this, news, recyclerLayout);
        newsEntriesRecyclerView.setAdapter(customAdapter);
        newsEntriesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void displayNews(News news) {
        Intent toDisplayNews = new Intent(MainActivity.this, displayNews.class);
        toDisplayNews.putExtra("TO_DISPLAYWEB", news.getNewsLink());
        startActivity(toDisplayNews);
    }

    @Override
    public void addToDatabase(News news) {
        dbManager.saveNote(news);
    }
}
