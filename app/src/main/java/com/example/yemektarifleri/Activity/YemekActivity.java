package com.example.yemektarifleri.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.yemektarifleri.Adapter.YemekAdapter;
import com.example.yemektarifleri.Database.DatabaseHelper;
import com.example.yemektarifleri.Model.Tur;
import com.example.yemektarifleri.Model.Yemek;
import com.example.yemektarifleri.R;

import java.io.IOException;
import java.util.ArrayList;

public class YemekActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Yemek> yemekler;
    YemekAdapter yemekAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemek);
        listView = (ListView)findViewById(R.id.listViewTarifler);

        int gelenVeri = getIntent().getIntExtra("tur",1);




        SQLiteDatabase db;
        Cursor c;

        yemekler = new ArrayList<>();


        try {
            databaseHelper = new DatabaseHelper(getApplicationContext());
            databaseHelper.createDatabase();
            db = databaseHelper.getReadableDatabase();
            c = db.rawQuery("select * from Yemekler where yemek_tur_id = "+gelenVeri+" order by yemek_id desc", null);
            while (c.moveToNext()){

                int yemekId = c.getInt(0);
                Log.d("LOG",""+c.getString(1));
                String yemekAdi = c.getString(1);
                int yemekTurId = c.getInt(2);
                int hazirlamaSuresi = c.getInt(3);
                int pisirmeSuresi = c.getInt(4);
                int kisiSayisi = c.getInt(5);

                String malzeme = c.getString(6);
                String[] malzemeler = malzeme.split(",") ;
                String alerjen = c.getString(9);
                String[] alerjenler = alerjen.split(",");

                String yapilis = c.getString(7);
                String resim = c.getString(8);

                yemekler.add(
                        new Yemek(
                                yemekId,
                                yemekAdi,
                                yemekTurId,
                                hazirlamaSuresi,
                                pisirmeSuresi,
                                kisiSayisi,
                                malzemeler,
                                yapilis,
                                resim,
                                alerjenler
                        )
                );
            }
        }
        catch (IOException e){

        }


        
        yemekAdapter = new YemekAdapter(this,yemekler);
        listView = (ListView)findViewById(R.id.listViewTarifler);
        listView.setAdapter(yemekAdapter);

    }
}
