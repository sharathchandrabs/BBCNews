package com.example.shara.bbcnews;

import android.os.AsyncTask;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by shara on 3/18/2017.
 */

public class FetchNewsAsyncTask extends AsyncTask<String, Void, ArrayList<News>> {

    IData mainActivity;

    public FetchNewsAsyncTask(IData mainActivity) {
        this.mainActivity = mainActivity;
    }

    public static interface IData {
        public void setupData(ArrayList<News> news);
    }

    @Override
    protected ArrayList<News> doInBackground(String... params) {

        BufferedReader reader = null;
        try {
            URL url = new URL(params[0]);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream in = httpURLConnection.getInputStream();
            return NewsUtil.BBCNewsParser.parseNewsPullParser(in);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<News> newsList) {
        mainActivity.setupData(newsList);
    }
}
