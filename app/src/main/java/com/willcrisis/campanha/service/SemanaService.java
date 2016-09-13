package com.willcrisis.campanha.service;

import android.content.res.AssetManager;

import com.willcrisis.campanha.model.Semana;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SemanaService {
    private Integer semana;
    private Integer dia;

    private static SemanaService instance;

    private List<Semana> semanas;

    public SemanaService(AssetManager assets) {
        lerSemanas(assets);

        Calendar dataBase = getDataBase();

        Calendar hoje = Calendar.getInstance();
        zerarHoraData(hoje);

        this.dia = hoje.get(Calendar.DAY_OF_WEEK);

        calcularSemana(dataBase, hoje);
    }

    private void calcularSemana(Calendar dataBase, Calendar hoje) {
        long diferenca = hoje.getTimeInMillis() - dataBase.getTimeInMillis();
        long diferencaEmDias = TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS);

        while (diferencaEmDias >= 49) {
            diferencaEmDias -= 49;
        }

        this.semana = (int) Math.floor(diferencaEmDias / 7) + 1;
    }

    private void lerSemanas(AssetManager assets) {
        try {
            JSONArray array = new JSONArray(lerJson(assets));

            semanas = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                semanas.add(Semana.fromJson(array.getJSONObject(i)));
            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException("Não foi possível abrir o arquivo de dados.", e);
        }
    }

    private String lerJson(AssetManager assets) throws IOException {
        InputStream stream = assets.open("dados.json");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = stream.read(buffer)) != -1) {
            output.write(buffer, 0, length);
        }
        stream.close();
        return output.toString("UTF-8");
    }

    private void zerarHoraData(Calendar hoje) {
        hoje.set(Calendar.HOUR, 0);
        hoje.set(Calendar.MINUTE, 0);
        hoje.set(Calendar.SECOND, 0);
        hoje.set(Calendar.MILLISECOND, 0);
    }

    private Calendar getDataBase() {
        //Data base: 11/09/2016. O campo MONTH do Calendar é 0-based, ou seja, 0 - Janeiro, 1 - Fevereiro, etc.
        Calendar dataBase = Calendar.getInstance();
        dataBase.set(Calendar.DAY_OF_MONTH, 11);
        dataBase.set(Calendar.MONTH, 8);
        dataBase.set(Calendar.YEAR, 2016);
        zerarHoraData(dataBase);
        return dataBase;
    }

    public Integer getSemana() {
        return semana;
    }

    public Integer getDia() {
        return dia;
    }

    public static SemanaService getInstance(AssetManager assets) {
        if (instance == null) {
            instance = new SemanaService(assets);
        }
        return instance;
    }

    public List<Semana> getSemanas() {
        return semanas;
    }

    public Semana findSemana(Integer id) {
        for (Semana semana : semanas) {
            if (id.equals(semana.id)) {
                return semana;
            }
        }
        return null;
    }
}
