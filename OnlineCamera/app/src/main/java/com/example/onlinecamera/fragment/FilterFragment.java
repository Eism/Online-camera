package com.example.onlinecamera.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.onlinecamera.R;

/**
 * Created by ELNUR on 21.10.2016.
 */

public class FilterFragment extends Fragment{

    private Button btAll,btFilter1,btFilter2,btFilter3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_filter, null);

        btAll=(Button) v.findViewById(R.id.buttonAll);
        btFilter1=(Button) v.findViewById(R.id.buttonFilter1);
        btFilter2=(Button) v.findViewById(R.id.buttonFilter2);
        btFilter3=(Button) v.findViewById(R.id.buttonFilter3);


        btAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),
                        btAll.getText(),
                        Toast.LENGTH_LONG).show();
            }
        });

        btFilter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),
                        btFilter1.getText(),
                        Toast.LENGTH_LONG).show();
            }
        });

        btFilter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),
                        btFilter2.getText(),
                        Toast.LENGTH_LONG).show();
            }
        });

        btFilter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),
                        btFilter3.getText(),
                        Toast.LENGTH_LONG).show();
            }
        });


        return v;
    }
}
