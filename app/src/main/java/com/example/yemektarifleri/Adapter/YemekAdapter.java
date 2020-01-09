package com.example.yemektarifleri.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yemektarifleri.Activity.YemekDetayActivity;
import com.example.yemektarifleri.Model.Yemek;
import com.example.yemektarifleri.R;

import java.util.ArrayList;

public class YemekAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Yemek> yemekler;

    public YemekAdapter(Activity activity, ArrayList<Yemek> yemekler) {
        this.context = activity;
        this.yemekler = yemekler;
        this.layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return yemekler.size();
    }

    @Override
    public Object getItem(int position) {
        return yemekler.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View layout = layoutInflater.inflate(R.layout.listview_yemek_gorunumu,null);

        ImageView ivYemekResim = (ImageView)layout.findViewById(R.id.ivYemekResim);
        TextView tvYemekIsim = (TextView)layout.findViewById(R.id.tvYemekIsim);
        TextView tvHazirlamaSuresi = (TextView)layout.findViewById(R.id.tvDetayHazirlamaSuresi);
        TextView tvPisirmeSuresi = (TextView)layout.findViewById(R.id.tvPisirmeSuresi);
        TextView tvKisiSayisi = (TextView)layout.findViewById(R.id.tvKisiSayisi);

        int id = layout.getResources().getIdentifier(yemekler.get(position).getResim(),"drawable",context.getPackageName());



        ivYemekResim.setImageResource(id);
        tvYemekIsim.setText(yemekler.get(position).getYemek_adi());
        tvKisiSayisi.setText(""+yemekler.get(position).getYemek_kisi_sayisi());
        tvHazirlamaSuresi.setText(""+yemekler.get(position).getYemek_hazirlama_suresi());
        tvPisirmeSuresi.setText(""+yemekler.get(position).getYemek_pisirme_suresi());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, YemekDetayActivity.class);
                i.putExtra("yemekId",yemekler.get(position).getYemek_id());
                context.startActivity(i);
            }
        });


        return layout;
    }
}
