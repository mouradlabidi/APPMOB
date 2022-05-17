package com.mourad.tp5appexo1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.app.Activity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AjouterProduitActivity extends AppCompatActivity {
    private static final int CODE_FORM1=1;
    private static final int CODE_Annuler=1;
    private static final int RESULT_OK=1;
    ImageView camera,img;
    Bitmap photo;
    Button ajouter, annuler;
    EditText label,description;

    private ArrayList<Produit> listProduits;
    private ArrayAdapter<Produit> adapter;
    private MyGridAdapter myListAdapter;
    private DatabaseReference productdataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit);


        ajouter=(Button) findViewById(R.id.ajout);
        annuler=(Button) findViewById(R.id.annul);
        label=(EditText) findViewById(R.id.label);
        description=(EditText) findViewById(R.id.description);
        final String image = Produit.BitmapToString(photo);

        productdataset = FirebaseDatabase.getInstance().getReference("Produits");
        productdataset.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            listProduits.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()){
                    Produit produit = d.getValue(Produit.class);
                    listProduits.add(produit);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }


    public void takepicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1888); // 1888 request code
        }
    }


    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data)
    {
        if ((requestCode==CODE_FORM1)&&(resultCode == Activity.RESULT_OK)) {
            photo = (Bitmap) data.getExtras().get("data");
            ImageView imageview = (ImageView)findViewById(R.id.img);
            imageview.setImageBitmap(photo);
        }

    }


    public void Annuler(View view) {
        Intent intent=new Intent();
        this.setResult(CODE_Annuler,intent);
        this.finish();
    }

    public void Ajouter(View view) {
        String label=((EditText) findViewById(R.id.label)).getText().toString();
        String desc = ((EditText) findViewById(R.id.description)).getText().toString();
        String image = Produit.BitmapToString(photo);

        if(label.isEmpty()|| desc.isEmpty()|| image.isEmpty()){
            Toast.makeText(AjouterProduitActivity.this, "Merci de remplir les champs",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("label", label);
        intent.putExtra("desc", desc);
        intent.putExtra("image", image);
        this.setResult(CODE_FORM1, intent);
        this.finish();

        String id = productdataset.push().getKey();
        productdataset.child(id).setValue(new Produit(id,label,desc,image)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(AjouterProduitActivity.this,"Produit ajoute avec succes",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("onComplete", task.getException().getMessage());
                }
            }
        });
    }



}

