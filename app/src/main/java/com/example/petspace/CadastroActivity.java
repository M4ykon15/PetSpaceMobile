package com.example.petspace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

        if (ed_nome.equals("") || ed_email.equals("") || ed_senha.equals("") || ed_telefone.equals("") || ed_cpf.equals("")) {
            hideKeyBoard();
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else if (UsuarioDAO.validarEmailCad(ed_email) != null) {
            hideKeyBoard();
            Toast.makeText(this, "Este Email já está em uso", Toast.LENGTH_SHORT).show();
        } else if (validarEmail(ed_email) != 1) {
            hideKeyBoard();
            Toast.makeText(this, "Digite um email valido", Toast.LENGTH_SHORT).show();
        } else if (ed_senha.length() < 8) {
            hideKeyBoard();
            Toast.makeText(this, "Senha deve conter no minímo 8 digitos", Toast.LENGTH_SHORT).show();
        } else if (ed_telefone.length() < 15 ) {
            hideKeyBoard();
            Toast.makeText(this, "Digite um Telefone Valido", Toast.LENGTH_SHORT).show();
        } else if (ed_cpf.length() < 14 || !validateCPF(ed_cpf)) {
            hideKeyBoard();
            Toast.makeText(this, "Digite um Cpf valido", Toast.LENGTH_SHORT).show();
        } else {
            hideKeyBoard();
            int cadUser = UsuarioDAO.inserirUsuario(usuario);

            if(cadUser != 0){
                Toast.makeText(this, "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show();
            }
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

    private int validarEmail(String email) {
        //o 1 significa que o email é valido
        int resvalidate = 1;

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return resvalidate;
        } else {
            resvalidate = 0;
            return resvalidate;
        }
    }

    public static boolean validateCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(cpf.substring(i, i + 1)) * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) {
            primeiroDigito = 0;
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(cpf.substring(i, i + 1)) * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) {
            segundoDigito = 0;
        }

        // Verifica se os dígitos verificadores são iguais aos dígitos no CPF
        return Integer.parseInt(cpf.substring(9, 10)) == primeiroDigito
                && Integer.parseInt(cpf.substring(10)) == segundoDigito;
    }

}