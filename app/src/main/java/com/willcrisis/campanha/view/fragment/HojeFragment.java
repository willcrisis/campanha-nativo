package com.willcrisis.campanha.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.willcrisis.campanha.Campanha;
import com.willcrisis.campanha.R;
import com.willcrisis.campanha.model.Dia;
import com.willcrisis.campanha.model.Semana;
import com.willcrisis.campanha.service.SemanaService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HojeFragment extends Fragment {

    @BindView(R.id.hoje_semana) TextView campoSemana;
    @BindView(R.id.hoje_dia) TextView campoDia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Campanha app = (Campanha) getActivity().getApplication();
        SemanaService service = app.getSemanaService();

        Semana semana = service.findSemana(service.getSemana());
        Dia dia = semana.findDia(service.getDia());

        View view = inflater.inflate(R.layout.fragment_hoje, container, false);
        ButterKnife.bind(this, view);

        campoSemana.setText(semana.id.toString());
        campoDia.setText(dia.getNome());

        return view;
    }

}
