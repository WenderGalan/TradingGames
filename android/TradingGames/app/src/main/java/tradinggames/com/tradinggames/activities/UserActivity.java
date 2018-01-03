package tradinggames.com.tradinggames.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tradinggames.com.tradinggames.R;
import tradinggames.com.tradinggames.models.User;

public class UserActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText nome;
    private EditText sobrenome;
    private EditText email;
    private EditText senha;
    private Button alterarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Minha conta");
        setSupportActionBar(toolbar);

        nome = findViewById(R.id.edit_nome);
        sobrenome = findViewById(R.id.edit_ultimo_nome);
        email = findViewById(R.id.edit_email);
        senha = findViewById(R.id.edit_senha);
        alterarUsuario = findViewById(R.id.button);

        alterarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUserName(nome.getText().toString());
                user.setUserSecondName(sobrenome.getText().toString());
                user.setUserEmail(email.getText().toString());
                user.setUserPassword(senha.getText().toString());

                /*
                 * Aqui seria uma task para fazer a alteração de usuario mas provavelmente não haverá
                 * pois esta função não está implementada na primeira release
                 */
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.item_sair:
                intent = new Intent(UserActivity.this, Login.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.item_anuncios:
                intent = new Intent(UserActivity.this, MainAnuncios.class);
                startActivity(intent);
                return true;

            case R.id.item_configuracoes:
                return true;

            case R.id.item_meus_anuncios:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
