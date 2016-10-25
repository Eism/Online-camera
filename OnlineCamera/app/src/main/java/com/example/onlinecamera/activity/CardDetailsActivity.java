package com.example.onlinecamera.activity;

import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.onlinecamera.R;
import com.example.onlinecamera.fragment.CameraListFragment;

public class CardDetailsActivity extends AppCompatActivity {

    private CameraListFragment cameraListFragment;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbal_actionbar);
        setSupportActionBar(mActionBarToolbar);

        cameraListFragment=new CameraListFragment();

        ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.contentCamera,cameraListFragment);
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
}
