package com.example.tds.outils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tds.FragmentAfficher;
import com.example.tds.FragmentAjouter;

public class PageAdapter extends FragmentStateAdapter {

    /** Nombre de fragments gérés par cet adaptateur */
    private static final int NB_FRAGMENTS = 2;

    /**
     * Constructeur par défaut de l'adaptateur
     * @param activite contient le ViewPager qui gérera les fragments
     */
    public PageAdapter(FragmentActivity activite){
        super(activite);
    }

    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return FragmentAfficher.newInstance();
            case 1:
                return FragmentAjouter.newInstance();
            default:
                return null;
        }
    }

    /**
     * @return le nombre de fragments géré par l'adaptateur
     */
    public int getItemCount() {
        return NB_FRAGMENTS;
    }
}
