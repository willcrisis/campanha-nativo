package com.willcrisis.campanha.view.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.willcrisis.campanha.Campanha;
import com.willcrisis.campanha.R;
import com.willcrisis.campanha.model.Capitulo;
import com.willcrisis.campanha.model.Dia;
import com.willcrisis.campanha.model.Livro;
import com.willcrisis.campanha.model.Semana;
import com.willcrisis.campanha.model.Versiculo;
import com.willcrisis.campanha.service.SemanaService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SemanaDiaFragment extends Fragment {

    @BindView(R.id.semana_dia_semana)
    Spinner spinnerSemana;
    @BindView(R.id.semana_dia_dia)
    Spinner spinnerDia;

    @BindView(R.id.semana_dia_result_semana)
    TextView campoSemana;
    @BindView(R.id.semana_dia_result_dia)
    TextView campoDia;
    @BindView(R.id.semana_dia_result_atributo)
    TextView campoAtributo;
    @BindView(R.id.semana_dia_result_textos)
    TextView campoTextos;
    @BindView(R.id.semana_dia_result_lista)
    LinearLayout campoLista;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final Campanha app = (Campanha) getActivity().getApplication();

        final SemanaService service = app.getSemanaService();

        View view = inflater.inflate(R.layout.fragment_semana_dia, container, false);
        ButterKnife.bind(this, view);

        final Semana semana = service.findSemana(1);
        final Dia dia = semana.findDia(1);

        campoAtributo.setTypeface(Typeface.createFromAsset(app.getAssets(), "fonts/GreatVibes-Regular.ttf"));

        efetuarPesquisa(inflater, app, semana, dia);

        spinnerDia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Semana encontrada = service.findSemana(spinnerSemana.getSelectedItemPosition() + 1);
                Dia encontrado = encontrada.findDia(position + 1);
                efetuarPesquisa(inflater, app, encontrada, encontrado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selecionarPadrao(service, inflater, app);
            }
        });

        spinnerSemana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Semana encontrada = service.findSemana(position + 1);
                Dia encontrado = encontrada.findDia(spinnerDia.getSelectedItemPosition() + 1);
                efetuarPesquisa(inflater, app, encontrada, encontrado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selecionarPadrao(service, inflater, app);
            }
        });

        return view;
    }

    private void selecionarPadrao(SemanaService service, LayoutInflater inflater, Campanha app) {
        Semana encontrada = service.findSemana(spinnerSemana.getSelectedItemPosition() + 1);
        Dia encontrado = encontrada.findDia(spinnerDia.getSelectedItemPosition() + 1);
        efetuarPesquisa(inflater, app, encontrada, encontrado);
    }

    private void efetuarPesquisa(LayoutInflater inflater, Campanha app, Semana semana, Dia dia) {
        campoSemana.setText(app.getString(R.string.texto_semana, semana.id, semana.tema));
        campoDia.setText(dia.getNome());

        campoAtributo.setText(app.getString(R.string.atributo, dia.atributo));

        campoTextos.setText(dia.textos);

        campoLista.removeAllViews();

        for (Livro livro : dia.livros) {
            TextView linhaLivro = (TextView) inflater.inflate(R.layout.row_livro, campoLista, false);
            linhaLivro.setText(livro.nome);
            campoLista.addView(linhaLivro);

            for (Capitulo capitulo : livro.capitulos) {
                TextView linhaCapitulo = (TextView) inflater.inflate(R.layout.row_capitulo, campoLista, false);
                linhaCapitulo.setText(app.getString(R.string.texto_capitulo, capitulo.id));
                campoLista.addView(linhaCapitulo);

                for (Versiculo versiculo : capitulo.versiculos) {
                    TextView linhaVersiculo = (TextView) inflater.inflate(R.layout.row_versiculo, campoLista, false);
                    linhaVersiculo.setText(app.getString(R.string.texto_versiculo, versiculo.id, versiculo.texto));
                    campoLista.addView(linhaVersiculo);
                }
            }
        }

        campoLista.addView(inflater.inflate(R.layout.row_livro, campoLista, false));
    }
}
