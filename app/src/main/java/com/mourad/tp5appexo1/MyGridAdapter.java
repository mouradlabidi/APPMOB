package com.mourad.tp5appexo1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyGridAdapter extends ArrayAdapter<Produit> {
private Activity context;
private ArrayList<Produit> listProduits;
Button del, edit;

    public  MyGridAdapter(Activity context, ArrayList<Produit> listProduits){
        super (context, R.layout.ma_cellule, listProduits);
        this.context=context;
        this.listProduits=listProduits;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.ma_cellule, null);
        }
        TextView productName=(TextView)convertView.findViewById(R.id.productName);
        TextView productImage =(TextView)convertView.findViewById(R.id.productimage);

        productName.setText(listProduits.get(position).getLabel());
        productImage.setText(String.valueOf(listProduits.get(position).getPhoto()));

        del = (Button)convertView.findViewById(R.id.deletebtn);
        edit= (Button)convertView.findViewById(R.id.editbtn);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listProduits.remove((position));
                // listAchats.remove();
                notifyDataSetChanged();
            }
        });

        edit = (Button) convertView.findViewById(R.id.editbtn);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return  convertView;

    }


    public void onClick(View v){

//         int position = (Integer) v.getTag();
//
//         if (v.getId()==R.id.deletebtn){
//            // listAchats.remove(position);
//         }
//         else {
//             //
//         }
//         this.notifyDataSetChanged();

    }

}