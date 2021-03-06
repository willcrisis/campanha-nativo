package com.willcrisis.campanha.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Semana {
    public Integer id;
    public String tema;
    public List<Dia> dias;

    public Semana(int id, String tema, ArrayList<Dia> dias) {
        this.id = id;
        this.tema = tema;
        this.dias = dias;
    }

    public static Semana fromJson(JSONObject object) throws JSONException {
        ArrayList<Dia> dias = new ArrayList<>();
        JSONArray array = object.getJSONArray("dias");
        for (int i = 0; i < array.length(); i++) {
            dias.add(Dia.fromJson(array.getJSONObject(i)));
        }
        return new Semana(object.getInt("id"), object.getString("tema"), dias);
    }

    public Dia findDia(Integer id) {
        for (Dia dia : dias) {
            if (id.equals(dia.id)) {
                return dia;
            }
        }
        return null;
    }
}
