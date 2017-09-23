package com.example.tp01.tp1_giovannar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by giovannariqueti on 21/09/17.
 */

public class SegundaTela extends AppCompatActivity {

    Calendar instancia = Calendar.getInstance();
    Calendar instancia2 = Calendar.getInstance();
    EditText EditavelOrg, EditavelEmail, Editavelconv1, Editaveltel1 , Editavelconv2, Editaveltel2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segunda);


        Intent intent = getIntent();
        if (intent != null) {
            Bundle params = intent.getExtras();
            String[] dataInicial = params.getString("dataInicial").split("/");
            int ano = Integer.parseInt(dataInicial[0]);
            int mes = Integer.parseInt(dataInicial[1]);
            int dia = Integer.parseInt(dataInicial[2]);

            String[] Inicial = params.getString("horaInicial").split(":");
            int hora = Integer.parseInt(Inicial[0]);
            int minuto = Integer.parseInt(Inicial[1]);

            instancia.set(ano, mes, dia, hora, minuto);
            long startmillisI = instancia.getTimeInMillis();

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            String[] dataFinal = params.getString("dataFinal").split("/");
            ano = Integer.parseInt(dataFinal[0]);
            mes = Integer.parseInt(dataFinal[1]);
            dia = Integer.parseInt(dataFinal[2]);

            String[] Finalll = params.getString("horaFinal").split(":");
            hora = Integer.parseInt(Finalll[0]);
            minuto = Integer.parseInt(Finalll[1]);

            instancia2.set(ano, mes, dia, hora, minuto);
            long startmillisF = instancia2.getTimeInMillis();

            Intent icalen = new Intent(Intent.ACTION_INSERT_OR_EDIT);
            icalen.setType("vnd.android.cursor.item/event");


            icalen.putExtra(CalendarContract.Events.TITLE, params.getString("nomedoEvento"));
            icalen.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startmillisI);
            icalen.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, startmillisF);
            icalen.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");

            startActivity(icalen);

        }else{
            TextView excecao = (TextView) findViewById(R.id.registro);
            excecao.setText("Não deu");
        }

    }

    public void enviar (View view){

        EditavelOrg = (EditText) findViewById(R.id.organizador);
        EditavelEmail = (EditText) findViewById(R.id.email);
        Editavelconv1 = (EditText) findViewById(R.id.contato1);
        Editaveltel1 = (EditText) findViewById(R.id.telefone1);
        Editavelconv2 = (EditText) findViewById(R.id.contato2);
        Editaveltel2 = (EditText) findViewById(R.id.telefone2);

        Intent terceira = new Intent(ContactsContract.Intents.Insert.ACTION);
        terceira.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        // mandando o nome
        terceira.putExtra(ContactsContract.Intents.Insert.NAME, EditavelOrg.getText().toString());

        // mandando email
        //terceira.putExtra(ContactsContract.Intents.Insert.EMAIL, EditavelEmail.getText().toString());
        //terceira.putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME);

        // mandando o telefone
        terceira.putExtra(ContactsContract.Intents.Insert.PHONE, Editaveltel1.getText().toString());
        terceira.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME);

        // mandando o endereco
        //intent.putExtra(ContactsContract.Intents.Insert.POSTAL, end);
        //intent.putExtra(ContactsContract.Intents.Insert.POSTAL_TYPE, ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME);

        // iniciando a intent
        startActivity(terceira);



    }

    public void whatsapp (View view){
        Intent nova = new Intent(this, Terceira.class);
        startActivity(nova);
    }
}


