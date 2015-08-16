package com.mdsproduction.cesg;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class loans extends ActionBarActivity {
    String DB_FILE_NAME = "cesg.db";

    final List<String> province = new ArrayList<>();
    final List<String> four = new ArrayList<>();
    final List<String> five = new ArrayList<>();
    final List<String> six = new ArrayList<>();
    final List<String> seven = new ArrayList<>();
    final List<String> eight = new ArrayList<>();
    final List<String> nine = new ArrayList<>();
    final List<String> ten = new ArrayList<>();
    final List<String> eleven = new ArrayList<>();
    final List<String> tweleve = new ArrayList<>();
    final List<String> thirteen = new ArrayList<>();
    final List<String> fourteen = new ArrayList<>();

    Integer[] imgid={
            R.drawable.alberta,
            R.drawable.bc,
            R.drawable.manitoba,
            R.drawable.newbrunswik,
            R.drawable.newfounlandandlabrador,
            R.drawable.northwestterritories,
            R.drawable.novascotia,
            R.drawable.nunavut,
            R.drawable.ontario,
            R.drawable.princeedwardislands,
            R.drawable.quebec,
            R.drawable.saskatchewan,
            R.drawable.yukon,
            R.drawable.canada,
    };

    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loans);

        // Called to create DB (Only will run on the initial run)
        createDB();

        SQLiteDatabase mydatabase;
        mydatabase = this.openOrCreateDatabase(DB_FILE_NAME, MODE_PRIVATE, null);

        String TableName = "CESG";
        String query = "SELECT * FROM " + TableName + ";";
        Cursor c = mydatabase.rawQuery(query, null);
        Log.d("TAG", "Table: " + TableName);
        Log.d("TAG", "Cursor: " + c.toString());

        // Fill my arrays from the database
        //c.moveToFirst();
        while (c.moveToNext())
        {
            //Log.d("TAG", "I am here: ");

            //Grab Data from the file and save it to the Local List
            province.add(c.getString(0));
            four.add(c.getString(1));
            five.add(c.getString(2));
            six.add(c.getString(3));
            seven.add(c.getString(4));
            eight.add(c.getString(5));
            nine.add(c.getString(6));
            ten.add(c.getString(7));
            eleven.add(c.getString(8));
            tweleve.add(c.getString(9));
            thirteen.add(c.getString(10));
            fourteen.add(c.getString(11));
        }
        c.close();

        Log.d("TAG", "I am here1: ");

        // Creates the list View with Province
        final ListView lv = (ListView) findViewById(R.id.provinceLV);

        // This shows the list of Provinces in aListView
        // ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, province);

        // This shows the list of Provinces with an image in aListView
        //ArrayAdapter aa = new ArrayAdapter(this, R.layout.custome_list, R.id.Itemname, province);

        search = (EditText) findViewById(R.id.search);

        CustomListAdapter aa=new CustomListAdapter(this, province, imgid);
        lv.setAdapter(aa);

        Log.d("TAG", "I am here2: " + aa.toString());
        Log.d("TAG", "I am here3: " + lv.toString());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Grab the selected Names email and phoneNumber
                final String four1 = four.get(position);
                final String five1 = five.get(position);
                final String six1 = six.get(position);
                final String seven1 = seven.get(position);
                final String eight1 = eight.get(position);
                final String nine1 = nine.get(position);
                final String ten1 = ten.get(position);
                final String eleven1 = eleven.get(position);
                final String twelve1 = tweleve.get(position);
                final String thirteen1 = thirteen.get(position);
                final String fourteen1 = fourteen.get(position);

                Log.d("TAG", "I am here4: ");
                // Create an intent for the friendsDetail View
                Intent intent = new Intent(getApplicationContext(), loanDetails.class);

                // Parse the data selected
                intent.putExtra("four", four1);
                intent.putExtra("five", five1);
                intent.putExtra("six", six1);
                intent.putExtra("seven", seven1);
                intent.putExtra("eight", eight1);
                intent.putExtra("nine", nine1);
                intent.putExtra("ten", ten1);
                intent.putExtra("eleven", eleven1);
                intent.putExtra("twelve", twelve1);
                intent.putExtra("thirteen", thirteen1);
                intent.putExtra("fourteen", fourteen1);
                startActivity(intent);
            }
        });

        // My Search function
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // The text changed will match the typed data with existing data and return the results
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<String> temp = new ArrayList<String>();
                int textLength = search.getText().length();
                temp.clear();

                for (int i = 0; i < province.size(); i++) {
                    if (textLength <= province.get(i).length()) {
                        if (search.getText().toString().equalsIgnoreCase((String)
                                province.get(i).subSequence(0, textLength))) {


                            Log.d("TAG", "Search: " + search.getText().toString());
                            //temp.clear();

                            // Sets the temp list if a match found
                            temp.add(province.get(i));
                            Log.d("TAG", "Temp: " + temp.toString());
                        }

                    }
                }

                // Display the temp result in the listView
                CustomListAdapter aa=new CustomListAdapter(loans.this, temp, imgid);
                lv.setAdapter(aa);
            }

            @Override
            public void afterTextChanged(Editable s) {

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
