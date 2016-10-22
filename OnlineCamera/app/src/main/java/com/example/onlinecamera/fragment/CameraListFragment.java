package com.example.onlinecamera.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.onlinecamera.R;

/**
 * Created by ELNUR on 22.10.2016.
 */

public class CameraListFragment extends ListFragment {

    private final String[] catnames = new String[] { "Рыжик", "Барсик",
            "Мурзик", "Мурка", "Васька", "Томасина", "Бобик", "Кристина",
            "Пушок", "Дымка", "Кузя", "Китти", "Барбос", "Масяня", "Симба" };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.camera_list, container, false);

        ListAdapter myListAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.item_camera_list, R.id.titleCamera, catnames);
        setListAdapter(myListAdapter);
        setRetainInstance(true);
        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Long ID=id;

        Toast.makeText(getActivity(),
                ID.toString(),
                Toast.LENGTH_LONG).show();
    }
}
