package com.example.shara.bbcnews;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by shara on 3/18/2017.
 */

public class RequestParams {

    String baseUrl, method;
    HashMap<String,String> params = new HashMap<String,String>();

    public RequestParams(String baseUrl, String method, HashMap<String, String> params) {
        this.baseUrl = baseUrl;
        this.method = method;
        this.params = params;
    }

    public HttpURLConnection setupConnection() throws IOException {
        HttpURLConnection con;
        if(method.equals("GET")){
            URL url = new URL("www.xyz.com");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
        }
        else{
            con = null;
        }

        return con;
    }

}
