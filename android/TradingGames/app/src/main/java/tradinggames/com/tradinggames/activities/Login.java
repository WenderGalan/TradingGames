package tradinggames.com.tradinggames.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import tradinggames.com.tradinggames.R;
import tradinggames.com.tradinggames.models.User;
import tradinggames.com.tradinggames.tasks.LoginTask;
import tradinggames.com.tradinggames.util.Validator;

public class Login extends AppCompatActivity {

    private Button entrar;
    private Button criarConta;
    private Button esqueceuSenha;
    private EditText email;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //RECUPERA OS IDS DO WIDGETS
        email = (EditText) findViewById(R.id.editTextEmail);
        senha = (EditText) findViewById(R.id.editTextSenha);
        entrar = (Button) findViewById(R.id.buttonEntrar);
        criarConta = (Button) findViewById(R.id.buttonCriarConta);
        esqueceuSenha = (Button) findViewById(R.id.buttonEsqueceuSenha);

        //BOTÕES
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //IMPLEMENTAÇÃO DO BOTÃO ENTRAR

                //VERIFICA SE OS CAMPOS NAO ESTAO VAZIOS
                Validator validator = new Validator();
                boolean email_valido = validator.validateNotNull(email, "Preencha o campo email");
                if (!email_valido) {
                    email.setError("Email inválido");
                    email.setFocusable(true);
                    email.requestFocus();
                    return;
                }

                boolean senha_valido = validator.validateNotNull(senha, "Preencha o campo nome");
                if (!senha_valido) {
                    senha.setError("Senha inválida");
                    senha.setFocusable(true);
                    senha.requestFocus();
                    return;
                }



                try {
                    AsyncTask asyncTask = new LoginTask(getApplicationContext()).execute(email.getText().toString(), senha.getText().toString());
                    User user = (User) asyncTask.get();
                    Intent intent = new Intent(Login.this, MainAnuncios.class);
                    intent.putExtra("userId", user.getUserId());
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }
        });


        criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Chama a classe Create Account para inicializar a nova activity
                Intent intent = new Intent(Login.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        esqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Chama a activity Esqueceu a senha
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);

            }
        });
    }

}
