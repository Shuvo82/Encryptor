package com.example.encryptor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Decrypt_section extends AppCompatActivity {
    EditText encrypted;
    TextView decrypted;
    private Button encrypt_button,page2;
    String print="";
    String dec;
    int rnd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decrypt_section);
        encrypted=(EditText) findViewById(R.id.encrypted);
        decrypted=(TextView) findViewById(R.id.decrypted);
        page2=(Button)findViewById(R.id.page2);
        encrypt_button=(Button) findViewById(R.id.bt_encrypt);

        encrypt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(encrypted.length()<6)
                {
                    Toast.makeText(Decrypt_section.this,"Minimum encrypted letter required!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    decrypted.setText(null);

                    //start
                    String[] troy=encrypted.getText().toString().split("");
                    rnd=(troy[0].charAt(0));
                    String en=encrypted.getText().toString();
                    String[] t=en.split("");
                    for (int i=1;i<en.length() ;i++) {
                        int ascii_value=(t[i].charAt(0))-rnd-723;
                        char y=(char) ascii_value;
                        String str = new Character((char) y).toString();
                        print=print+str;
                    }
                    //end
                    decrypted.setText(print);
                    print="";

                }


            }
        });






//page changing
        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //decrypted.setText("Page 2 is clicked");
                // Toast.makeText(MainActivity.this,"Page 2 clicked",Toast.LENGTH_LONG).show();
                Intent in =new Intent(v.getContext(),MainActivity.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);

            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder aleartdialog;
        aleartdialog =new AlertDialog.Builder(Decrypt_section.this);
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
            startActivity(new Intent(Decrypt_section.this, about.class));
            return true;
        } else if (itemId == R.id.exi_t) {
            finish();
            System.exit(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
