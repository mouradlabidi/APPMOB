package com.mourad.tp5appexo1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;

public class GrilleProduitsActivity extends AppCompatActivity {
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 1;
    GridView gridView;
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
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data)
    {
        if ((requestCode==CODE_FORM1)&&(resultCode == Activity.RESULT_OK)) {
            photo = (Bitmap) data.getExtras().get("data");
            ImageView imageview = (ImageView)findViewById(R.id.img);
            imageview.setImageBitmap(photo);
        }

    }

}
