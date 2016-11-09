package com.example.onlinecamera.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.onlinecamera.R;
import com.example.onlinecamera.activity.BaseActivity;

/**
 * Created by ELNUR on 21.10.2016.
 */

public class FilterFragment extends Fragment {

    private TextView btAll, btFilter1, btFilter2, btFilter3;

    private Integer id = 0; //выбранный фильтр

    private BaseActivity context;

    public void setContex(BaseActivity context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_filter, null);

        btAll = (TextView) v.findViewById(R.id.buttonAll);
        btFilter1 = (TextView) v.findViewById(R.id.buttonFilter1);
        btFilter2 = (TextView) v.findViewById(R.id.buttonFilter2);
        btFilter3 = (TextView) v.findViewById(R.id.buttonFilter3);

        // закрашиваем выбранный ранее фильтр
        Bundle bundle = this.getArguments();
        Integer buttonID = bundle.getInt("id");
        if (null != buttonID) {
            switch (buttonID) {
                case 0:
                    btAll.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    break;
                case 1:
                    btFilter1.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    break;
                case 2:
                    btFilter2.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    break;
                case 3:
                    btFilter3.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    break;
            }
        }

        btAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (0 != id) {
                    id = 0;
                    context.changeListFragment(id);
                    btAll.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    btFilter1.setTextColor(getActivity().getResources().getColor(R.color.colorBlack87));
                    btFilter2.setTextColor(getActivity().getResources().getColor(R.color.colorBlack87));
                    btFilter3.setTextColor(getActivity().getResources().getColor(R.color.colorBlack87));
                }
                context.removeFilterFragment();

            }
        });

        btFilter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (1 != id) {
                    id = 1;
                    context.changeListFragment(id);
                    btAll.setTextColor(getActivity().getResources().getColor(R.color.colorBlack87));
                    btFilter1.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    btFilter2.setTextColor(getActivity().getResources().getColor(R.color.colorBlack87));
                    btFilter3.setTextColor(getActivity().getResources().getColor(R.color.colorBlack87));
                }
                context.removeFilterFragment();
            }
        });

        btFilter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (2 != id) {
                    id = 2;
                    context.changeListFragment(id);
                    btAll.setTextColor(getActivity().getResources().getColor(R.color.colorBlack87));
                    btFilter1.setTextColor(getActivity().getResources().getColor(R.color.colorBlack87));
                    btFilter2.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    btFilter3.setTextColor(getActivity().getResources().getColor(R.color.colorBlack87));
                }
                context.removeFilterFragment();
            }
        });

        btFilter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (3 != id) {
                    id = 3;
                    context.changeListFragment(id);
                    btAll.setTextColor(getActivity().getResources().getColor(R.color.colorBlack87));
                    btFilter1.setTextColor(getActivity().getResources().getColor(R.color.colorBlack87));
                    btFilter2.setTextColor(getActivity().getResources().getColor(R.color.colorBlack87));
                    btFilter3.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                }
                context.removeFilterFragment();
            }
        });

        return v;
    }

}