package com.example.petspace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.petspace.Adapter.AdapteRecyclerViewAnimais;
import com.example.petspace.DAO.AnimaisDAO;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapteRecyclerViewAnimais adapter, adapteFilter;
    private EditText mEditTextSeach;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mEditTextSeach = findViewById(R.id.edittext_search);

        recyclerView = findViewById(R.id.Rv_home);
        adapter = new AdapteRecyclerViewAnimais(AnimaisDAO.pesquisarAnimais());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

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
}