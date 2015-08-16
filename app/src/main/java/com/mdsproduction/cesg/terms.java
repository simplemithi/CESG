package com.mdsproduction.cesg;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class terms extends ActionBarActivity {
    final String TAG = "myAPP";

    final List<String> terms = new ArrayList<>();
    final List<String> definition = new ArrayList<>();

    String DB_FILE_NAME = "cesg.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        // Called to create DB (Only will run on the initial run)
        createDB();

        SQLiteDatabase mydatabase;
        mydatabase = this.openOrCreateDatabase(DB_FILE_NAME, MODE_PRIVATE, null);

        // Set the table name and the query
        String TableName = "terms1";
        String query = "SELECT * FROM " + TableName + ";";
        Cursor c = mydatabase.rawQuery(query, null);

        Log.d("TAG", "Table: " + TableName);
        Log.d("TAG", "Cursor: " + c.toString());

        // Fill my arrays from the database
        //c.moveToFirst();
        while (c.moveToNext())
        {
            Log.d("TAG", "I am here: ");
            terms.add(c.getString(0));
            definition.add(c.getString(1));
        }
        c.close();

        Log.d("TAG", "I am here1: ");

        // Creates the list View with Terms
        final ListView lv = (ListView) findViewById(R.id.termsLV);
        //lv.setBackgroundColor(Color.parseColor("GREEN"));
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, terms);
        lv.setAdapter(aa);
        
        Log.d("TAG", "I am here2: " + aa.toString());
        Log.d("TAG", "I am here3: " + lv.toString());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Grab the selected definition
                final String def = definition.get(position);

                // Create an intent for the termDefinitions View
                Intent intent = new Intent(getApplicationContext(), termDefinition.class);

                // Parse the data selected
                intent.putExtra("definition", def);
                startActivity(intent);
            }
        });
    }

    // CreateDB() implementation
    public void createDB() {
        try
        {
            String destPath = "/data/data/" + getApplicationContext().getPackageName() + "/databases/";

            File destPathFile =  new File(destPath);
            if (!destPathFile.exists())
                destPathFile.mkdirs();

            File destFile = new File(destPath + DB_FILE_NAME);
            if (!destFile.exists())
            {
                Log.d("TAG", "First run, copying default database");
                copyFile(this.getAssets().open(DB_FILE_NAME),
                        new FileOutputStream(destPath + "/" + DB_FILE_NAME));
            }
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    // CopyFile Method
    // Code taken from the MAP524 website
    public void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException
    {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0)
            outputStream.write(buffer, 0, length);
        inputStream.close();
        outputStream.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_terms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
