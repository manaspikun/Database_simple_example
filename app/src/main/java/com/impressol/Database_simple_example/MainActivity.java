package com.impressol.Database_simple_example;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */

    SQLiteDatabase db1 = null;
    private static String DBNAME = "PERSONS.db";
    Button btn,btn1,btn2,btn3 = null;
    TextView tvw;
    EditText edt1,edt2,edt3 = null;
    Editable d1,d2,d3 = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        edt3= (EditText) findViewById(R.id.edt3);
        btn = (Button) findViewById(R.id.btn);
        //Database will be created through below method
        db1 = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);
        //Create the table if it is not existing

        btn.setOnClickListener(new View.OnClickListener() {
            //handling the click method on button
            @Override
            public void onClick(View view) {
                //For fetching data from edittext, use editable instead of string
                d1 = edt1.getText();
                d2 = edt2.getText();
                d3 = edt3.getText();
                try{

                    db1.execSQL("CREATE TABLE IF NOT EXISTS tabq34(ID INTEGER PRIMARY KEY ,FIRSTNAME VARCHAR, MIDDLENAME VARCHAR ,LASTNAME VARCHAR ); ");
                    db1.execSQL("INSERT INTO tabq34 (FIRSTNAME,MIDDLENAME,LASTNAME)  VALUES ('"+d1+"','"+d2+"','"+d3+"');");
                    Cursor c = db1.rawQuery("SELECT * FROM tabq34", null);
                    if(c!= null){
                        if (c.moveToFirst()) {
                            do {
                                //whole data of column is fetched by getColumnIndex()
                                String firstname =c.getString(c.getColumnIndex("FIRSTNAME"));
                                String middlename =c.getString(c.getColumnIndex("MIDDLENAME"));
                                String lastname =c.getString(c.getColumnIndex("LASTNAME"));
                                System.out.println(firstname);
                                System.out.println(middlename);
                                System.out.println(lastname);
                            }while(c.moveToNext());}
                        //count the total number of entries
                        Integer a =  c.getCount();
                        System.out.println(a);
                        //db1.close();
                        //if you close the database then illegal exception will be occured...
                    }} catch(Exception e){
                    System.out.println(e);
                }


                btn1 = (Button)  findViewById(R.id.btn1);
                btn1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        db1.execSQL("DELETE  FROM tabq34 WHERE ID > -1;");
                    }
                });


                btn2 = (Button)  findViewById(R.id.btn2);
                btn2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        db1.execSQL("UPDATE tabq34 SET LASTNAME='SAM' WHERE FIRSTNAME='SAMKIT'");
                    }
                });

                btn3 = (Button)  findViewById(R.id.btn3);
                btn3.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        tvw = (TextView) findViewById(R.id.tvw2);

                        tvw.setText(d1+""+d2+""+d3);
                        String s = (String) tvw.getText();
                        System.out.print(s);
                    }
                });
            }});
    }
}
