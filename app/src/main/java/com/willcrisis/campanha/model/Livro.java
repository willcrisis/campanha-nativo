package com.willcrisis.campanha.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    String nome;
    List<Capitulo> capitulos;

    public Livro(String nome, ArrayList<Capitulo> capitulos) {
        this.nome = nome;
        this.capitulos = capitulos;
    }

    public static Livro fromJson(JSONObject object) throws JSONException {
        ArrayList<Capitulo> capitulos = new ArrayList<>();
        JSONArray array = object.getJSONArray("capitulos");
        for (int i = 0; i < array.length(); i++) {
            capitulos.add(Capitulo.fromJson(array.getJSONObject(i)));
        }
        return new Livro(object.getString("nome"), capitulos);
    }
}
