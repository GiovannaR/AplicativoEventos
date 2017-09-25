package com.example.tp01.tp1_giovannar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by giovannariqueti on 21/09/17.
 */

public class SegundaTela extends AppCompatActivity {

    ArrayList<String> arrayList;

    Calendar instancia = Calendar.getInstance();
    Calendar instancia2 = Calendar.getInstance();
    EditText EditavelOrg, EditavelEmail, Editavelconv1, Editaveltel1 , Editavelconv2, Editaveltel2;
    int ano, mes, dia, hora, minuto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segunda);

        arrayList = new ArrayList<>();


        Editavelconv1 = (EditText) findViewById(R.id.contato1);
        Editaveltel1 = (EditText) findViewById(R.id.telefone1);



        Intent intent = getIntent();
        if (intent != null) {
            Bundle params = intent.getExtras();
            String[] dataInicial = params.getString("dataInicial").split("/");
            ano = Integer.parseInt(dataInicial[0]);
            mes = Integer.parseInt(dataInicial[1]);
            dia = Integer.parseInt(dataInicial[2]);

            String[] Inicial = params.getString("horaInicial").split(":");
            hora = Integer.parseInt(Inicial[0]);
            minuto = Integer.parseInt(Inicial[1]);

            instancia.set(ano, mes, dia, hora, minuto);
            long startmillisI = instancia.getTimeInMillis();

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            String[] dataFinal = params.getString("dataFinal").split("/");
            int anoF = Integer.parseInt(dataFinal[0]);
            int mesF = Integer.parseInt(dataFinal[1]);
            int diaF = Integer.parseInt(dataFinal[2]);

            String[] Finalll = params.getString("horaFinal").split(":");
            int horaF = Integer.parseInt(Finalll[0]);
            int minutoF = Integer.parseInt(Finalll[1]);

            instancia2.set(anoF, mesF, diaF, horaF, minutoF);
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


        int i = 0;

        for ( i = 0; i < arrayList.size(); i++){
            Intent terceira = new Intent(ContactsContract.Intents.Insert.ACTION);
            terceira.setType(ContactsContract.RawContacts.CONTENT_TYPE);

            terceira.putExtra(ContactsContract.Intents.Insert.NAME, arrayList.get(i).split(" ")[0]);
            terceira.putExtra(ContactsContract.Intents.Insert.PHONE, arrayList.get(i).split(" ")[1]);
            terceira.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME);
            startActivity(terceira);
        }
        // mandando o nome


        // mandando email
        //terceira.putExtra(ContactsContract.Intents.Insert.EMAIL, EditavelEmail.getText().toString());
        //terceira.putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME);

        // mandando o telefone


        // mandando o endereco
        //intent.putExtra(ContactsContract.Intents.Insert.POSTAL, end);
        //intent.putExtra(ContactsContract.Intents.Insert.POSTAL_TYPE, ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME);

        // iniciando a intent




    }

    public void whatsapp (View view){

        String mensagem = "Festa no dia " + dia + "/" + mes + "/" + ano + " às " + hora + ":" + minuto +
                "\n" + "Para mais informações contacte o organizador(a) " + "\nNome do organizador(a): " + EditavelOrg.getText().toString() +
                "\nEmail do Organizador(a): " + EditavelEmail.getText().toString();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, mensagem);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(sendIntent, ""));

    }

    public void facebook (View view){
        Intent nova = new Intent(this, Terceira.class);
        startActivity(nova);
    }

    public void addcontato (View view){

        Editavelconv1 = (EditText) findViewById(R.id.contato1);
        Editaveltel1 = (EditText) findViewById(R.id.telefone1);

        String contato = Editavelconv1.getText().toString();
        String telefone = Editaveltel1.getText().toString();
        arrayList.add(contato + " " + telefone);

    }


}


