package com.example.yemektarifleri.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.yemektarifleri.Adapter.TurAdapter;
import com.example.yemektarifleri.Database.DatabaseHelper;
import com.example.yemektarifleri.Model.Tur;
import com.example.yemektarifleri.Model.Yemek;
import com.example.yemektarifleri.R;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView liste;
    ArrayList<Yemek> yemekArrayList;
    ArrayList<Tur> turArrayList;
    TurAdapter turAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db;
        Cursor c;
        turArrayList = new ArrayList<>();

        try {
            databaseHelper = new DatabaseHelper(getApplicationContext());
            databaseHelper.createDatabase();
            db = databaseHelper.getReadableDatabase();
            c = db.rawQuery("select * from Turler ", null);
            while (c.moveToNext()){
                turArrayList.add(new Tur(c.getInt(0),c.getString(1),c.getString(2)));
            }
        }
        catch (IOException e){

        }




/*
        yemekArrayList = new ArrayList<>();
        String[] malzemeler = {"malzeme1","malzeme2","malzeme3"};
        String[] alerjenler = {"alerjen1","alerjen2","alerjen3"};


        yemekArrayList.add(

                new Yemek(
                        1,"Yemek adı",1,3,3,5,malzemeler,"yapılışı","resim",alerjenler
                )
        );

 */


        turAdapter = new TurAdapter(this,turArrayList);
        liste = (ListView) findViewById(R.id.listView);
        liste.setAdapter(turAdapter);
    }
}
