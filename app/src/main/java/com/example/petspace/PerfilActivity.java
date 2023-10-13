package com.example.petspace;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petspace.DAO.UsuarioDAO;
import com.example.petspace.Model.Usuario;
import com.santalu.maskara.widget.MaskEditText;

import java.util.ArrayList;
import java.util.List;

public class PerfilActivity extends AppCompatActivity {

    EditText mEditTextNome, mEditTextEmail, mEditTextSenha;
    MaskEditText mEditTexTelefone, mEditTextCpf;
    TextView mTextViewNome;
    ImageView mImageViewDelete;
    Button mButtonSave, mButtonEditInfo;
    private ImageView mIconback;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mTextViewNome = findViewById(R.id.TextView_Nome);
        mEditTextNome = findViewById(R.id.editTextNome);
        mEditTextEmail = findViewById(R.id.editTextEmail);
        mEditTextSenha = findViewById(R.id.editTextSenha);
        mEditTexTelefone = findViewById(R.id.editTextTelefone);
        mEditTextCpf = findViewById(R.id.editTextCPF);
        mButtonEditInfo = findViewById(R.id.buttonInfo);
        mButtonSave = findViewById(R.id.buttonSave);
        mImageViewDelete = findViewById(R.id.buttonDeleteIcon);



        mIconback = findViewById(R.id.IconBack);
        mIconback.setOnClickListener(view -> {
           onBackPressed();
        });



        mButtonEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEditTextNome.getVisibility() != View.VISIBLE){
                    hideOrApperInfo(1);
                } else {
                    hideOrApperInfo(0);
                }
            }
        });

        mImageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog();
            }
        });

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUsuario();
                putInfo();
            }
        });

        putInfo();

    }

    public void putInfo(){

        String email = getIntent().getExtras().getString("emailUser");

        List<Usuario> usuarios = UsuarioDAO.getUsuario(UsuarioDAO.getIdByEmail(email));

        for (Usuario usuario : usuarios) {
            mTextViewNome.setText(usuario.getNome());
            mEditTextNome.setText(usuario.getNome());
            mEditTextEmail.setText(usuario.getEmail());
            mEditTextSenha.setText(usuario.getSenha());
            mEditTexTelefone.setText(usuario.getTelefone());
            mEditTextCpf.setText(usuario.getCpf());
        }
    }

    public void hideOrApperInfo(int status){
        if(status != 1) {
            mEditTextNome.setVisibility(View.INVISIBLE);
            mEditTextEmail.setVisibility(View.INVISIBLE);
            mEditTextSenha.setVisibility(View.INVISIBLE);
            mEditTexTelefone.setVisibility(View.INVISIBLE);
            mEditTextCpf.setVisibility(View.INVISIBLE);
            mImageViewDelete.setVisibility(View.INVISIBLE);
            mButtonSave.setVisibility(View.INVISIBLE);
        } else {
            mEditTextNome.setVisibility(View.VISIBLE);
            mEditTextEmail.setVisibility(View.VISIBLE);
            mEditTextSenha.setVisibility(View.VISIBLE);
            mEditTexTelefone.setVisibility(View.VISIBLE);
            mEditTextCpf.setVisibility(View.VISIBLE);
            mImageViewDelete.setVisibility(View.VISIBLE);
            mButtonSave.setVisibility(View.VISIBLE);
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

    public void updateUsuario(){

        String email = getIntent().getExtras().getString("emailUser");
        int idUser = UsuarioDAO.getIdByEmail(email);

        String ed_nome = mEditTextNome.getText().toString();
        String ed_email = mEditTextEmail.getText().toString();
        String ed_senha = mEditTextSenha.getText().toString();
        String ed_Telefone = mEditTexTelefone.getText().toString();
        String ed_Cpf = mEditTextCpf.getText().toString();

        if(ed_nome.equals("") || ed_email.equals("") || ed_senha.equals("")) {
            hideKeyBoard();
            Toast.makeText(this, "Preencha todos os dados", Toast.LENGTH_SHORT).show();
        } else if (UsuarioDAO.validarEmailUpate(ed_email, idUser) != null) {
            hideKeyBoard();
            Toast.makeText(this, "Email já está em uso", Toast.LENGTH_SHORT).show();
        } else if (validarEmail(ed_email) != 1) {
            hideKeyBoard();
            Toast.makeText(this, "Digite um email valido", Toast.LENGTH_SHORT).show();
        } else if (ed_senha.length() < 8) {
            hideKeyBoard();
            Toast.makeText(this, "Senha deve conter no minimo 8 caracteres", Toast.LENGTH_SHORT).show();
        } else if (ed_Telefone.length() < 14) {
            hideKeyBoard();
            Toast.makeText(this, "Digite um telefone valido", Toast.LENGTH_SHORT).show();
        } else if (UsuarioDAO.validarTelefoneUpdate(ed_Telefone, idUser) != null) {
            hideKeyBoard();
            Toast.makeText(this, "Este telefone já esta em uso", Toast.LENGTH_SHORT).show();
        } else if (ed_Cpf.length() < 14 || !validateCPF(ed_Cpf) ) {
            hideKeyBoard();
            Toast.makeText(this, "Digite um Cpf valido", Toast.LENGTH_SHORT).show();
        } else {
            hideKeyBoard();
            int Update = UsuarioDAO.atualizarUsuarios(idUser, ed_nome, ed_email, ed_senha, ed_Telefone, ed_Cpf );

            if (Update != 0){
                Toast.makeText(this, "Dados atualizados com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showConfirmationDialog() {

        String email = getIntent().getExtras().getString("emailUser");
        int idUser = UsuarioDAO.getIdByEmail(email);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflar o layout personalizado para a caixa de diálogo
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.diag_delete, null);

        builder.setView(dialogView)
                .setTitle("Confirmação")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UsuarioDAO.deletarUser(idUser);
                        Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
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