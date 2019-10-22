package com.example.honeyshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CreateItemActivity extends AppCompatActivity {
    private static final String TAG = "CreateItemActivity";
    Button btn_take_image,btn_save_Item;
    ImageView item_image;
    Bitmap bitmap_item_image;
    TextInputEditText edt_name,edt_price;
    Spinner typeSpinner;

    final int CAPTURE_IMAGE_REQUEST_CODE=51;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);
        init();
    }

    private void init() {
        btn_take_image=findViewById(R.id.btn_take_image);
        btn_save_Item=findViewById(R.id.btn_save_Item);
        item_image=findViewById(R.id.item_image);
        edt_name=findViewById(R.id.edt_name);
        edt_price=findViewById(R.id.edt_price);
        typeSpinner=findViewById(R.id.typeSpinner);

    }

    public void takeimage(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_REQUEST_CODE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CAPTURE_IMAGE_REQUEST_CODE:
                if(RESULT_OK == resultCode){
                    bitmap_item_image=(Bitmap) data.getExtras().get("data");
                    if(bitmap_item_image!=null)
                        item_image.setImageBitmap(bitmap_item_image);
                }
                break;
        }
    }
    public void saveImage(View view) {

        String typeId=getResources().getStringArray(R.array.values)[typeSpinner.getSelectedItemPosition()];

        String name=edt_name.getText().toString().trim();
        String price=edt_price.getText().toString().trim();

        byte[] image=null;
        if(bitmap_item_image!=null)
            image=DataConverter.convertBitmap2ByteArray(bitmap_item_image);
        if(name == null || name.isEmpty() || name.length() == 0 || name.equals(""))
        {
            //EditText is empty
            name=null;
        }
        if(price == null || price.isEmpty() || price.length() == 0 || price.equals("") )
        {
            //EditText is empty
            price=null;
        }
        if(name==null) {
            Toast.makeText(CreateItemActivity.this, "No value in Name", Toast.LENGTH_SHORT).show();
        }else if(price==null){
            Toast.makeText(CreateItemActivity.this,"No value in Price",Toast.LENGTH_SHORT).show();
        }else if(image==null){
            Toast.makeText(CreateItemActivity.this,"Please capture image",Toast.LENGTH_SHORT).show();
        }else {
            Item item = new Item(name, Integer.parseInt(typeId), image, Integer.parseInt(price));
            //To-D0 Saver Item in DB remove below statement
            AppDatabase db= Room.databaseBuilder(this,AppDatabase.class,"Production").allowMainThreadQueries().build();
            db.itemDAO().insertAll(item);
            startActivity(new Intent(CreateItemActivity.this, AddItemToListActivity.class));
        }
    }

}