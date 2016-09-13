package com.willcrisis.campanha.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dia {
    public Integer id;
    public String atributo;
    public String textos;
    public List<Livro> livros;

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

    public String getNome() {
        switch (id) {
            case 1:
                return "Domingo";
            case 2:
                return "Segunda-feira";
            case 3:
                return "Terça-feira";
            case 4:
                return "Quarta-feira";
            case 5:
                return "Quinta-feira";
            case 6:
                return "Sexta-feira";
            case 7:
                return "Sábado";
            default:
                return null;
        }
    }
}
