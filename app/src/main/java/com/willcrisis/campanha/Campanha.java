package com.willcrisis.campanha;

import android.app.Application;

import com.willcrisis.campanha.service.SemanaService;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by willian.krause on 12/09/2016.
 */
public class Campanha extends Application {

    private SemanaService semanaService;

    @Override
    public void onCreate() {
        super.onCreate();
        semanaService = SemanaService.getInstance();
    }

    public SemanaService getSemanaService() {
        return semanaService;
    }
}
