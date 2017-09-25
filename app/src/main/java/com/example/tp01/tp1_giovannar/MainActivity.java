package com.example.tp01.tp1_giovannar;

/**
 * Tp1_LDDM
 * Objetivo: aplicativo que seja capaz de criar um evento no Calendário, adicionar os contatos na agenda, mandar whatsapp, publicar um post sobre o evento no FAcebok e Linkedin
 * Autora: Giovanna Ávila Riqueti
 * Matrícula: 553285
 * Data 25/09/2017
 * Versão 3
 */

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    EditText EditavelDataI, EditavelDataF, EditavelHoraI, EditavelHoraF , EditavelNome;
    String nome;
    Calendar instancia = Calendar.getInstance();
    int ano, mes, dia;
    int hora, minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditavelDataI = (EditText) findViewById(R.id.dataI);
        EditavelDataF = (EditText) findViewById(R.id.edit_dataF);
        EditavelHoraI = (EditText) findViewById(R.id.edit_horarioI);
        EditavelHoraF = (EditText) findViewById(R.id.edit_horarioF);



        EditavelDataI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizarData1();
            }
        });

        EditavelDataF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizarData2();
            }
        });

        EditavelHoraI.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                atualizarHora1();
            }
        });

        EditavelHoraF.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                atualizarHora2();
            }
        });

        //String dataInicial = Editavel.getText();
    }

    public void mandar (View view){
    try {
        EditavelNome = (EditText) findViewById(R.id.nome);

        Intent intent = new Intent(this, SegundaTela.class);

        Bundle params = new Bundle();
        params.putString("nomedoEvento", EditavelNome.getText().toString());
        params.putString("dataInicial", EditavelDataI.getText().toString());
        params.putString("horaInicial", EditavelHoraI.getText().toString());
        params.putString("dataFinal", EditavelDataF.getText().toString());
        params.putString("horaFinal", EditavelHoraF.getText().toString());


        intent.putExtras(params);
        startActivity(intent);


    }catch( Exception io){
        TextView excecao = (TextView) findViewById(R.id.registro);
        excecao.setText(io.getMessage());
    }

    }



    public void atualizarHora1 (){
        hora = instancia.get(Calendar.HOUR_OF_DAY);
        minuto = instancia.get(Calendar.MINUTE);
        new TimePickerDialog(this, a, hora, minuto, true).show();

    }

    public void atualizarHora2 (){
        hora = instancia.get(Calendar.HOUR_OF_DAY);
        minuto = instancia.get(Calendar.MINUTE);
        new TimePickerDialog(this, b, hora, minuto, true).show();

    }

    TimePickerDialog.OnTimeSetListener a = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            EditavelHoraI.setText(i + ":" + i1);
        }
    };

    TimePickerDialog.OnTimeSetListener b = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            EditavelHoraF.setText(i + ":" + i1);
        }
    };


    //////////////////////////////////////////////////////////////////////////

    public void atualizarData1 (){
        ano = instancia.get(Calendar.YEAR);
        mes = instancia.get(Calendar.MONTH);
        dia = instancia.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, g, ano, mes, dia).show();
    }

    public void atualizarData2 (){
        ano = instancia.get(Calendar.YEAR);
        mes = instancia.get(Calendar.MONTH);
        dia = instancia.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, f, ano, mes, dia).show();
    }

    DatePickerDialog.OnDateSetListener g = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectyear, int selectmes, int selectdia) {
            EditavelDataI.setText(selectdia + "/" + selectmes + "/" + selectyear);

        }
    };

    DatePickerDialog.OnDateSetListener f = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectyear, int selectmes, int selectdia) {
            EditavelDataF.setText(selectdia + "/" + selectmes + "/" + selectyear);

        }
    };
}
