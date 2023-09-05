package com.example.petspace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

    }
}