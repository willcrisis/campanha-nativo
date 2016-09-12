package com.willcrisis.campanha.view.fragment;


import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.willcrisis.campanha.Campanha;
import com.willcrisis.campanha.R;
import com.willcrisis.campanha.service.SemanaService;

public class HojeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Campanha app = (Campanha) getActivity().getApplication();
        SemanaService service = app.getSemanaService();

        Integer semana = service.getSemana();
        Integer dia = service.getDia();

        //alguma coisa para consultar json

        View view = inflater.inflate(R.layout.fragment_hoje, container, false);
        //setar dados do json na view

        return view;
    }

}
