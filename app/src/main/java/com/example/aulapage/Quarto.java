package com.example.aulapage;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Quarto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Quarto extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView foto;
    Button fotobtt, escolhebtt;

    public Quarto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Quarto.
     */
    // TODO: Rename and change types and number of parameters
    public static Quarto newInstance(String param1, String param2) {
        Quarto fragment = new Quarto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quarto, container, false);
        foto = v.findViewById(R.id.iv);
        fotobtt = v.findViewById(R.id.fotobtt);
        escolhebtt = v.findViewById(R.id.escolhebtt);
        fotobtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirarFoto();
            }
        });
        escolhebtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escolheFoto();
            }
        });
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) { //checa as próprias permissões, se a permissão de camera for diferente de garantida; o android.Manifest... é o camera, não o
            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 0); //pede a permissão com e envia o código 0
        }
        return v;
    }
    public void escolheFoto(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        abrirGaleria.launch(i);
    }

    public void tirarFoto() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(i, 1); não é mais usado por segurança
        abrirCamera.launch(i);
    }

    ActivityResultLauncher<Intent> abrirCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            Bundle dado = data.getExtras();
            Bitmap imagem = (Bitmap) dado.get("data");
            foto.setImageBitmap(imagem);
            Toast.makeText(getContext(), "Tá gostosa, hein!", Toast.LENGTH_SHORT).show();
        }
    }); //arrow function wtffff?
    ActivityResultLauncher<Intent> abrirGaleria = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            Uri imagemSelecionada = data.getData();
            String[] localGaleria = {MediaStore.Images.Media.DATA};
            Cursor c = getContext().getContentResolver().query(imagemSelecionada, localGaleria, null, null, null); //query é o comando de select from * x
            c.moveToFirst();
            int coluna = c.getColumnIndex(localGaleria[0]);
            String caminhoFisico = c.getString(coluna);
            c.close();
            Bitmap imagem = BitmapFactory.decodeFile(caminhoFisico);
            foto.setImageBitmap(imagem);
            Toast.makeText(getContext(), "Tá gostosa, hein!", Toast.LENGTH_SHORT).show();
        }
    }); //arrow function wtffff?
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //@Nullable pois pode receber nulo, caso abra a câmera e não tire foto
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle dado = data.getExtras();
            Bitmap imagem = (Bitmap) dado.get("data");
            foto.setImageBitmap(imagem);
            Toast.makeText(this, "Tá gostosa, hein!", Toast.LENGTH_SHORT).show();
        }
    }*/

}