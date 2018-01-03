package tradinggames.com.tradinggames.activities;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tradinggames.com.tradinggames.R;
import tradinggames.com.tradinggames.util.Validator;

public class ForgotPassword extends AppCompatActivity {

    private Button enviarEmail;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        enviarEmail = findViewById(R.id.buttonEnviarEmail);
        email = findViewById(R.id.editTextEmail);

        enviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ForgotPassword.this, "Esta função ainda não foi implementada, desulpe-nos! Em breve teremos novidades!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
