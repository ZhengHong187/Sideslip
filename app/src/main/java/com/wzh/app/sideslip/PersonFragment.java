package com.wzh.app.sideslip;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {


    public PersonFragment() {
        // Required empty public constructor
    }

    public static PersonFragment newInstance(String parm1) {

        Bundle args = new Bundle();
        PersonFragment fragment = new PersonFragment();
        args.putString("agrs2",parm1);
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment, container, false);
        Bundle bundle =getArguments();
        String agrs2 = bundle.getString("agrs2");
        TextView tv = view.findViewById(R.id.tv_fragment_text);
        tv.setText(agrs2);
        return view;
    }

}
