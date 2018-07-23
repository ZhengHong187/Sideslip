package com.wzh.app.sideslip;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class messagefragment extends Fragment {


    public messagefragment() {

    }


    public static messagefragment newInstance(String param1) {
        messagefragment fragment = new messagefragment();
        Bundle args = new Bundle();
        args.putString("agrs",param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        Bundle bundle =getArguments();
        String agrs1 = bundle.getString("agrs1");
        TextView tv = view.findViewById(R.id.tv_fragment_text);
        tv.setText(agrs1);
        return view;
    }



}
