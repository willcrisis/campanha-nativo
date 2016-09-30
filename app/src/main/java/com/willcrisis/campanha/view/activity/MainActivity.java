package com.willcrisis.campanha.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.willcrisis.campanha.R;
import com.willcrisis.campanha.adapter.SwipeAdapter;
import com.willcrisis.campanha.model.Capitulo;
import com.willcrisis.campanha.model.Dia;
import com.willcrisis.campanha.model.Livro;
import com.willcrisis.campanha.model.Semana;
import com.willcrisis.campanha.model.Versiculo;
import com.willcrisis.campanha.service.SemanaService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.main_frame) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        viewPager.setAdapter(new SwipeAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hoje, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_hoje_compartilhar:
                String textoFormatado = getTextoFormatado();
                Intent shareIntent = getShareTextIntent(textoFormatado);
                startActivity(Intent.createChooser(shareIntent, getString(R.string.compartilhar)));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getTextoFormatado() {
        StringBuilder stringBuilder = new StringBuilder();

        SemanaService service = SemanaService.getInstance(null);
        Semana semana = service.findSemana(service.getSemana());
        Dia dia = semana.findDia(service.getDia());

        stringBuilder.append(getString(R.string.texto_semana, semana.id, semana.tema));
        stringBuilder.append("\n");
        stringBuilder.append(dia.getNome());

        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append(getString(R.string.atributo, dia.atributo));

        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append(dia.textos);

        for (Livro livro : dia.livros) {
            stringBuilder.append("\n");
            stringBuilder.append("\n");
            stringBuilder.append(livro.nome);
            stringBuilder.append("\n");

            for (Capitulo capitulo : livro.capitulos) {
                stringBuilder.append(getString(R.string.texto_capitulo, capitulo.id));
                stringBuilder.append("\n");

                for (Versiculo versiculo : capitulo.versiculos) {
                    stringBuilder.append(getString(R.string.texto_versiculo, versiculo.id, versiculo.texto));
                }
            }
        }

        return stringBuilder.toString();
    }

    private Intent getShareTextIntent(String shareText) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareText);
        return sharingIntent;
    }
}
