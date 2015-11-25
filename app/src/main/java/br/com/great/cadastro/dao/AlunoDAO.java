package br.com.great.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.great.cadastro.modelo.Aluno;


public class AlunoDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "CadastroAlunos";
    private static final int VERSAO = 1;
    private static final String TABELA = "Alunos";

    public AlunoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + " ("
                + "id INTEGER PRIMARY KEY, "        // Coluna 0
                + "nome TEXT UNIQUE NOT NULL, "     // Coluna 1
                + "telefone TEXT, "                 // Coluna 2
                + "endereco TEXT, "                 // Coluna 3
                + "site TEXT, "                     // Coluna 4
                + "nota REAL, "                     // Coluna 5
                + "caminhoFoto TEXT"                // Coluna 6
                + ");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    private ContentValues toContentValues(Aluno aluno) {
        ContentValues cv = new ContentValues();
        //cv.put("id", aluno.getId());  // Isso quenga tudo! Pq? Pesquisar...
        cv.put("nome", aluno.getNome());
        cv.put("telefone", aluno.getTelefone());
        cv.put("endereco", aluno.getEndereco());
        cv.put("site", aluno.getSite());
        cv.put("nota", aluno.getNota());
        cv.put("caminhoFoto", aluno.getCaminhoFoto());
        return cv;
    }

    public void salvar(Aluno aluno) {
        ContentValues cv = new ContentValues();
        cv = toContentValues(aluno);

        getWritableDatabase().insert(TABELA, null, cv);
    }

    public List<Aluno> getLista() {
        List<Aluno> alunos = new ArrayList<Aluno>();

        String sql = "SELECT * FROM " + TABELA + ";";
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while(c.moveToNext()) {
            Aluno aluno = new Aluno();

            aluno.setId(c.getLong(c.getColumnIndex("id")));
            //aluno.setNome(c.getString(1));    // 1 é a posição do índice consultado, getString recebe int.
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            aluno.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));

            alunos.add(aluno);
        }

        return alunos;
    }

    public void deletar(Aluno aluno) {
        String[] args = {String.valueOf(aluno.getId())};
        getWritableDatabase().delete(TABELA, "id=?", args);
    }

    public void atualizar(Aluno aluno) {
        ContentValues cv = new ContentValues();

        cv.put("nome", aluno.getNome());
        cv.put("telefone", aluno.getTelefone());
        cv.put("endereco", aluno.getEndereco());
        cv.put("site", aluno.getSite());
        cv.put("nota", aluno.getNota());
        cv.put("caminhoFoto", aluno.getCaminhoFoto());

        String[] args = {String.valueOf(aluno.getId())};
        getWritableDatabase().update("Alunos", cv, "id=?", args);
    }
}
