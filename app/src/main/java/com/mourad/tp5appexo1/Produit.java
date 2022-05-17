package com.mourad.tp5appexo1;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Produit {

    private String id,label,description,photo;

    public Produit() {
    }

    public Produit(String id, String label, String description, String photo){
        this.id=id;
        this.label = label;
        this.description = description;
        this.photo = photo;
    }

    public Produit( String label, String description, String photo){
        this.label = label;
        this.description = description;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getLabel() {
        return label;
    }

    public String getPhoto() {
        return photo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static String BitmapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap StringToBitmap(String photo)throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(photo, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0,
                decodedByteArray.length);
    }





}
