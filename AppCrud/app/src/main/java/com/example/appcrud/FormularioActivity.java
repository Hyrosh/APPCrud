package com.example.appcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etCategoria;
    private Spinner spAno;
    private Button btnSalvar;
    private String acao;
    private Anime anime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        etCategoria = findViewById(R.id.etCategoria);
        spAno = findViewById(R.id.spAno);
        btnSalvar = findViewById(R.id.btnSalvar);

        acao = getIntent().getStringExtra("acao");
        if (acao.equals("editar")) {
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

    }

    private void carregarFormulario() {
        int idAnime = getIntent().getIntExtra("idAnime", 0);
        if (idAnime != 0) {
            anime = AnimeDAO.getAnimeById(this, idAnime);

            etNome.setText(anime.nome);
            etCategoria.setText(anime.categoria);


            String[] arrayAno = getResources().getStringArray(R.array.arrayAno);
            for (int i = 1; i < arrayAno.length; i++) {
                if (Integer.valueOf(arrayAno[i]) == anime.getAno()) {
                    spAno.setSelection(i);
                }
            }
        }
    }

    private void salvar() {
        if (spAno.getSelectedItemPosition() == 0 || etNome.getText().toString().isEmpty()) {

            Toast.makeText(this, "Todos campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();

        } else {

            if (acao.equals("novo")) {
                anime = new Anime();
            }

            anime.nome = etNome.getText().toString();
            anime.categoria = etCategoria.getText().toString();
            anime.setAno(Integer.valueOf(spAno.getSelectedItem().toString()));

            if (acao.equals("editar")) {
                AnimeDAO.editar(anime, this);
                finish();
            } else {
                AnimeDAO.inserir(anime, this);
                etNome.setText("");
                etCategoria.setText("");
                spAno.setSelection(0);
            }
        }
    }
}