package com.example.yemektarifleri.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yemektarifleri.Database.DatabaseHelper;
import com.example.yemektarifleri.Model.Yemek;
import com.example.yemektarifleri.R;

import java.io.IOException;

public class YemekDetayActivity extends AppCompatActivity {

    LinearLayout llMalzemeler;
    LinearLayout llAlerjenler;
    ImageView tarifResim;
    TextView tarifBaslik, tvKategoriAdi, tvPisirmeSuresi, tvHazirlamaSuresi, tvKisiSayisi, tvTarif;

    CheckBox chk;

    SQLiteDatabase db;
    DatabaseHelper databaseHelper;

    private void malzemeleriDoldur(String[] dizi){

        llMalzemeler = (LinearLayout)findViewById(R.id.llDetayMalzemeler);
        llMalzemeler.setOrientation(LinearLayout.VERTICAL);
        for(int i = 0;i<dizi.length;i++){
            chk = new CheckBox(this);
            chk.setId(i);
            chk.setText(dizi[i]);
            llMalzemeler.addView(chk);


        }

    }

    public void alerjenleriDoldur(String[] dizi){
        llAlerjenler = (LinearLayout)findViewById(R.id.llDetayAlerjenler);
        llAlerjenler.setOrientation(LinearLayout.VERTICAL);
        for(int i = 0;i<dizi.length;i++){
            chk = new CheckBox(this);
            chk.setId(i);
            chk.setText(dizi[i]);
            llAlerjenler.addView(chk);


        }

    }

    public String getKategoriAdi(int id){
        String sorgu = "Select * from Turler where id ="+id;
        Cursor c = db.rawQuery(sorgu,null);
        c.moveToFirst();


        String baslik = "";
        do {
            baslik = c.getString(1);

        }while (c.moveToNext());
        c.close();
        return baslik;
    }



    public Yemek yemekDataylari(int id){
        Yemek yemek = new Yemek();
        String sorgu = "Select * from Yemekler where yemek_id ="+id;
        Cursor c = db.rawQuery(sorgu,null);
        c.moveToFirst();
        do {
            yemek.setYapilisi(c.getString(7));
            yemek.setYemek_adi(c.getString(1));
            yemek.setYemek_id(c.getInt(0));
            yemek.setResim(c.getString(8));
            yemek.setYemek_hazirlama_suresi(c.getInt(3));
            yemek.setYemek_pisirme_suresi(c.getInt(4));
            yemek.setYemek_kisi_sayisi(c.getInt(5));
            yemek.setYemek_tur_id(c.getInt(2));

            String[] malzemeler = c.getString(c.getColumnIndex("yemek_malzemeleri")).split(",");
            String[] alerjenler = c.getString(c.getColumnIndex("yemek_alerjenleri")).split(",");
            yemek.setAlerjenler(alerjenler);
            yemek.setMalzemeler(malzemeler);
            malzemeleriDoldur(malzemeler);
            alerjenleriDoldur(alerjenler);

        }while (c.moveToNext());
        c.close();
        return yemek;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemek_detay);

        int yemekId = getIntent().getIntExtra("yemekId",1);

        try {
            databaseHelper = new DatabaseHelper(this);
            db = databaseHelper.getReadableDatabase();

        }catch (IOException e){

        }


        Yemek yemek = yemekDataylari(yemekId);



        llMalzemeler = (LinearLayout)findViewById(R.id.llDetayMalzemeler);
        llAlerjenler = (LinearLayout)findViewById(R.id.llDetayAlerjenler);
        tarifResim = (ImageView)findViewById(R.id.ivDetayResim);
        tarifBaslik = (TextView)findViewById(R.id.tvDetayYemekBaslik);
        tvKategoriAdi = (TextView)findViewById(R.id.tvDetayYemekTurAdi);
        tvPisirmeSuresi = (TextView)findViewById(R.id.tvDetayPisirmeSuresi);
        tvHazirlamaSuresi = (TextView)findViewById(R.id.tvDetayHazirlamaSuresi);
        tvKisiSayisi = (TextView)findViewById(R.id.tvDetayKisiSayisi);
        tvTarif = (TextView)findViewById(R.id.tvDetayTarif);

        tarifBaslik.setText(yemek.getYemek_adi());
        tvTarif.setText(yemek.getYapilisi());
        tvKisiSayisi.setText(""+yemek.getYemek_kisi_sayisi());
        tvHazirlamaSuresi.setText(""+yemek.getYemek_hazirlama_suresi());
        tvPisirmeSuresi.setText(""+yemek.getYemek_pisirme_suresi());
        tvKategoriAdi.setText(getKategoriAdi(yemek.getYemek_tur_id()));
        int id = getResources().getIdentifier(yemek.getResim(),"drawable",this.getPackageName());
        tarifResim.setImageResource(id);



    }
}
