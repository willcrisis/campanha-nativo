package com.willcrisis.campanha.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.willcrisis.campanha.view.PesquisaFragment;
import com.willcrisis.campanha.view.fragment.HojeFragment;

/**
 * Created by willian.krause on 28/09/2016.
 */
public class SwipeAdapter extends FragmentPagerAdapter {

    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HojeFragment();
            case 1:
                return new PesquisaFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
