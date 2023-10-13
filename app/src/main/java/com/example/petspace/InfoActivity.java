package com.example.petspace;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petspace.DAO.AnimaisDAO;
import com.example.petspace.Model.Animais;

import java.util.List;

public class InfoActivity extends AppCompatActivity {

    TextView mTextViewNome, mTextViewSexo, mTextViewEspecie, mTextViewRaca, mTextViewIdade, mTextViewPorte;
    ImageView mImageViewPet;

    private ImageView mIconback;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        mIconback = findViewById(R.id.IconBack);

        mIconback.setOnClickListener(view -> {
            startActivity(new Intent(this, HomeActivity.class));
        });

        mTextViewNome = findViewById(R.id.textViewNamePet);
        mTextViewSexo = findViewById(R.id.textViewSexPet);
        mTextViewEspecie = findViewById(R.id.textViewSpeciesPet);
        mTextViewRaca = findViewById(R.id.textViewRacaPet);
        mTextViewIdade = findViewById(R.id.textViewIdadePet);
        mTextViewPorte = findViewById(R.id.textViewPortePet);
        mImageViewPet = findViewById(R.id.imageViewPet);

        setDadosAnimal();
    }

    public void setDadosAnimal() {
        String idAnimal = getIntent().getExtras().getString("id");
        List<Animais> listaAnimal = AnimaisDAO.infoAnimais(idAnimal);

        if (!listaAnimal.isEmpty()) {
            Animais animal = listaAnimal.get(0);
            mTextViewRaca.setText(animal.getRaca());
            mTextViewNome.setText(animal.getNome());
            mTextViewPorte.setText(animal.getPorte());
            mTextViewIdade.setText(animal.getIdade());
            mTextViewSexo.setText(animal.getSexo());
            mTextViewEspecie.setText(animal.getEspecie());

            // Carregar e exibir a foto do animal
            byte[] fotoBytes = animal.getFoto(); // Suponha que a foto do animal esteja em um campo chamado "foto"
            if (fotoBytes != null && fotoBytes.length > 0) {
                Bitmap fotoBitmap = converterByteToBipmap(fotoBytes);
                mImageViewPet.setImageBitmap(fotoBitmap);
            }
        } else {
            Toast.makeText(this, "Animal não encontrado", Toast.LENGTH_LONG).show();
        }
    }

    public static Bitmap converterByteToBipmap(byte[] foto) {
        Bitmap bmp = BitmapFactory.decodeByteArray(foto, 0, foto.length);
        return Bitmap.createScaledBitmap(bmp, 300, 260, true);
    }
}
