package com.example.onlinecamera.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.onlinecamera.fragment.CardListFragment;
import com.example.onlinecamera.fragment.FilterFragment;
import com.example.onlinecamera.R;

public class BaseActivity extends AppCompatActivity {

    private FilterFragment filterFragment;
    private FragmentTransaction ft;
    private Boolean isFilterVisible = false; //состояние фрагмента filter

    private CardListFragment cardListFragment;
    private Integer filterButtonID = 0; // номер выбранного фильтра

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbal_actionbar);
        setSupportActionBar(mActionBarToolbar);

        filterFragment = new FilterFragment();
        filterFragment.setContex(this);

        cardListFragment = new CardListFragment();

        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contentCard, cardListFragment);
        ft.commit();

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.contentFrag);
        frameLayout.bringToFront();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_app_info:
                removeFilterFragment();
                Intent intent = new Intent(getApplicationContext(), AppInfoActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_filter:

                if (!isFilterVisible) {
                    replaceFilterFragment();
                } else {
                    removeFilterFragment();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void changeListFragment(Integer id) {
        filterButtonID = id;
        cardListFragment.changeList(id); // изменяем карточки в зависимости от фильтра
    }

    public void replaceFilterFragment() {
        ft = getFragmentManager().beginTransaction();

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setEnabled(false);     // убираем прокручивание

        ft.setCustomAnimations(R.anim.to_left, R.anim.to_right);
        ft.replace(R.id.contentFrag, filterFragment);

        Bundle bundle = new Bundle();
        bundle.putInt("id", filterButtonID);
        filterFragment.setArguments(bundle);

        isFilterVisible = !isFilterVisible;
        ft.commit();
    }

    public void removeFilterFragment() {
        ListView listView = (ListView) findViewById(android.R.id.list);
        ft = getFragmentManager().beginTransaction();

        ft.setCustomAnimations(R.anim.to_left, R.anim.to_right);
        ft.remove(filterFragment);
        listView.setEnabled(true);     // возвращаем прокручивание

        isFilterVisible = !isFilterVisible;
        ft.commit();
    }

}