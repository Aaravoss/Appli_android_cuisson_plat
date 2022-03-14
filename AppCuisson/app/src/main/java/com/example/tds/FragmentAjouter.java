package com.example.tds;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentAjouter extends Fragment {

    public FragmentAjouter(){
        //Required empty public constructor
    }

    /**
     * Factory de la classe FragmentAjouter
     * @return une nouvelle instance du fragment FragmentAjouter
     */
    public static FragmentAjouter newInstance(){
        FragmentAjouter fragment = new FragmentAjouter();
        return fragment;
    }

    @Override
    public void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.page_ajouter, container, false);
    }
}
