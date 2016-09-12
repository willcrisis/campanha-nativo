package com.willcrisis.campanha.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Capitulo {
    public Integer id;
    public List<Versiculo> versiculos;

    public Capitulo(int id, ArrayList<Versiculo> versiculos) {
        this.id = id;
        this.versiculos = versiculos;
    }

    public static Capitulo fromJson(JSONObject object) throws JSONException {
        ArrayList<Versiculo> versiculos = new ArrayList<>();
        JSONArray array = object.getJSONArray("versiculos");
        for (int i = 0; i < array.length(); i++) {
            versiculos.add(Versiculo.fromJson(array.getJSONObject(i)));
        }
        return new Capitulo(object.getInt("id"), versiculos);
    }
}
