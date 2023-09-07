package com.example.petspace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
        if (ed_email.equals("") || ed_senha.equals("")) {
            hideKeyBoard();
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else if (usu != null){
            hideKeyBoard();
            Toast.makeText(LoginActivity.this, "Login realizado com Sucesso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra("emailUser" , ed_email);
            startActivity(intent);
            finish();
        } else {
            hideKeyBoard();
            Toast.makeText(LoginActivity.this, "Email e/ou Senha invalidos", Toast.LENGTH_SHORT).show();
        }
    }

    public void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) { // Verifica se o teclado está ativo/aberto
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

}