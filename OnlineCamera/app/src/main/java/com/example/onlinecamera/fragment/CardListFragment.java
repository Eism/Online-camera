package com.example.onlinecamera.fragment;

import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import com.example.onlinecamera.R;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

    private ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter adapter;

    private ProgressBar loadProgressBar;

    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.card_list, container, false);
        isLoading = true;
        loadProgressBar = (ProgressBar) v.findViewById(R.id.loadProgressBar);
        loadProgressBar.setVisibility(View.VISIBLE);

        new CallMashapeAsync().execute();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {


                }
            }
        }).start();

        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Integer ID = ((Long) id).intValue();

        Intent intent = new Intent(getActivity(), CardDetailsActivity.class);
        intent.putExtra("webcams", webcams1[idCountry[ID]].toString());
        intent.putExtra("id", ID);
        startActivity(intent);
    }

    public void changeList(Integer filter) {

        Integer lenght = 0;
        Map<String, Integer> data;
        Map<String, Integer> treeMap;
        switch (filter) {
            case 0:
                idCountry = new Integer[]{0, 1, 2, 3};
                setList(4);
                break;
            case 1:
                for (int i = 0; i < webcams1.length; i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = webcams1[i].getJSONObject(0);
                        String status = jsonObject.get("status").toString();
                        if (status.equals("active")) {
                            idCountry[lenght++] = i;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setList(lenght);

                break;
            case 2:
                data = new HashMap<String, Integer>();
                //в дальнейшем ускорить за счет друугой сортировки
                for (int i = 0; i < webcams1.length; i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = webcams1[i].getJSONObject(0);
                        data.put(jsonObject.getJSONObject("location").get("region").toString().toLowerCase(), i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                treeMap = new TreeMap<String, Integer>(data);
                lenght = 0;
                for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
                    idCountry[lenght++] = entry.getValue();
                }

                setList(lenght);


                break;
            case 3:
                data = new HashMap<String, Integer>();
                //в дальнейшем ускорить за счет друугой сортировки
                for (int i = 0; i < webcams1.length; i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = webcams1[i].getJSONObject(0);
                        data.put(jsonObject.getJSONObject("location").get("region").toString().toLowerCase(), i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                treeMap = new TreeMap<String, Integer>(data);
                lenght = webcams1.length - 1;
                for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
                    idCountry[lenght--] = entry.getValue();
                }
                setList(webcams1.length);
                break;
        }
    }

    public class CallMashapeAsync extends AsyncTask<String, Integer, Boolean> {

        protected Boolean doInBackground(String... msg) {

            Unirest.get("https://webcamstravel.p.mashape.com/webcams/list/property=live,hd/region=US.MS/limit=4,11?show=webcams:idCamera,title,image,location")
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
            Unirest.get("https://webcamstravel.p.mashape.com/webcams/list/property=live,hd/region=US.TX/limit=7,0?show=webcams:idCamera,title,image,location")
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
            Unirest.get("https://webcamstravel.p.mashape.com/webcams/list/property=live,hd/region=US.WY/limit=2,0?show=webcams:idCamera,title,image,location")
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
            Unirest.get("https://webcamstravel.p.mashape.com/webcams/list/property=live,hd/region=US.CO/limit=5,0?show=webcams:idCamera,title,image,location")
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

                        for (int i = 0; i < 4; i++) {
                            idCountry[i] = i;
                            try {
                                JSONObject jsonObject = webcams1[i].getJSONObject(0);
                                statusCard[i] = jsonObject.get("status").toString();
                                titleCard[l] = jsonObject.getJSONObject("location").get("region").toString();
                                imageCard[l] = jsonObject.getJSONObject("image").getJSONObject("current").get("preview").toString();
                                otherInfoCard1[l] = jsonObject.getJSONObject("location").get("continent").toString();
                                otherInfoCard2[l++] = jsonObject.getJSONObject("location").get("country").toString();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                error = true;
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

            if (error) {
                isLoading = false;
                Intent intent = new Intent(getActivity(), ErrorActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {

                //GetBitmapFromURL bitm = new GetBitmapFromURL();
                //bitm.execute(imageCard);
                setList(webcams1.length);
            }
        }
    }

    private void setList(Integer length) {
        HashMap<String, String> map = new HashMap<String, String>();

        //****----костыль
        int[][] image = {
                {R.drawable.preview1, R.drawable.preview2, R.drawable.preview3, R.drawable.preview4},
                {R.drawable.preview5, R.drawable.preview6},
                {R.drawable.preview7, R.drawable.preview8},
                {R.drawable.preview9, R.drawable.preview10, R.drawable.preview11, R.drawable.preview12, R.drawable.preview13}};

        data.clear();
        for (int i = 0; i < length; i++) {
            map = new HashMap<String, String>();
            map.put("status", statusCard[idCountry[i]]);
            map.put("title", titleCard[idCountry[i]]);
            map.put("image", String.valueOf(image[idCountry[i]][0]));
            map.put("info1", otherInfoCard1[idCountry[i]]);
            map.put("info2", otherInfoCard2[idCountry[i]]);
            data.add(map);
        }
        String[] from = {"status", "title", "image", "info1", "info2"};

        int[] to = {R.id.statusCard, R.id.titleCard, R.id.imagePreviewCamera, R.id.adress, R.id.subAdress};


        adapter = new SimpleAdapter(getActivity(), data, R.layout.item_card_list, from, to);

        isLoading = false;

        loadProgressBar.setVisibility(View.GONE);

        setListAdapter(adapter);
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

            int[][] image = {{R.drawable.preview1, R.drawable.preview2, R.drawable.preview3, R.drawable.preview4}, {R.drawable.preview5, R.drawable.preview6}, {R.drawable.preview7, R.drawable.preview8}, {R.drawable.preview9, R.drawable.preview10, R.drawable.preview11, R.drawable.preview12, R.drawable.preview13}};

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