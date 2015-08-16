package com.mdsproduction.cesg;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class loanDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_details);

        Intent intent = getIntent();

        String four = intent.getStringExtra("four");
        String five = intent.getStringExtra("five");
        String six = intent.getStringExtra("six");
        String seven = intent.getStringExtra("seven");
        String eight = intent.getStringExtra("eight");
        String nine = intent.getStringExtra("nine");
        String ten = intent.getStringExtra("ten");
        String eleven = intent.getStringExtra("eleven");
        String twelve = intent.getStringExtra("twelve");
        String thirteen = intent.getStringExtra("thirteen");
        String fourteen = intent.getStringExtra("fourteen");

        TextView tv = (TextView) findViewById(R.id.textView3);
        tv.setText(four);
        TextView tv1 = (TextView) findViewById(R.id.textView4);
        tv1.setText(five);
        TextView tv2 = (TextView) findViewById(R.id.textView5);
        tv2.setText(six);
        TextView tv3 = (TextView) findViewById(R.id.textView6);
        tv3.setText(seven);
        TextView tv4 = (TextView) findViewById(R.id.textView7);
        tv4.setText(eight);
        TextView tv5 = (TextView) findViewById(R.id.textView8);
        tv5.setText(nine);
        TextView tv6 = (TextView) findViewById(R.id.textView9);
        tv6.setText(ten);
        TextView tv7 = (TextView) findViewById(R.id.textView10);
        tv7.setText(eleven);
        TextView tv8 = (TextView) findViewById(R.id.textView11);
        tv8.setText(twelve);
        TextView tv9 = (TextView) findViewById(R.id.textView12);
        tv9.setText(thirteen);
        TextView tv10 = (TextView) findViewById(R.id.textView13);
        tv10.setText(fourteen);

        // Directs back to main page of the app if  button clicked
        Button homeButton = (Button)findViewById(R.id.homeBtn);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "In HomeButton");
                Intent intent = new Intent(loanDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loan_details, menu);
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
