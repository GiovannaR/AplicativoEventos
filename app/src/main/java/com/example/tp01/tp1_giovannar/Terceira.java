package com.example.tp01.tp1_giovannar;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by giovannariqueti on 23/09/17.
 */

public class Terceira extends AppCompatActivity {

    EditText Editavelter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terceira);

    }

    public void textar (View view){

        Editavelter = (EditText) findViewById(R.id.msgn);

        PackageManager pm = getPackageManager();

        Intent whatsapp = new Intent(Intent.ACTION_SEND);
        whatsapp.setType("text/plain");
        String text = Editavelter.getText().toString();

        PackageInfo info = pm.getPackageInfo ("com.whatsapp", PackageManager.GET_META_DATA);
        //Check if package exists or not. If not then code
        //in catch block will be called
        //whatsapp.setPackage("com.whatsapp");

        whatsapp.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(whatsapp, "Share with"));
    }


}