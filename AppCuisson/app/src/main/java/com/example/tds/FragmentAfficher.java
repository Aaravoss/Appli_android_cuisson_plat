package com.example.tds;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentAfficher extends Fragment {

    public FragmentAfficher(){
        //Required empty public constructor
    }

    /**
     * Factory de la classe FragmentAfficher
     * @return une nouvelle instance du fragment FragmentAfficher
     */
    public static FragmentAfficher newInstance(){
        FragmentAfficher fragment = new FragmentAfficher();
        return fragment;
    }

    @Override
    public void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.page_afficher, container, false);
    }
}
