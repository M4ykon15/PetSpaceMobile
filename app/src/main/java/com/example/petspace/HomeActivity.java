package com.example.petspace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.petspace.Adapter.AdapteRecyclerViewAnimais;
import com.example.petspace.DAO.AnimaisDAO;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapteRecyclerViewAnimais adapter, adapteFilter;
    private EditText mEditTextSeach;
    private ImageView mImageViewArrowRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mEditTextSeach = findViewById(R.id.edittext_search);
        mImageViewArrowRight = findViewById(R.id.ImageViewArrowRight);

        recyclerView = findViewById(R.id.Rv_home);
        adapter = new AdapteRecyclerViewAnimais(AnimaisDAO.pesquisarAnimais());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        mImageViewArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });

        mEditTextSeach.addTextChangedListener(new TextWatcher() {
            private static final long DELAY = 0;
            private long lastInputTime = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapteFilter = new AdapteRecyclerViewAnimais(AnimaisDAO.pesquisarAnimal(mEditTextSeach.getText().toString()));
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastInputTime > DELAY) {
                    recyclerView.setAdapter(adapteFilter);
                }
                lastInputTime = currentTime;
            }
        });
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_overflow, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_phone:
                        String phoneNumber = "(11) 95470-5679"; // Substitua com o número de telefone desejado
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));

                        try {
                            startActivity(intent);
                        } catch (SecurityException e) {
                            Toast.makeText(HomeActivity.this, "Erro ao discar para o número de telefone.", Toast.LENGTH_SHORT).show();
                        }

                        return true;



                    case R.id.action_logout:
                        Toast.makeText(HomeActivity.this, "Logout selecionado", Toast.LENGTH_SHORT).show();
                        openLoginActivity();
                        return true;

                        case R.id.action_profile:
                            Toast.makeText(HomeActivity.this, "Perfil selecionado", Toast.LENGTH_SHORT).show();
                            openPerfilActivity();
                        return true;

                    case R.id.action_about_us:
                        startActivity(new Intent(HomeActivity.this, About.class));
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }

    private void openPerfilActivity() {
        Intent intent = new Intent(HomeActivity.this, PerfilActivity.class);
        intent.putExtra("emailUser", getIntent().getExtras().getString("emailUser"));
        startActivity(intent);

    }
    private void openLoginActivity() {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.putExtra("emailUser", getIntent().getExtras().getString("emailUser"));
        startActivity(intent);

    }





}