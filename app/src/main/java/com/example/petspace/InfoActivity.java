package com.example.petspace;

import androidx.appcompat.app.AppCompatActivity;

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
    ImageView mImageViewFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        mTextViewNome = findViewById(R.id.textViewNamePet);
        mTextViewSexo = findViewById(R.id.textViewSexPet);
        mTextViewEspecie = findViewById(R.id.textViewSpeciesPet);
        mTextViewRaca = findViewById(R.id.textViewRacaPet);
        mTextViewIdade = findViewById(R.id.textViewIdadePet);
        mTextViewPorte = findViewById(R.id.textViewPortePet);
        mImageViewFoto  = findViewById(R.id.imageViewPet);

        setDadosAnimal();

    }

    public void setDadosAnimal(){
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
        } else {
            Toast.makeText(this, "erro", Toast.LENGTH_LONG).show();
        }
    }

    public static Bitmap converterByteToBipmap(byte[] foto) {
        Bitmap bmp = null;
        Bitmap bitmapReduzido = null;
        byte[] x = foto;

        try {
            bmp = BitmapFactory.decodeByteArray(x, 0, x.length);

            bitmapReduzido = Bitmap.createScaledBitmap(bmp, 220, 220, true);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmapReduzido;
    }

}