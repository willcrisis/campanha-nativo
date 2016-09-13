package com.willcrisis.campanha;

import android.app.Application;

import com.willcrisis.campanha.service.SemanaService;

/**
 * Created by willian.krause on 12/09/2016.
 */
public class Campanha extends Application {

    private SemanaService semanaService;

    @Override
    public void onCreate() {
        super.onCreate();
        semanaService = SemanaService.getInstance(getAssets());
    }

    public SemanaService getSemanaService() {
        return semanaService;
    }
}
