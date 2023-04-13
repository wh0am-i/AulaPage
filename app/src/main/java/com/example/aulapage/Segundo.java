package com.example.aulapage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Segundo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Segundo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button somarBtt, subBtt;
    TextView horaReal, minutoReal;
    EditText newHora, newHora2, newMinuto, newMinuto2;

    public Segundo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Segundo.
     */
    // TODO: Rename and change types and number of parameters
    public static Segundo newInstance(String param1, String param2) {
        Segundo fragment = new Segundo();
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
        View v = inflater.inflate(R.layout.fragment_segundo, container, false);

        horaReal = v.findViewById(R.id.horaReal);
        minutoReal = v.findViewById(R.id.minutoReal);
        newHora = v.findViewById(R.id.newHora);
        newHora2 = v.findViewById(R.id.newHora2);
        newMinuto = v.findViewById(R.id.newMinuto);
        newMinuto2 = v.findViewById(R.id.newMinuto2);
        somarBtt = v.findViewById(R.id.somarBtt);
        somarBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                somar();
            }
        });
        subBtt = v.findViewById(R.id.subBtt);
        subBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subtrair();
            }
        });
        return v;
    }

    public void somar() {
        int horaint = 0;
        int hora2int = 0;
        int minutoint = 0;
        int minuto2int = 0;

        if (!(newHora.getText().toString()).isEmpty()) {
            horaint = Integer.parseInt(newHora.getText().toString());
        }
        if (!(newHora2.getText().toString()).isEmpty()) {
            hora2int = Integer.parseInt(newHora2.getText().toString());
        }
        if (!(newMinuto.getText().toString()).isEmpty()) {
            minutoint = Integer.parseInt(newMinuto.getText().toString());
        }
        if (!(newMinuto2.getText().toString()).isEmpty()) {
            minuto2int = Integer.parseInt(newMinuto2.getText().toString());
        }
        //até aqui funciona
        int minutosom = minutoint + minuto2int;
        int horasom = horaint + hora2int;
        while (minutosom > 59) {
            minutosom -= 60;
            horasom += 1;
        }

        if (minutosom > 59) {
            horasom += 1;
            minutosom -= 60;
            minutoReal.setText("" + minutosom);
        }
        if (minutosom < 10) {
            minutoReal.setText("0" + minutosom);
        } else {
            minutoReal.setText("" + minutosom);
        }
        if (horasom < 10) {
            horaReal.setText("0" + horasom);
        } else {
            horaReal.setText("" + horasom);
        }
    }

    public void subtrair() {
        int horaint = 0;
        int hora2int = 0;
        int minutoint = 0;
        int minuto2int = 0;

        if (!(newHora.getText().toString()).isEmpty()) {
            horaint = Integer.parseInt(newHora.getText().toString());
        }
        if (!(newHora2.getText().toString()).isEmpty()) {
            hora2int = Integer.parseInt(newHora2.getText().toString());
        }
        if (!(newMinuto.getText().toString()).isEmpty()) {
            minutoint = Integer.parseInt(newMinuto.getText().toString());
        }
        if (!(newMinuto2.getText().toString()).isEmpty()) {
            minuto2int = Integer.parseInt(newMinuto2.getText().toString());
        }

        while (minutoint > 59) {
            minutoint -= 60;
            horaint += 1;
        }
        while (minuto2int > 59) {
            minuto2int -= 60;
            hora2int += 1;
        }
        if ((horaint > hora2int) || ((horaint == hora2int) && (minutoint >= minuto2int))) { //se as horas forem menor ou iguais e com minutos menor
            int minutosub = minutoint - minuto2int;
            int horasub = horaint - hora2int;
            calcs(minutosub, horasub);
        } else {
            int minutosub = minuto2int - minutoint;
            int horasub = hora2int - horaint;
            calcs(minutosub, horasub);
        }
    }

    public void calcs(int minutosub, int horasub) {
        if (minutosub < 0) {
            horasub -= 1;
            minutosub += 60;
            minutoReal.setText("" + minutosub);
        }
        if (minutosub < 10) {
            minutoReal.setText("0" + minutosub);
        } else {
            minutoReal.setText("" + minutosub);
        }
        if (horasub < 10) {
            horaReal.setText("0" + horasub);
        } else {
            horaReal.setText("" + horasub);
        }
    }
}