package com.willcrisis.campanha.service;

import java.util.Date;

/**
 * Created by willian.krause on 12/09/2016.
 */
public class SemanaService {
    private Integer semana;
    private Integer dia;

    private Date dataBase;

    private static SemanaService instance;

    public SemanaService() {
    }

    public Integer getSemana() {
        return semana;
    }

    public Integer getDia() {
        return dia;
    }

    public static SemanaService getInstance() {
        if (instance == null) {
            instance = new SemanaService();
        }
        return instance;
    }
}
