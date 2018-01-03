package tradinggames.com.tradinggames.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tradinggames.com.tradinggames.R;
import tradinggames.com.tradinggames.util.Validator;


public class CreateAccount extends AppCompatActivity {

    private EditText nome;
    private EditText sobrenome;
    private EditText email;
    private EditText senha;
    private EditText repetirSenha;
    private Button criarContaCadastre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        criarContaCadastre = (Button) findViewById(R.id.buttonCriarContaCadastre);
        nome = (EditText) findViewById(R.id.editTextNome);
        sobrenome = (EditText) findViewById(R.id.editTextSobrenome);
        email = (EditText) findViewById(R.id.editTextEmailCadastre);
        senha = (EditText) findViewById(R.id.editTextSenhaCadastre);
        repetirSenha = (EditText) findViewById(R.id.editTextRepetirSenhaCadastre);

        //Botao criar conta
        criarContaCadastre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validator validator = new Validator();
                //Verifica se os campos nao estao vazios
                //NOME
                boolean nome_valido = validator.validateNotNull(nome, "Preencha o campo nome");
                if(!nome_valido){
                    nome.setError("Nome inválido");
                    nome.setFocusable(true);
                    nome.requestFocus();
                }

                //SOBRENOME
                boolean sobrenome_valido = validator.validateNotNull(sobrenome, "Preencha o campo sobrenome");
                if(!sobrenome_valido){
                    sobrenome.setError("Sobrenome inválido");
                    sobrenome.setFocusable(true);
                    sobrenome.requestFocus();
                }

                //EMAIL
                boolean email_valido = validator.validateNotNull(email, "Preencha o campo email");
                if(!email_valido){
                    email.setError("Email inválido");
                    email.setFocusable(true);
                    email.requestFocus();
                }



                //SENHA
                boolean senha_valido = validator.validateNotNull(senha, "Preencha o campo senha");
                if(!senha_valido){
                    senha.setError("Senha inválida");
                    senha.setFocusable(true);
                    senha.requestFocus();
                }

                //REPETIR SENHA
                boolean repetirSenha_valido = validator.validateNotNull(repetirSenha, "Preencha o campo repetir senha");
                if(!repetirSenha_valido){
                    repetirSenha.setError("Senha inválida");
                    repetirSenha.setFocusable(true);
                    repetirSenha.requestFocus();
                }

                //Verifica se as senhas estao iguais
                if (senha.getText().toString().equals(repetirSenha.getText().toString())){
                    //Verifica se as duas senhas nao sao vazias
                    if (senha.getText().toString().isEmpty() || repetirSenha.getText().toString().isEmpty()){
                        Toast.makeText(CreateAccount.this, "Senhas vazias não são aceitas!", Toast.LENGTH_LONG).show();
                    }else{
                        //AQUI CHAMA O METODO QUE VAI CADASTRAR O USUARIO
                        Toast.makeText(CreateAccount.this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                        finish();
                    }

                }else {
                    repetirSenha.setError("Senha diferente!");
                    repetirSenha.setFocusable(true);
                    repetirSenha.requestFocus();
                }
            }
        });
    }
}