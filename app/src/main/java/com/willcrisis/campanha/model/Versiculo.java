package com.willcrisis.campanha.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Versiculo {
    public Integer id;
    public String texto;

    public Versiculo(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public static Versiculo fromJson(JSONObject object) throws JSONException {
        return new Versiculo(object.getInt("id"), object.getString("texto"));
    }
}
