package com.example.honeyshop;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddItemViewHolder extends RecyclerView.ViewHolder {

    public AddItemViewHolder(@NonNull View itemView) {
        super(itemView);
        //btn_take_image.
    }
    /*public void takeimage(View view) {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,CAPTURE_IMAGE_REQUEST_CODE);
        }
    }*/
}
