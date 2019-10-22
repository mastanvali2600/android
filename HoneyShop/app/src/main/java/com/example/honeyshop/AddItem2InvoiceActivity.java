package com.example.honeyshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class AddItem2InvoiceActivity extends AppCompatActivity {
    private static final String TAG = "AddItem2InvoiceActivity";
    ImageView a_i_2_i_image;
    TextView a_i_2_i_name,value;
    Switch a_i_2_i_type_switch;
    Spinner a_i_2_i_type_spinner;
    Button btn_a_i_2_invoice;
    Item item;
    String quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item2_invoice);
        item=(Item)getIntent().getSerializableExtra("item");
        init();
        a_i_2_i_image.setImageBitmap(DataConverter.converByteArray2Bitmap(item.getImage()));
        a_i_2_i_name.setText(item.getName());

        onclickListeners();

    }
    public void onclickListeners(){
        btn_a_i_2_invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                double price=0;
                InvoiceItem invoiceItem=null;

                double quantity_nul=0;
                if(a_i_2_i_type_switch.isChecked()){
                    String checkedValue=value.getText().toString().trim();
                    boolean b=checkedValue == null || checkedValue.isEmpty() || checkedValue.length() == 0 || checkedValue.equals("");
                    if(b){
                        Toast.makeText(AddItem2InvoiceActivity.this,"Please enter price value",Toast.LENGTH_SHORT).show();
                    }else {
                        switch (item.getTypeId()) {
                            case 1:
                                price = Double.parseDouble(String.valueOf(value.getText()));
                                quantity_nul = DataConverter.getGrams(item.getPrice(), Double.parseDouble(String.valueOf(value.getText())));
                                if (quantity_nul >= 1000) {
                                    quantity = (quantity_nul / 1000) + "Kg ";
                                } else {
                                    quantity = quantity_nul + " grams";
                                }
                                break;
                            case 2:
                                price = Double.parseDouble(String.valueOf(value.getText()));
                                quantity_nul = DataConverter.getMl(item.getPrice(), Double.parseDouble(String.valueOf(value.getText())));
                                if (quantity_nul > 1000) {
                                    quantity = (quantity_nul / 1000) + "lit ";
                                } else {
                                    quantity = quantity_nul + " ml";
                                }
                                break;
                            default:
                                break;
                        }
                        invoiceItem=new InvoiceItem(item.getId(),item.getName(),quantity,price);
                        MainActivity.addToInvoice(invoiceItem);
                        startActivity(new Intent(AddItem2InvoiceActivity.this,MainActivity.class));
                    }
                }else {
                    String type = a_i_2_i_type_spinner.getSelectedItem().toString();
                    String checkedValue = value.getText().toString().trim();
                    boolean b = checkedValue == null || checkedValue.isEmpty() || checkedValue.length() == 0 || checkedValue.equals("");
                    if (b) {
                        Toast.makeText(AddItem2InvoiceActivity.this, "Please enter Quantity value", Toast.LENGTH_SHORT).show();
                    } else {
                        switch (item.getTypeId()) {
                            case 1:
                                switch (type.toLowerCase()) {
                                    case "grams":
                                        quantity = value.getText().toString() + "grams";
                                        price = DataConverter.getPriceByGrams(item.getPrice(), Integer.parseInt(value.getText().toString()));
                                        break;
                                    case "kg":
                                        quantity = value.getText().toString() + "kgs";
                                        price = DataConverter.getPriceByKG(item.getPrice(), Integer.parseInt(value.getText().toString()));
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 2:
                                switch (type.toLowerCase()) {
                                    case "liter":
                                        quantity = value.getText().toString() + "liters";
                                        price = DataConverter.getPriceByLiter(item.getPrice(), Integer.parseInt(value.getText().toString()));
                                        break;
                                    case "ml":
                                        quantity = value.getText().toString() + "ml";
                                        price = DataConverter.getPriceByML(item.getPrice(), Integer.parseInt(value.getText().toString()));
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 3:
                                quantity = value.getText().toString() + " packs";
                                price = DataConverter.getPriceByPacket(item.getPrice(), Integer.parseInt(value.getText().toString()));
                                break;
                            default:
                                break;
                        }
                        invoiceItem=new InvoiceItem(item.getId(),item.getName(),quantity,price);
                        AppDatabase db= Room.databaseBuilder(AddItem2InvoiceActivity.this,AppDatabase.class,"Production").allowMainThreadQueries().build();
                        MainActivity.addToInvoice(invoiceItem);
                        startActivity(new Intent(AddItem2InvoiceActivity.this,MainActivity.class));
                    }

                }

            }
        });
    }

    private void init() {
        a_i_2_i_image=findViewById(R.id.a_i_2_i_image);
        a_i_2_i_name=findViewById(R.id.a_i_2_i_name);
        value=findViewById(R.id.value);
        a_i_2_i_type_switch=findViewById(R.id.a_i_2_i_type_switch);
        btn_a_i_2_invoice=findViewById(R.id.btn_a_i_2_invoice);
        a_i_2_i_type_spinner=findViewById(R.id.a_i_2_i_type_spinner);
        a_i_2_i_type_switch.setChecked(true);
        a_i_2_i_type_spinner.setVisibility(View.INVISIBLE);
        if(item.getTypeId()==3){
            value.setVisibility(View.INVISIBLE);
        }
    }
    public void checkSwitch(View view){
        ArrayAdapter arrayAdapter = null;
        if(a_i_2_i_type_switch.isChecked()){
            a_i_2_i_type_spinner.setVisibility(View.INVISIBLE);
            switch (item.getTypeId()){
                case 3:
                    value.setVisibility(View.INVISIBLE);
                    break;
            }


        }else{
            a_i_2_i_type_spinner.setVisibility(View.VISIBLE);
            switch (item.getTypeId()){
                case 1:
                    setValuesToSpinner(arrayAdapter,getResources().getStringArray(R.array.kgType));
                    break;
                case 2:
                    setValuesToSpinner(arrayAdapter,getResources().getStringArray(R.array.literType));
                    break;
                case 3:
                    setValuesToSpinner(arrayAdapter,getResources().getStringArray(R.array.packetType));
                    value.setVisibility(View.VISIBLE);
                    break;
                default:break;
            }
            //R.array.kgType
        }
    }
    public void setValuesToSpinner(ArrayAdapter arrayAdapter,String[] values){
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, Arrays.asList(values));
        a_i_2_i_type_spinner.setAdapter(arrayAdapter);
    }
    public void cancelPage(View view){
        startActivity(new Intent(AddItem2InvoiceActivity.this,AddItemToListActivity.class));

    }


}
