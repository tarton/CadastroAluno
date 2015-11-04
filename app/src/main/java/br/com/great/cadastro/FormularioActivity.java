package br.com.great.cadastro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.great.cadastro.dao.AlunoDAO;
import br.com.great.cadastro.modelo.Aluno;

/**
 * Created by ufc110.pedrosa on 27/10/2015.
 */
public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        Button enviar = (Button) findViewById(R.id.btn_enviar);

        helper = new FormularioHelper(this);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FormularioActivity.this, "Dados enviados", Toast.LENGTH_SHORT).show();

                Aluno aluno = helper.pegaAlunoDoFormulario();

                AlunoDAO dao = new AlunoDAO(FormularioActivity.this);
                dao.insere(aluno);
                dao.close();


                finish();
            }
        });

    }
}
