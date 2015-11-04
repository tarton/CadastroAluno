package br.com.great.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.great.cadastro.dao.AlunoDAO;
import br.com.great.cadastro.modelo.Aluno;

/**
 * Created by ufc110.pedrosa on 23/10/2015.
 */
public class ListaAlunosActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_alunos);   // Configura os comportamentos da View (listagem_alunos)

        lista = (ListView) findViewById(R.id.lista);

        //final String[] alunos = {"Edgar", "Tarton", "Oliveira", "Pedrosa"};   // Array chumbado!
        /*
        **** Essa parte foi para o onResume()*****

        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getLista();

        // O Adapter é uma classe que adapta objetos Java para a tela, criando um objeto do tipo View.
        // Um dos adapters disponíveis é o ArrayAdapter, que converte listas ou arrays em views.
        // new ArrayAdapter<>(Context, ID_do_layout_a_ser_usado, lista_ou_array_a_ser_exibido);

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        lista.setAdapter(adapter);  // É aqui que o adapter é associado a ListView
        */

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Toast.makeText(ListaAlunosActivity.this, "A posição é " + position, Toast.LENGTH_SHORT).show();
            }
        }); // Representa um clique em um ítem da lista

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view,
                                           int position, long id) {
                Toast.makeText(ListaAlunosActivity.this, "Aluno clicado é " + adapter.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                // Toast.makeText(ListaAlunosActivity.this, "Aluno clicado é " + aluno[posicao], Toast.LENGTH_SHORT).show();

                return true;    // Vou consumir esse evento de clique sozinho? true : false
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CICLO DE VIDA", "onResume");

        // Atualizando a lista de Alunos
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getLista();

        ArrayAdapter<Aluno> adapter =
                new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        lista.setAdapter(adapter);
        // Fim da atualização da lista de Alunos
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);

        // Ou podemos também adicionar os ítens do menu (concatenado num optionButton), um a um da seguinte forma:
        //menu.add("Novo");
        //menu.add("Mapa");
        //menu.add("Enviar Alunos");
        //menu.add("Receber Alunos");
        //menu.add("Preferências");

        // Se for preciso exibir os botões no ActionButton, então basta fazer:
        /*
        MenuItem novo = menu.add("Novo");
        novo.setIcon(R.drawable.ic_person_add);

        MenuItem mapa = menu.add("Mapa");
        mapa.setIcon(R.drawable.ic_map);

        MenuItem enviar = menu.add("Enviar Alunos");
        enviar.setIcon(R.drawable.ic_cloud_upload);

        MenuItem receber = menu.add("Receber Alunos");
        receber.setIcon(R.drawable.ic_cloud_download);

        MenuItem preferencias = menu.add("Preferências");
        preferencias.setIcon(R.drawable.ic_action_settings);
        */

        return super.onCreateOptionsMenu(menu);
    }

    // Sobrescreve o método de comportamento ao se clicar numa option.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_novo:
                Intent irParaFormulario = new Intent(this, FormularioActivity.class); // Intenção de ir para algum lugar: Intencao(onde_estou, para_onde_quero_ir);
                startActivity(irParaFormulario);
                break;

            case R.id.menu_mapa:
                Toast.makeText(ListaAlunosActivity.this, "Em construção...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_enviar_alunos:
                Toast.makeText(ListaAlunosActivity.this, "Em construção...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_receber_alunos:
                Toast.makeText(ListaAlunosActivity.this, "Em construção...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_preferencias:
                Toast.makeText(ListaAlunosActivity.this, "Em construção...", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
