package com.wzh.app.sideslip;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SettingFragment extends Fragment {



    public SettingFragment() {

    }

    public static SettingFragment newInstance(String parm1) {

        Bundle args = new Bundle();
        args.putString("agrs3",parm1);
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        Bundle bundle = getArguments();
        String agrs3 = bundle.getString("agrs3");
        TextView tv = view.findViewById(R.id.tv_fragment_text);
        tv.setText(agrs3);

        return view;
    }


    }






