package com.example.onlinecamera.fragment;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.onlinecamera.R;
import com.example.onlinecamera.activity.PlayerActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ELNUR on 22.10.2016.
 */

public class CameraListFragment extends ListFragment {

    private final String[] catnames = new String[]{"Рыжик", "Барсик",
            "Мурзик", "Мурка", "Васька", "Томасина", "Бобик", "Кристина",
            "Пушок", "Дымка", "Кузя", "Китти", "Барбос", "Масяня", "Симба"};

    private JSONArray webcams;
    private Integer ID;

    String[] idCamera;
    String[] statusCamera;
    String[] titleCamera;
    String[] imageCamera;
    String[] otherInfoCamera1;
    String[] otherInfoCamera2;



    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.camera_list, container, false);

        Bundle bundle=this.getArguments();
        ID=bundle.getInt("id");
        if (null!= bundle){
            try {
                webcams = new JSONArray(bundle.getString("webcams"));

                Integer lenght = webcams.length();
                idCamera = new String[lenght];
                statusCamera = new String[lenght];
                titleCamera = new String[lenght];
                imageCamera = new String[lenght];
                otherInfoCamera1 = new String[lenght];
                otherInfoCamera2 = new String[lenght];

                for (int i = 0; i < webcams.length(); i++) {
                    try {
                        JSONObject jsonObject = webcams.getJSONObject(i);

                        idCamera[i] = jsonObject.get("id").toString();
                        statusCamera[i] = jsonObject.get("status").toString();
                        titleCamera[i] = jsonObject.get("title").toString();
                        imageCamera[i] = jsonObject.getJSONObject("image").getJSONObject("current").get("preview").toString();
                        otherInfoCamera1[i] = jsonObject.getJSONObject("location").get("continent").toString();
                        otherInfoCamera2[i] = jsonObject.getJSONObject("location").get("country").toString() +" "+ jsonObject.getJSONObject("location").get("city").toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                HashMap<String, String> map = new HashMap<String, String>();
                int[][] image = {{R.drawable.preview1,R.drawable.preview2,R.drawable.preview3,R.drawable.preview4},{R.drawable.preview5,R.drawable.preview6},{R.drawable.preview7,R.drawable.preview8},{R.drawable.preview9,R.drawable.preview10,R.drawable.preview11,R.drawable.preview12,R.drawable.preview13}};

                for (int i = 0; i < statusCamera.length; i++) {
                    map = new HashMap<String, String>();
                    //map.put("status", statusCamera[i]);
                    map.put("title", titleCamera[i]);
                    //Drawable d = new BitmapDrawable(getResources(), imageCamera[i]);
                    map.put("image", String.valueOf(image[ID][i]));
                    //map.put("info1", otherInfoCamera1[i]);
                    //map.put("info2", otherInfoCamera2[i]);

                    data.add(map);
                }

                String[] from = {/*"status",*/ "title", "image",/* "info1", "info2"*/};

                int[] to = {/*R.id.statusCard,*/ R.id.titleCamera, R.id.imagePreviewCamera,/*R.id.adress, R.id.subAdress*/ };

                adapter = new SimpleAdapter(getActivity(), data, R.layout.item_camera_list, from, to);
                setListAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return root;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Long ID=id;

        Intent intent = new Intent(getActivity(), PlayerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("idCamera",idCamera[position].toString());
        startActivity(intent);
    }
}
