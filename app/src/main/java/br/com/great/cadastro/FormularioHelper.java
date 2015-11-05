package br.com.great.cadastro;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.great.cadastro.modelo.Aluno;


public class FormularioHelper {

    private Aluno aluno;
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEndereco;
    private EditText campoSite;
    private RatingBar campoNota;

    public FormularioHelper(FormularioActivity activity) {      // Como essa classe não é uma activity, então temos que importar FormularioActivity, para que o findViewById() consiga enxergar o os IDs dos campos.
        aluno = new Aluno();

        campoNome = (EditText) activity.findViewById(R.id.form_nome);
        campoTelefone = (EditText) activity.findViewById(R.id.form_telefone);
        campoEndereco = (EditText) activity.findViewById(R.id.form_endereco);
        campoSite = (EditText) activity.findViewById(R.id.form_site);
        campoNota = (RatingBar) activity.findViewById(R.id.form_nota);
    }

    public Aluno pegaAlunoDoFormulario() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String endereco = campoEndereco.getText().toString();
        String site = campoSite.getText().toString();
        Double nota = (double) campoNota.getRating();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEndereco(endereco);
        aluno.setSite(site);
        aluno.setNota(Double.valueOf(nota));

        return aluno;
    }

}
