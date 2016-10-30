package com.example.onlinecamera.fragment;

import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.example.onlinecamera.R;
import com.example.onlinecamera.activity.BaseActivity;
import com.example.onlinecamera.activity.CardDetailsActivity;
import com.example.onlinecamera.activity.ErrorActivity;
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
import java.io.Serializable;
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
    public Boolean isLoading = true;

    private Boolean error = false;
    private Integer async = 0;

    private final JSONArray[] webcams1 = new JSONArray[4];

    private String[] statusCard;
    private String[] titleCard;
    private String[] imageCard;
    private String[] otherInfoCard1;
    private String[] otherInfoCard2;
    private Integer[] idCountry;

    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;

    private ProgressBar loadProgressBar;

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.card_list, container, false);
        isLoading = true;
        loadProgressBar = (ProgressBar) v.findViewById(R.id.loadProgressBar);
        loadProgressBar.setVisibility(View.VISIBLE);

        new CallMashapeAsync().execute();

        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Integer ID = ((Long) id).intValue();

        Intent intent = new Intent(getActivity(), CardDetailsActivity.class);
        intent.putExtra("webcams", webcams1[ID].toString());
        intent.putExtra("id",ID);
        startActivity(intent);
    }

    public class CallMashapeAsync extends AsyncTask<String, Integer, Boolean> {

        protected Boolean doInBackground(String... msg) {

            Future<HttpResponse<JsonNode>> request = Unirest.get("https://webcamstravel.p.mashape.com/webcams/list/property=live,hd/region=US.MS/limit=4,11?show=webcams:idCamera,title,image,location")
                    .header("X-Mashape-Key", "Fo4TeWno4vmshqokVApAI2DHB5aYp1u2kKvjsnyu1ZPsYirCD5")
                    .asJsonAsync(new Callback<JsonNode>() {
                        @Override
                        public void completed(HttpResponse<JsonNode> httpResponse) {

                            try {
                                webcams1[0] = httpResponse.getBody().getObject().getJSONObject("result").optJSONArray("webcams");
                            } catch (JSONException e) {
                                error = true;
                                async++;
                                e.printStackTrace();
                            }
                            async++;
                        }

                        @Override
                        public void failed(UnirestException e) {
                            error = true;
                            async++;
                        }

                        @Override
                        public void cancelled() {
                            error = true;
                            async++;
                        }
                    });
            Future<HttpResponse<JsonNode>> request1 = Unirest.get("https://webcamstravel.p.mashape.com/webcams/list/property=live,hd/region=US.TX/limit=7,0?show=webcams:idCamera,title,image,location")
                    .header("X-Mashape-Key", "Fo4TeWno4vmshqokVApAI2DHB5aYp1u2kKvjsnyu1ZPsYirCD5")
                    .asJsonAsync(new Callback<JsonNode>() {
                        @Override
                        public void completed(HttpResponse<JsonNode> httpResponse) {
                            try {
                                webcams1[1] = httpResponse.getBody().getObject().getJSONObject("result").optJSONArray("webcams");
                            } catch (JSONException e) {
                                error = true;
                                async++;
                                e.printStackTrace();
                            }
                            async++;
                        }

                        @Override
                        public void failed(UnirestException e) {
                            error = true;
                            async++;
                        }

                        @Override
                        public void cancelled() {
                            error = true;
                            async++;
                        }
                    });
            Future<HttpResponse<JsonNode>> request2 = Unirest.get("https://webcamstravel.p.mashape.com/webcams/list/property=live,hd/region=US.WY/limit=2,0?show=webcams:idCamera,title,image,location")
                    .header("X-Mashape-Key", "Fo4TeWno4vmshqokVApAI2DHB5aYp1u2kKvjsnyu1ZPsYirCD5")
                    .asJsonAsync(new Callback<JsonNode>() {
                        @Override
                        public void completed(HttpResponse<JsonNode> httpResponse) {
                            try {
                                webcams1[2] = httpResponse.getBody().getObject().getJSONObject("result").optJSONArray("webcams");
                            } catch (JSONException e) {
                                error = true;
                                async++;
                                e.printStackTrace();
                            }
                            async++;
                        }

                        @Override
                        public void failed(UnirestException e) {
                            error = true;
                            async++;
                        }

                        @Override
                        public void cancelled() {
                            error = true;
                            async++;
                        }
                    });
            Future<HttpResponse<JsonNode>> request3 = Unirest.get("https://webcamstravel.p.mashape.com/webcams/list/property=live,hd/region=US.CO/limit=5,0?show=webcams:idCamera,title,image,location")
                    .header("X-Mashape-Key", "Fo4TeWno4vmshqokVApAI2DHB5aYp1u2kKvjsnyu1ZPsYirCD5")
                    .asJsonAsync(new Callback<JsonNode>() {
                        @Override
                        public void completed(HttpResponse<JsonNode> httpResponse) {
                            try {
                                webcams1[3] = httpResponse.getBody().getObject().getJSONObject("result").optJSONArray("webcams");
                            } catch (JSONException e) {
                                error = true;
                                async++;
                                e.printStackTrace();
                            }
                            async++;
                        }

                        @Override
                        public void failed(UnirestException e) {
                            error = true;
                            async++;
                        }

                        @Override
                        public void cancelled() {
                            error = true;
                            async++;
                        }
                    });


            while (true) {
                if (async == 4) {
                    if (error) {
                        break;
                    } else {

                        idCountry = new Integer[4];
                        statusCard = new String[4];
                        titleCard = new String[4];
                        imageCard = new String[4];
                        otherInfoCard1 = new String[4];
                        otherInfoCard2 = new String[4];

                        Integer l = 0;

                        for(int i=0;i<4;i++){
                            idCountry[i]=i;
                            try {
                                JSONObject jsonObject = webcams1[i].getJSONObject(0);
                                statusCard[i]=jsonObject.get("status").toString();
                                titleCard[l] = jsonObject.getJSONObject("location").get("region").toString();
                                imageCard[l] = jsonObject.getJSONObject("image").getJSONObject("current").get("preview").toString();
                                otherInfoCard1[l] = jsonObject.getJSONObject("location").get("continent").toString();
                                otherInfoCard2[l++] = jsonObject.getJSONObject("location").get("country").toString();
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

        protected void onProgressUpdate(Integer... integers) {
        }

        protected void onPostExecute(Boolean response) {

            if (error){
                isLoading = false;
                Intent intent = new Intent(getActivity(), ErrorActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else {
                GetBitmapFromURL bitm = new GetBitmapFromURL();
                bitm.execute(imageCard);
            }
        }
    }

    public class GetBitmapFromURL extends AsyncTask<String[], Integer, Bitmap[]> {
        @Override
        protected Bitmap[] doInBackground(String[]... src) {

            Bitmap[] bitmaps = new Bitmap[src[0].length];

            for (int i = 0; i < src[0].length; i++) {
                try {
                    URL url = new URL(src[0][i]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    bitmaps[i] = bitmap;
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

        protected void onProgressUpdate(Integer... integers) {
        }

        protected void onPostExecute(Bitmap[] response) {

            HashMap<String, String> map = new HashMap<String, String>();

            int[][] image = {{R.drawable.preview1,R.drawable.preview2,R.drawable.preview3,R.drawable.preview4},{R.drawable.preview5,R.drawable.preview6},{R.drawable.preview7,R.drawable.preview8},{R.drawable.preview9,R.drawable.preview10,R.drawable.preview11,R.drawable.preview12,R.drawable.preview13}};

            for (int i = 0; i < statusCard.length; i++) {
                map = new HashMap<String, String>();
                map.put("status", statusCard[i]);
                map.put("title", titleCard[i]);
                map.put("image", String.valueOf(image[i][0]));
                map.put("info1", otherInfoCard1[i]);
                map.put("info2", otherInfoCard2[i]);
                data.add(map);
            }
            String[] from = {"status", "title", "image", "info1", "info2"};

            int[] to = {R.id.statusCard, R.id.titleCard, R.id.imagePreviewCamera, R.id.adress, R.id.subAdress};

            adapter = new SimpleAdapter(getActivity(), data, R.layout.item_card_list, from, to);

            isLoading = false;

            loadProgressBar.setVisibility(View.GONE);
            setListAdapter(adapter);
        }
    }
}