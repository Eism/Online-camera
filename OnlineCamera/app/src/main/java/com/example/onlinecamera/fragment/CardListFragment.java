package com.example.onlinecamera.fragment;

import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.onlinecamera.R;
import com.example.onlinecamera.activity.CardDetailsActivity;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Future;

import static com.example.onlinecamera.R.id.imageView;

/**
 * Created by ELNUR on 22.10.2016.
 */

public class CardListFragment extends ListFragment {

    private final String[] title = new String[6];

    private Boolean error=false;
    private Integer async = 0;

    private final JSONArray[] webcams1 = new JSONArray[4];
    public ListView mList;
    boolean mListShown;
    View mProgressContainer;
    View mListContainer;


    String[] id;
    String[] statusCard;
    String[] titleCard;
    String[] imageCard;
    String[] otherInfoCard1;
    String[] otherInfoCard2;

    ArrayList<HashMap<String,String>> data= new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.card_list, container, false);

        new CallMashapeAsync().execute();

        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Long ID=id;

        Intent intent = new Intent(getActivity(), CardDetailsActivity.class);
        startActivity(intent);
    }

    public void setListShown(boolean shown, boolean animate){
        if (mListShown == shown) {
            return;
        }
        mListShown = shown;
        if (shown) {
            if (animate) {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(
                        getActivity(), android.R.anim.fade_out));
                mListContainer.startAnimation(AnimationUtils.loadAnimation(
                        getActivity(), android.R.anim.fade_in));
            }
            mProgressContainer.setVisibility(View.GONE);
            mListContainer.setVisibility(View.VISIBLE);
        } else {
            if (animate) {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(
                        getActivity(), android.R.anim.fade_in));
                mListContainer.startAnimation(AnimationUtils.loadAnimation(
                        getActivity(), android.R.anim.fade_out));
            }
            mProgressContainer.setVisibility(View.VISIBLE);
            mListContainer.setVisibility(View.INVISIBLE);
        }
    }
    public void setListShown(boolean shown){
        setListShown(shown, true);
    }
    public void setListShownNoAnimation(boolean shown) {
        setListShown(shown, false);
    }

    public class CallMashapeAsync extends AsyncTask<String, Integer, Boolean> {

        protected Boolean doInBackground(String... msg) {

            Future<HttpResponse<JsonNode>> request = Unirest.get("https://webcamstravel.p.mashape.com/webcams/list/property=live,hd/region=US.MS/limit=4,11?show=webcams:id,title,image,location")
                    .header("X-Mashape-Key", "Fo4TeWno4vmshqokVApAI2DHB5aYp1u2kKvjsnyu1ZPsYirCD5")
                    .asJsonAsync(new Callback<JsonNode>() {
                        @Override
                        public void completed(HttpResponse<JsonNode> httpResponse) {
                            async++;
                            try {
                                webcams1[0] =httpResponse.getBody().getObject().getJSONObject("result").optJSONArray("webcams");
                            } catch (JSONException e) {
                                error=true;
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(UnirestException e) {

                        }

                        @Override
                        public void cancelled() {

                        }
                    });
            Future<HttpResponse<JsonNode>> request1 = Unirest.get("https://webcamstravel.p.mashape.com/webcams/list/property=live,hd/region=US.TX/limit=7,0?show=webcams:id,title,image,location")
                    .header("X-Mashape-Key", "Fo4TeWno4vmshqokVApAI2DHB5aYp1u2kKvjsnyu1ZPsYirCD5")
                    .asJsonAsync(new Callback<JsonNode>() {
                        @Override
                        public void completed(HttpResponse<JsonNode> httpResponse) {
                            async++;
                            try {
                                webcams1[1] =httpResponse.getBody().getObject().getJSONObject("result").optJSONArray("webcams");
                            } catch (JSONException e) {
                                error=true;
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(UnirestException e) {

                        }

                        @Override
                        public void cancelled() {

                        }
                    });
            Future<HttpResponse<JsonNode>> request2 = Unirest.get("https://webcamstravel.p.mashape.com/webcams/list/property=live,hd/region=US.WY/limit=2,0?show=webcams:id,title,image,location")
                    .header("X-Mashape-Key", "Fo4TeWno4vmshqokVApAI2DHB5aYp1u2kKvjsnyu1ZPsYirCD5")
                    .asJsonAsync(new Callback<JsonNode>() {
                        @Override
                        public void completed(HttpResponse<JsonNode> httpResponse) {
                            async++;
                            try {
                                webcams1[2] =httpResponse.getBody().getObject().getJSONObject("result").optJSONArray("webcams");
                            } catch (JSONException e) {
                                error=true;
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(UnirestException e) {

                        }

                        @Override
                        public void cancelled() {

                        }
                    });
            Future<HttpResponse<JsonNode>> request3 = Unirest.get("https://webcamstravel.p.mashape.com/webcams/list/property=live,hd/region=US.CO/limit=5,0?show=webcams:id,title,image,location")
                    .header("X-Mashape-Key", "Fo4TeWno4vmshqokVApAI2DHB5aYp1u2kKvjsnyu1ZPsYirCD5")
                    .asJsonAsync(new Callback<JsonNode>() {
                        @Override
                        public void completed(HttpResponse<JsonNode> httpResponse) {
                            async++;
                            try {
                                webcams1[3] =httpResponse.getBody().getObject().getJSONObject("result").optJSONArray("webcams");
                            } catch (JSONException e) {
                                error=true;
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(UnirestException e) {

                        }

                        @Override
                        public void cancelled() {

                        }
                    });


            while (true) {
                if (async == 4) {
                    if (error) {

                    } else {

                        Integer lenght = webcams1[0].length() + webcams1[1].length() + webcams1[2].length() + webcams1[3].length();
                        id = new String[lenght];
                        statusCard = new String[lenght];
                        titleCard = new String[lenght];
                        imageCard = new String[lenght];
                        otherInfoCard1 = new String[lenght];
                        otherInfoCard2 = new String[lenght];

                        Integer l = 0;
                        for (int i = 0; i < webcams1[0].length(); i++) {
                            try {
                                JSONObject jsonObject = webcams1[0].getJSONObject(i);

                                id[l] = jsonObject.get("id").toString();
                                statusCard[l] = jsonObject.get("status").toString();
                                titleCard[l] = jsonObject.get("title").toString();
                                imageCard[l] = jsonObject.getJSONObject("image").getJSONObject("current").get("preview").toString();
                                otherInfoCard1[l] = jsonObject.getJSONObject("location").get("continent").toString();
                                otherInfoCard2[l++] = jsonObject.getJSONObject("location").get("country").toString() + jsonObject.getJSONObject("location").get("city").toString();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        for (int i = 0; i < webcams1[1].length(); i++) {
                            try {
                                JSONObject jsonObject = webcams1[1].getJSONObject(i);

                                id[l] = jsonObject.get("id").toString();
                                statusCard[l] = jsonObject.get("status").toString();
                                titleCard[l] = jsonObject.get("title").toString();
                                imageCard[l] = jsonObject.getJSONObject("image").getJSONObject("current").get("preview").toString();
                                otherInfoCard1[l] = jsonObject.getJSONObject("location").get("continent").toString();
                                otherInfoCard2[l++] = jsonObject.getJSONObject("location").get("country").toString() + jsonObject.getJSONObject("location").get("city").toString();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        for (int i = 0; i < webcams1[2].length(); i++) {
                            try {
                                JSONObject jsonObject = webcams1[2].getJSONObject(i);

                                id[l] = jsonObject.get("id").toString();
                                statusCard[l] = jsonObject.get("status").toString();
                                titleCard[l] = jsonObject.get("title").toString();
                                imageCard[l] = jsonObject.getJSONObject("image").getJSONObject("current").get("preview").toString();
                                otherInfoCard1[l] = jsonObject.getJSONObject("location").get("continent").toString();
                                otherInfoCard2[l++] = jsonObject.getJSONObject("location").get("country").toString() + jsonObject.getJSONObject("location").get("city").toString();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        for (int i = 0; i < webcams1[3].length(); i++) {
                            try {
                                JSONObject jsonObject = webcams1[3].getJSONObject(i);

                                id[l] = jsonObject.get("id").toString();
                                statusCard[l] = jsonObject.get("status").toString();
                                titleCard[l] = jsonObject.get("title").toString();
                                imageCard[l] = jsonObject.getJSONObject("image").getJSONObject("current").get("preview").toString();
                                otherInfoCard1[l] = jsonObject.getJSONObject("location").get("continent").toString();
                                otherInfoCard2[l++] = jsonObject.getJSONObject("location").get("country").toString() + jsonObject.getJSONObject("location").get("city").toString();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }
                }
            }
            return true;
        }

        protected void onProgressUpdate(Integer...integers) {
        }

        protected void onPostExecute(Boolean response) {

            GetBitmapFromURL bitm=new GetBitmapFromURL();
            bitm.execute(imageCard);
        }
    }

    public class GetBitmapFromURL extends AsyncTask<String[],Integer,Bitmap[]>{
        @Override
        protected Bitmap[] doInBackground(String[]... src) {

            Bitmap[] bitmaps=new Bitmap[src[0].length];

            for (int i=0;i<src[0].length;i++) {
                try {
                    URL url = new URL(src[0][i]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    bitmaps[i]=bitmap;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return bitmaps;
        }

        protected void onProgressUpdate(Integer...integers) {
        }

        protected void onPostExecute(Bitmap[] response) {

            HashMap<String,String> map = new HashMap<String, String>();

            for (int i=0;i<statusCard.length;i++){
                map=new HashMap<String, String>();
                map.put("status",statusCard[i]);
                map.put("title",titleCard[i]);
                Drawable d = new BitmapDrawable(getResources(),response[i]);
                map.put("image", String.valueOf(d));
                map.put("info1",otherInfoCard1[i]);
                map.put("info2",otherInfoCard2[i]);

                data.add(map);
            }

            String[] from={"status","title","image","info1","info2"};

            int[] to ={R.id.statusCard,R.id.titleCard,R.id.imagePreviewCamera,R.id.adress,R.id.subAdress};

            adapter = new SimpleAdapter(getActivity(),data,R.layout.item_card_list,from,to);
            setListAdapter(adapter);

        }

    }

}
