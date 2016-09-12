package com.willcrisis.campanha.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dia {
    Integer id;
    String atributo;
    String textos;
    List<Livro> livros;

    public Dia(int id, String atributo, String textos, ArrayList<Livro> livros) {
        this.id = id;
        this.atributo = atributo;
        this.textos = textos;
        this.livros = livros;
    }

    public static Dia fromJson(JSONObject object) throws JSONException {
        ArrayList<Livro> livros = new ArrayList<>();
        JSONArray array = object.getJSONArray("livros");
        for (int i = 0; i < array.length(); i++) {
            livros.add(Livro.fromJson(array.getJSONObject(i)));
        }
        return new Dia(object.getInt("id"), object.getString("atributo"), object.getString("textos"), livros);
    }
}
