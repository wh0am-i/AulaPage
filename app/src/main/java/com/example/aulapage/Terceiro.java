package com.example.aulapage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Terceiro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Terceiro extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int[] nudes = {R.drawable.ben10, R.drawable.finn, R.drawable.motomoto, R.drawable.titas, R.drawable.sally};
    ImageView nude;
    EditText entrada;
    TextView saida;
    Button confirmabtt, gerarbtt;
    int numero = 0;
    int tentativas = 5;
    Random gerador = new Random();

    public Terceiro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Terceiro.
     */
    // TODO: Rename and change types and number of parameters
    public static Terceiro newInstance(String param1, String param2) {
        Terceiro fragment = new Terceiro();
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
        View v = inflater.inflate(R.layout.fragment_terceiro, container, false);
        nude = v.findViewById(R.id.nude);
        entrada = v.findViewById(R.id.entrada);
        saida = v.findViewById(R.id.saida);
        confirmabtt = v.findViewById(R.id.confirmabtt);
        confirmabtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirma();
            }
        });
        gerarbtt = v.findViewById(R.id.gerarbtt);
        gerarbtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gerar();
            }
        });
        return v;
    }
    public void confirma() {
        if (!entrada.getText().toString().isEmpty()) {
            int digitado = Integer.parseInt(entrada.getText().toString());
            if (tentativas > 0) {
                if (numero != 0) {
                    tentativas -= 1;
                    if (digitado > numero) {
                        saida.setText("O número digitado é maior! \n" + "Você ainda tem " + tentativas + " tentativas!");
                    } else if (digitado < numero) {
                        saida.setText("O número digitado é menor! \n" + "Você ainda tem " + tentativas + " tentativas!");
                    } else if (digitado == numero) {
                        saida.setText("Bom garoto, toma esse nude de recompensa!");
                        nude.setVisibility(View.VISIBLE);
                        nude.setImageResource(nudes[gerador.nextInt(5)]);
                    }
                } else {
                    saida.setText("Vai gerar numero não? Vacilão!");
                }
            } else {
                saida.setText("Acabou suas tentativas, ruinzão hein!");
            }
        }
        else {
            saida.setText("Queria crashar o app né amigão? \n Não vai rolar!");
        }
    }

    public void gerar() {
        nude.setVisibility(View.INVISIBLE);
        tentativas = 5;
        numero = gerador.nextInt(20) + 1;//gera números de 0 à 99 -> o +1 faz não ter o 0 e ir até 100
    }
}