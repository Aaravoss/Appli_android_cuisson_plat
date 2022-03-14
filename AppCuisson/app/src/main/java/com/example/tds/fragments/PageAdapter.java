package com.example.tds.fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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
                return null; //TODO remplacer par FragmentAjouter.newInstance(); une fois la page ajouter créée
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
