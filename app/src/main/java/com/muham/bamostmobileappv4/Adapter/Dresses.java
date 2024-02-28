package com.muham.bamostmobileappv4.Adapter;

public class Dresses {
    private int layout;
    private int resimId;
    private String isim;
    private double fiyat;

    public Dresses(int layout,int resimId, String isim, double fiyat) {
        this.layout = layout;
        this.resimId = resimId;
        this.isim = isim;
        this.fiyat = fiyat;
    }

    public int getLayout() {
        return layout;
    }

    public int getResimId() {
        return resimId;
    }

    public String getIsim() {
        return isim;
    }

    public double getFiyat() {
        return fiyat;
    }
}
