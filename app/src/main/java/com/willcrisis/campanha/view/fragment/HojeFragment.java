package com.willcrisis.campanha.view.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

public class HojeFragment extends Fragment {

    @BindView(R.id.hoje_semana) TextView campoSemana;
    @BindView(R.id.hoje_dia) TextView campoDia;
    @BindView(R.id.hoje_atributo) TextView campoAtributo;
    @BindView(R.id.hoje_textos) TextView campoTextos;
    @BindView(R.id.hoje_lista) LinearLayout campoLista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Campanha app = (Campanha) getActivity().getApplication();
        SemanaService service = app.getSemanaService();

        Semana semana = service.findSemana(service.getSemana());
        Dia dia = semana.findDia(service.getDia());

        View view = inflater.inflate(R.layout.fragment_hoje, container, false);
        ButterKnife.bind(this, view);

        campoSemana.setText(app.getString(R.string.texto_semana, semana.id, semana.tema));
        campoDia.setText(dia.getNome());

        campoAtributo.setTypeface(Typeface.createFromAsset(app.getAssets(), "fonts/GreatVibes-Regular.ttf"));
        campoAtributo.setText(app.getString(R.string.atributo, dia.atributo));

        campoTextos.setText(dia.textos);

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

        return view;
    }
}
