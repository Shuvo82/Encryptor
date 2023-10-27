package com.example.encryptor;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SharedPreferences first_time=null;
    EditText decrypted;
    TextView encrypted;
    private Button encrypt_button,page2;
    String print="",en;
    int ind;
    private static int Splash_time_out=4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first_time = getSharedPreferences("com.mycompany.myAppName", MODE_PRIVATE);

      /*  new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent in=new Intent(MainActivity.this, intro.class);
                startActivity(in);
                finish();
            }
        },Splash_time_out);*/

        decrypted=(EditText) findViewById(R.id.decrypted);
        encrypted=(TextView) findViewById(R.id.encrypted);
        page2=(Button)findViewById(R.id.page2);
        encrypt_button=(Button) findViewById(R.id.bt_encrypt);

        encrypt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(decrypted.length()<5)
                {
                    Toast.makeText(MainActivity.this,"Please enter minimum 5 letter!",Toast.LENGTH_LONG).show();
                }
                else
                {

                   // Log.v("tag","Encrypt button is clicked");

                    encrypted.setText(null);

                    //start
                    Random rand=new Random();
                    ind=rand.nextInt(9939);
                    // System.out.println(ind+33);
                    char z=(char) (ind+33);
                    en=decrypted.getText().toString();

                    String rnd = new Character((char) z).toString();
                    // System.out.println("rnd:"+rnd);
                    print=print+rnd;
                    String[] t=en.split("");
                    for (int i=0;i<en.length() ;i++) {
                        int asciivalue=(t[i].charAt(0))+ind+33+723;
                        System.out.println(asciivalue);
                        char y=(char) asciivalue;
                        //int t1=t[i]+9;
                        String str = new Character((char) y).toString();
                        // String final=final+str;

                        //  System.out.println(str);
                        print=print+str;
                    }

                    //end
                    encrypted.setText(print);
                    print="";

                }

            }
        });

        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //decrypted.setText("Page 2 is clicked");
               // Toast.makeText(MainActivity.this,"Page 2 clicked",Toast.LENGTH_LONG).show();
                Intent in =new Intent(v.getContext(),Decrypt_section.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);

            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();

        if (first_time.getBoolean("firstrun", true)) {
            Toast.makeText(MainActivity.this,"This app is running first time",Toast.LENGTH_LONG).show();

            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            first_time.edit().putBoolean("firstrun", false).commit();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder aleartdialog;
        aleartdialog =new AlertDialog.Builder(MainActivity.this);
        aleartdialog.setIcon(R.drawable.what);
        aleartdialog.setTitle(R.string.alertdialog_title);
        aleartdialog.setMessage(R.string.alertdialog);
        aleartdialog.setCancelable(false);
        aleartdialog.setPositiveButton("Pretty sure!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        aleartdialog.setNegativeButton("Nope!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=aleartdialog.create();
        alertDialog.show();
    }

    //Working on 3 dot menubaar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.about) {
            startActivity(new Intent(MainActivity.this, about.class));
            return true;
        } else if (itemId == R.id.exi_t) {
            finish();
            System.exit(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
