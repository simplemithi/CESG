package com.mdsproduction.cesg;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    // Sets the default lang to English
    static boolean clicked= true;
    //static String lang = "French";
    static String name = "Click here to change the language to French";// + lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This intent is used with the langButton
        final Intent intent = new Intent(MainActivity.this, MainActivity.class);

        final Button button = (Button) findViewById(R.id.langBtn);
        button.setText(name);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if else changing the lang from Eng to French and vise versa
                if (clicked) {

                    // Changes the text of the langButton
                    name = "Click here to change the language to English";
                    Log.d("TAG", "In setText if");

                    // Changes the contentView to support French Language
                    String languageToLoad = "fr"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());

                    Log.d("TAG", "Button Name: " + button.getText().toString());

                    //eng.setText("Test123");
                    //MainActivity.this.setContentView(R.layout.activity_main);

                    // Changes the langButton state
                    clicked = false;

                    // Calls the MainActivity
                    startActivity(intent);

                // Same as the if statement
                } else {
                    name = "Click here to change the language to French";
                    Log.d("TAG", "In setText else");

                    String languageToLoad = "en"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());

                    Log.d("TAG", "Button Name: " + button.getText().toString());

                    //eng.setText("Test123");
                    //MainActivity.this.setContentView(R.layout.activity_main);
                    clicked = true;

                    startActivity(intent);
                }
            }
        });

        // Call Terms page if the terms button clicked
        Button termsButton = (Button)findViewById(R.id.termsBtn);
        termsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "In Terms");
                Intent intent = new Intent(MainActivity.this, terms.class);
                startActivity(intent);
            }
        });

        // Call Loans page if the terms button clicked
        Button loansButton = (Button)findViewById(R.id.loansBtn);
        loansButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "In Loans");
                Intent intent = new Intent (MainActivity.this, loans.class);
                startActivity(intent);
            }
        });

        // Call Contact page if the terms button clicked
        Button contactButton = (Button)findViewById(R.id.contactBtn);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, contact.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_Help:
                Intent helpIntent = new Intent(this, HelpMenu.class);
                this.startActivity(helpIntent);
                break;
            case R.id.action_About:
                Intent aboutIntent = new Intent(this, AboutMenu.class);
                this.startActivity(aboutIntent);
            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }
}
