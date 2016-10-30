package com.example.onlinecamera.activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.onlinecamera.fragment.CardListFragment;
import com.example.onlinecamera.fragment.FilterFragment;
import com.example.onlinecamera.R;

public class BaseActivity extends AppCompatActivity {

    private FilterFragment filterFragment;
    private FragmentTransaction ft;
    private Boolean isFilterVisible= false;

    private CardListFragment cardListFragment;
    private ProgressBar loadProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbal_actionbar);
        setSupportActionBar(mActionBarToolbar);

        loadProgressBar = (ProgressBar) findViewById(R.id.loadProgressBar);

        filterFragment =new FilterFragment();
        cardListFragment = new CardListFragment();

        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contentCard,cardListFragment);
        ft.commit();


        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.contentFrag);
        frameLayout.bringToFront();

        //new IsLoadedFragmentList().execute();
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

                ListView listView = (ListView) findViewById(android.R.id.list);
                ft = getFragmentManager().beginTransaction();

                if (!isFilterVisible) {
                    listView.setEnabled(false);
                    ft.setCustomAnimations(R.anim.to_left,R.anim.to_right);
                    ft.replace(R.id.contentFrag, filterFragment);
                }else {
                    ft.setCustomAnimations(R.anim.to_left,R.anim.to_right);
                    ft.remove(filterFragment);
                    listView.setEnabled(true);
                }
                isFilterVisible=!isFilterVisible;
                ft.commit();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private class IsLoadedFragmentList extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            while (true) try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            return null;
        }

        protected void onProgressUpdate(Integer... integers) {
        }

        protected void onPostExecute(Boolean response) {
            loadProgressBar.setVisibility(View.GONE);
        }
    }
}
