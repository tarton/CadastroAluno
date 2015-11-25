package br.com.great.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.great.cadastro.dao.AlunoDAO;
import br.com.great.cadastro.extras.Extras;
import br.com.great.cadastro.modelo.Aluno;


public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        helper = new FormularioHelper(this);

        Intent intent = this.getIntent();

        final Aluno alunoParaSerAlterado = (Aluno) intent.getSerializableExtra(Extras.ALUNO_SELECIONADO);

        final Button botao = (Button) findViewById(R.id.btn_enviar);

        if(alunoParaSerAlterado != null) {
            Toast.makeText(this, "Aluno: " + alunoParaSerAlterado, Toast.LENGTH_SHORT).show();
            botao.setText("Alterar");
            helper.colocaAlunoNoFormulario(alunoParaSerAlterado);
        }

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FormularioActivity.this, "Dados enviados", Toast.LENGTH_SHORT).show();

                Aluno aluno = helper.pegaAlunoDoFormulario();

                AlunoDAO dao = new AlunoDAO(FormularioActivity.this);

                if (alunoParaSerAlterado != null) {
                    aluno.setId(alunoParaSerAlterado.getId());
                    dao.atualizar(aluno);
                } else {
                    dao.salvar(aluno);
                }

                dao.close();

                finish();
            }
        });

    }
}
