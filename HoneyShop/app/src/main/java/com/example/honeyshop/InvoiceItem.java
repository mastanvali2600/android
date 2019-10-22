package com.example.honeyshop;

public class InvoiceItem {
    private long id;
    private String name;
    private String quntity;
    private double price;
    private byte[] image;
    public InvoiceItem(long id, String name, String quntity, double price) {
        this.id = id;
        this.name = name;
        this.quntity = quntity;
        this.price = price;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuntity() {
        return quntity;
    }

    public void setQuntity(String quntity) {
        this.quntity = quntity;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
