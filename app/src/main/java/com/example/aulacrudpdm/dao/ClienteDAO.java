package com.example.aulacrudpdm.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.aulacrudpdm.model.ClienteVO;


import java.util.ArrayList;
import java.util.List;


public class ClienteDAO extends SQLiteOpenHelper {




    private static final String DATABASE_NAME = "LOJA_DB";
    private static final int DATABASE_VERSION = 1;
    private static final String TB_CLIENTES = "tb_clientes";
    private static final String KEY_ID = "id";
    private static final String NOME = "nome";
    private static final String EMAIL = "email";








    public ClienteDAO(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }








    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TB_CLIENTES = "CREATE TABLE "+ TB_CLIENTES + "(" + KEY_ID + "INTEGER PRIMARY KEY," + NOME + " TEXT," + EMAIL + " TEXT)";
        db.execSQL(CREATE_TB_CLIENTES);
    }








    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_CLIENTES);
        onCreate(db);
    }








    public void addClient(ClienteVO clientVO){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NOME, clientVO.getNome());
        contentValues.put(EMAIL, clientVO.getEmail());

        db.insert(TB_CLIENTES, null, contentValues);
        db.close();
    }

    public int getCountClientes(){
        int count = 0;

        String countQuerySQL = "SELECT * FROM " + TB_CLIENTES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuerySQL, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }








    public List<ClienteVO> getAllClientes(){
        List<ClienteVO> ltClientes = new ArrayList<ClienteVO>();
        String SELECT_QUERY = "SELECT * FROM "+TB_CLIENTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY, null);
        if(cursor.moveToFirst()){
            do {
                ClienteVO clienteVO = new ClienteVO();
                clienteVO.setId(Integer.parseInt(cursor.getString(0)));
                clienteVO.setNome(cursor.getString(1));
                clienteVO.setEmail(cursor.getString(2));

                ltClientes.add(clienteVO);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return ltClientes;
    }

    public ClienteVO getCliente(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        ClienteVO clienteVO = new ClienteVO();

        Cursor cursor = db.query(TB_CLIENTES,
                new String[]{KEY_ID,NOME,EMAIL},
                KEY_ID + " = ? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);

        if(cursor != null){
            cursor.moveToFirst();

            clienteVO.setId(Integer.parseInt(cursor.getString(0)));
            clienteVO.setNome(cursor.getString(1));
            clienteVO.setEmail(cursor.getString(2));
        }

        cursor.close();
        db.close();
        return clienteVO;
    }

    public int updateCliente(ClienteVO clienteVO){
        int qtdRegistrosAtualizados = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL, clienteVO.getEmail());

        qtdRegistrosAtualizados = db.update(TB_CLIENTES, contentValues,KEY_ID + " = ? ", new String[]{String.valueOf(clienteVO.getId())});

        db.close();
        return qtdRegistrosAtualizados;
    }

    public void deleteCliente(ClienteVO clienteVO){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TB_CLIENTES,KEY_ID + " = ? ", new String[]{String.valueOf(clienteVO.getId())});

        db.close();
    }
}
