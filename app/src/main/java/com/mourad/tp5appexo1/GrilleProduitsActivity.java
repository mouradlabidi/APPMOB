package com.mourad.tp5appexo1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GrilleProduitsActivity extends AppCompatActivity {
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 1;
    GridView gridView;

    private static String user;

    private ArrayList<Produit> listProduits;
    private ArrayAdapter<Produit> adapter;
    private MyGridAdapter myListAdapter;
    private DatabaseReference productdataset;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille_produits);

        gridView=(GridView)findViewById(R.id.grid_view);









    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {
            Intent intent = new Intent(this,AjouterProduitActivity.class);
            startActivityForResult(intent,SECOND_ACTIVITY_REQUEST_CODE);
            //startActivity(new Intent(getApplicationContext(), AjouterProduitActivity.class));
        }
        return true;
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String label;
        String description;
        String photo;
        String id;

        if(requestCode==2){
            if(resultCode==2){
                Toast.makeText(GrilleProduitsActivity.this,"Nafs les informations", Toast.LENGTH_SHORT).show();
            }
            if(resultCode==1){
                label=data.getExtras().getString("label");
                description=data.getExtras().getString("description");
                photo=data.getExtras().getString("photo");
                id=data.getExtras().getString("id");
                modifier(label,description,photo,id);
            }
            if(resultCode==0){
                Toast.makeText(GrilleProduitsActivity.this,"Modification annulé!",Toast.LENGTH_SHORT).show();
            }
        }};

    public void modifier(String label,String description,String photo,String _id){
        Produit produit=new Produit(_id,label,description,photo);
        productdataset.child(_id).setValue(produit).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(GrilleProduitsActivity.this,"Modifié!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(GrilleProduitsActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

}};

