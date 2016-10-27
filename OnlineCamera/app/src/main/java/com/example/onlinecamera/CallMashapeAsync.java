package com.example.onlinecamera;

import android.os.AsyncTask;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Created by ELNUR on 26.10.2016.
 */
public class CallMashapeAsync extends AsyncTask<String, Integer, HttpResponse<JsonNode>> {

    protected HttpResponse<JsonNode> doInBackground(String... msg) {

        HttpResponse<JsonNode> request = null;
        try {
            request = Unirest.get("https://webcamstravel.p.mashape.com/webcams/list/property=live,hd/continent=SA/limit=200,0?show=webcams:id,title,image,location")
                    .header("X-Mashape-Key", "Fo4TeWno4vmshqokVApAI2DHB5aYp1u2kKvjsnyu1ZPsYirCD5")
                    .asJson();
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return request;
    }

    protected void onProgressUpdate(Integer...integers) {
    }

    protected void onPostExecute(HttpResponse<JsonNode> response) {
        //String answer = response.getBody().toString();
        //TextView txtView = (TextView) root.findViewById(R.id.titleCamera);
        //txtView.setText(answer);
    }
}
