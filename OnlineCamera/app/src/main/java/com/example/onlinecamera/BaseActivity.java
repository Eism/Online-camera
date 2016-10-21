package com.example.onlinecamera;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {

    private FilterFragment filterFragment;
    private FragmentTransaction ft;
    private Boolean isFilterVisible= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbal_actionbar);
        setSupportActionBar(mActionBarToolbar);

        filterFragment =new FilterFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        setTitle("Онлайн-камеры");

        MenuItem coordMenuItem = menu.findItem(R.id.action_coord);
        coordMenuItem.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case R.id.action_app_info:
                Intent intent = new Intent(getApplicationContext(), AppInfoActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_filter:
                ft = getFragmentManager().beginTransaction();

                if (!isFilterVisible) {
                    ft.setCustomAnimations(R.anim.to_left,R.anim.to_right);
                    //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.replace(R.id.contentFrag, filterFragment);
                }else {
                    ft.setCustomAnimations(R.anim.to_left,R.anim.to_right);
                    //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    ft.remove(filterFragment);
                }

                isFilterVisible=!isFilterVisible;

                ft.commit();


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
