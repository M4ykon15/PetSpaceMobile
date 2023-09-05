package com.example.petspace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.petspace.DAO.UsuarioDAO;
import com.example.petspace.Model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    AppCompatEditText mEditTextNome, mEditTextEmail, mEditTextSenha, mEditTextTelefone, mEditTextCpf;
    AppCompatTextView mTextViewLogin;
    AppCompatButton mButtonCad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mTextViewLogin = findViewById(R.id.textView_already);
        mEditTextNome = findViewById(R.id.editText_full_name);
        mEditTextEmail = findViewById(R.id.editText_email);
        mEditTextSenha = findViewById(R.id.editText_password_sign_up);
        mEditTextTelefone = findViewById(R.id.edittext_telefone);
        mEditTextCpf = findViewById(R.id.editText_cpf_sing_up);
        mButtonCad = findViewById(R.id.button_sign_up);

        mButtonCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarUsusario();
            }
        });

        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void cadastrarUsusario(){

        String ed_nome = mEditTextNome.getText().toString();
        String ed_email = mEditTextEmail.getText().toString();
        String ed_senha = mEditTextSenha.getText().toString();
        String ed_telefone = mEditTextTelefone.getText().toString();
        String ed_cpf = mEditTextCpf.getText().toString();

        Usuario usuario = new Usuario(
            ed_nome,
            ed_email,
            ed_senha,
            ed_telefone,
            ed_cpf
        );

        int ok = UsuarioDAO.inserirUsuario(usuario);

        if(ok != 0){
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "fodeu", Toast.LENGTH_SHORT).show();
        }

    }

}