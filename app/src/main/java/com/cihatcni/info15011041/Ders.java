package com.cihatcni.info15011041;

public class Ders {

    private String dersAdı;
    private String dersNotu;
    private int ogrenciSayisi;


    public  Ders(String dersAdı, String dersNotu, int ogrenciSayisi) {
        this.dersAdı = dersAdı;
        this.dersNotu = dersNotu;
        this.ogrenciSayisi = ogrenciSayisi;
    }

    public String getDersAdı() {
        return dersAdı;
    }

    public String getDersNotu() {
        return dersNotu;
    }

    public int getOgrenciSayisi() {
        return ogrenciSayisi;
    }

}
