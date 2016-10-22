package com.example.onlinecamera;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by ELNUR on 22.10.2016.
 */

public class CardListFragment extends ListFragment {

    private final String[] catnames = new String[] { "Рыжик", "Барсик",
            "Мурзик", "Мурка", "Васька", "Томасина", "Бобик", "Кристина",
            "Пушок", "Дымка", "Кузя", "Китти", "Барбос", "Масяня", "Симба" };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.card_list, container, false);

        ListAdapter myListAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.item_card_list, R.id.titleCard, catnames);
        setListAdapter(myListAdapter);
        setRetainInstance(true);
        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(getActivity(),
                getListView().getItemAtPosition(position).toString(),
                Toast.LENGTH_LONG).show();
    }
}
