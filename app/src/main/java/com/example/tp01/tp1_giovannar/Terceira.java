package com.example.tp01.tp1_giovannar;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.media.MediaCas;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.service.textservice.SpellCheckerService;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

/**
 * Created by giovannariqueti on 23/09/17.
 */

public class Terceira extends AppCompatActivity {


    TextView campomsn;


    public void onCreate(Bundle savedInstanceState) {
        try {

            FacebookSdk.sdkInitialize(getApplicationContext());
            super.onCreate(savedInstanceState);
            setContentView(R.layout.terceira);

            if (AccessToken.getCurrentAccessToken() == null) {
                goLoginScreen();
            }
        }catch ( Exception io ){
            campomsn = (TextView) findViewById(R.id.mensagem);
            campomsn.setText(io.getMessage());
        }

    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout (View view){
        LoginManager.getInstance();
        goLoginScreen();
    }




}