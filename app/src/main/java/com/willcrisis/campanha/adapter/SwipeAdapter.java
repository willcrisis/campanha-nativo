package com.willcrisis.campanha.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.willcrisis.campanha.view.fragment.HojeFragment;
import com.willcrisis.campanha.view.fragment.PesquisaFragment;
import com.willcrisis.campanha.view.fragment.SemanaDiaFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willian.krause on 28/09/2016.
 */
public class SwipeAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public SwipeAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new SemanaDiaFragment());
        fragments.add(new HojeFragment());
        fragments.add(new PesquisaFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
