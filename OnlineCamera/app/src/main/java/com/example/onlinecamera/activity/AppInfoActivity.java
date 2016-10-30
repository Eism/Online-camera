package com.example.onlinecamera.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.onlinecamera.R;

public class AppInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbal_actionbar);
        setSupportActionBar(mActionBarToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        setTitle("О программе");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MenuItem coordMenuItem = menu.findItem(R.id.action_coord);
        coordMenuItem.setVisible(false);

        MenuItem filterMenuItem = menu.findItem(R.id.action_filter);
        filterMenuItem.setVisible(false);

        MenuItem infoMenuItem = menu.findItem(R.id.action_app_info);
        infoMenuItem.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return true;
    }
}