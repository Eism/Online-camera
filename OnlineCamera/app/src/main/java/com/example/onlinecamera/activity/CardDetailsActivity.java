package com.example.onlinecamera.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.onlinecamera.R;
import com.example.onlinecamera.fragment.CameraListFragment;

import org.json.JSONArray;
import org.json.JSONException;

public class CardDetailsActivity extends AppCompatActivity {

    private CameraListFragment cameraListFragment;
    private FragmentTransaction ft;

    JSONArray webcams;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbal_actionbar);
        setSupportActionBar(mActionBarToolbar);

        Intent intent = getIntent();
        String jsonArray = intent.getStringExtra("webcams");
        Integer id =intent.getIntExtra("id",0);
        if (null != jsonArray) {
            try {
                webcams = new JSONArray(jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        cameraListFragment = new CameraListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("webcams", webcams.toString());
        bundle.putInt("id",id);
        cameraListFragment.setArguments(bundle);


        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contentCamera, cameraListFragment);
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Камера");

        MenuItem appInfoMenuItem = menu.findItem(R.id.action_app_info);
        appInfoMenuItem.setVisible(false);

        MenuItem filterMenuItem = menu.findItem(R.id.action_filter);
        filterMenuItem.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_coord:
                if (null==webcams) break;
                try {
                    String latitude = webcams.getJSONObject(0).getJSONObject("location").get("latitude").toString();
                    String longitude = webcams.getJSONObject(0).getJSONObject("location").get("longitude").toString();
                    String uri = "geo:"+ latitude + "," + longitude;
                    startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case android.R.id.home:
                onBackPressed();
                //NavUtils.navigateUpFromSameTask(this);
                return true;

        }
        return true;
    }
}
