package com.example.honeyshop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DataConverter {
    public static byte[] convertBitmap2ByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        return stream.toByteArray();
    }
    public static Bitmap converByteArray2Bitmap(byte[] image){
        return BitmapFactory.decodeByteArray(image,0,image.length);
    }
    public static double getGrams(double kgPrice,double havingMoney){
        return  roundValue((1000*havingMoney)/kgPrice);


    }
    public static double getPriceByGrams(double kgPrice,int grams) {
        return roundValue((kgPrice*grams)/1000);
    }
    public static double getPriceByKG(double kgPrice,int no_of_kgs) {
        return kgPrice*no_of_kgs;
    }
    public static double getPriceByPacket(double packetPrice,int no_of_packets) {
        return packetPrice*no_of_packets;
    }
    public static double getMl(double literPrice,double havingMoney) {
        return roundValue((1000*havingMoney)/literPrice);
    }
    public static double getPriceByML(double literPrice,int ml) {
        return ((literPrice*ml)/1000);
    }
    public static double getPriceByLiter(double literPrice,int no_of_liters) {
        return literPrice*no_of_liters;
    }
    private static double roundValue(double value){
        return new BigDecimal(value / 1000).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}