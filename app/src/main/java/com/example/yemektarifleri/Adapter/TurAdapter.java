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

import com.example.yemektarifleri.Activity.YemekActivity;
import com.example.yemektarifleri.Model.Tur;
import com.example.yemektarifleri.Model.Yemek;
import com.example.yemektarifleri.R;

import java.util.ArrayList;

public class TurAdapter extends BaseAdapter {

    Context context;
    ArrayList<Tur> turler;
    LayoutInflater layoutInflater;

    public TurAdapter(Activity activity, ArrayList<Tur> turArrayList){
        this.layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = activity;
        this.turler = turArrayList;
    }

    @Override
    public int getCount() {
        return turler.size();
    }

    @Override
    public Object getItem(int position) {
        return turler.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        View satirGorunumu = layoutInflater.inflate(R.layout.listview_tur_gorunumu,null);

        TextView tvTurBaslik = (TextView)satirGorunumu.findViewById(R.id.tvTurBaslik);
        ImageView ivTurResim = (ImageView)satirGorunumu.findViewById(R.id.ivTurResim);


        int id = context
                .getApplicationContext()
                .getResources()
                .getIdentifier(
                        turler.get(position).getTur_resim(),
                        "drawable",
                        context.getPackageName()


                );

        tvTurBaslik.setText(turler.get(position).getTur_adi());

        ivTurResim.setImageResource(id);

        satirGorunumu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, YemekActivity.class);
                intent.putExtra("tur",turler.get(position).getTur_id());
                context.startActivity(intent);


            }
        });

        return  satirGorunumu;
    }
}
