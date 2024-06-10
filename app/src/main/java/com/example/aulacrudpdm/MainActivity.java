package com.example.aulacrudpdm;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.aulacrudpdm.dao.ClienteDAO;
import com.example.aulacrudpdm.model.ClienteVO;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_cadastrar), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void btnOnClickCadastrarCliente(View view){

        /*
        ClienteDAO db = new ClienteDAO(this);
        Log.d("Insert","Inserindo clientes...");
        db.addClient(new ClienteVO("Bruno"));
        db.addClient(new ClienteVO("Marcos"));
        db.addClient(new ClienteVO("Miranda"));


        EditText nomeEditText = (EditText) findViewById(R.id.nome);
        EditText emailEditText = (EditText) findViewById(R.id.email);



        ClienteVO vo = new ClienteVO();
        vo.setNome(nomeEditText.getText().toString());
        vo.setEmail(emailEditText.getText().toString());
        db.addClient(vo);


        Log.d("Select","Lendo os clientes...");
        List<ClienteVO> clientes = db.getAllClientes();


        for(ClienteVO cliente: clientes){
            String log = "Id: " + cliente.getId() + ", Nome: " + cliente.getNome();
            Log.d("Nome >>>",log);
        }

        */

        EditText nomeEditText = (EditText) findViewById(R.id.nome);
        String nome = nomeEditText.getText().toString();
        EditText emailEditText = (EditText) findViewById(R.id.email);
        String email = emailEditText.getText().toString();

        ClienteVO clienteVO = new ClienteVO();

        Log.d("Insert","Inserindo clientes...");

        ClienteDAO clienteDAO = new ClienteDAO(this);
        clienteDAO.addClient(clienteVO);

        Log.d("Insert","Registros inseridos");
    }

    public void btnOnClickViewCadastrar(View view){
        setContentView(R.layout.cadastrar_cliente);
    }
    public void btnOnClickViewMenu(View view){
        setContentView(R.layout.activity_main);
    }
    public void btnOnClickViewListar(View view){
        setContentView(R.layout.listar_clientes);
    }
    public void btnOnClickViewEditar(View view){
        setContentView(R.layout.atualizar_cliente);
    }
    public void btnOnClickViewDeletar(View view){
        setContentView(R.layout.deletar_cliente);
    }
}
