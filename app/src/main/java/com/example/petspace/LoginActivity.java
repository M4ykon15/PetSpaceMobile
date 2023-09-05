package com.example.petspace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petspace.DAO.UsuarioDAO;
import com.example.petspace.Model.Usuario;

public class LoginActivity extends AppCompatActivity {

    AppCompatEditText mEditTextEmail, mEditTextSenha;
    AppCompatTextView mTextViewCad;
    Button mButtonLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditTextEmail = findViewById(R.id.editText_credentials);
        mEditTextSenha = findViewById(R.id.editText_password);
        mTextViewCad = findViewById(R.id.textView_forgot_password);
        mButtonLogin = findViewById(R.id.button_sign_in);


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logar();
            }
        });
        mTextViewCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Logar(){
        String ed_email = mEditTextEmail.getText().toString();
        String ed_senha =  mEditTextSenha.getText().toString();

        Usuario usu = UsuarioDAO.verificaLogin(ed_email, ed_senha);

        // 1  retorna que o usuario existe
        if(usu != null){
            Toast.makeText(LoginActivity.this, "Login realizado com Sucesso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "Email e/ou Senha invalidos", Toast.LENGTH_SHORT).show();
        }
    }

}