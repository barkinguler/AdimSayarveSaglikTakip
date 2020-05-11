package com.example.yurumesayar.Post;

import com.google.gson.annotations.SerializedName;

public class Post {
    String KULLANICI;
    String SIFRE;
    int ADIM;


    public Post(String KULLANICI, String SIFRE, int ADIM) {
        this.KULLANICI = KULLANICI;
        this.SIFRE = SIFRE;
        this.ADIM = ADIM;
    }

    public String getKULLANICI() {
        return KULLANICI;
    }

    public void setKULLANICI(String KULLANICI) {
        this.KULLANICI = KULLANICI;
    }

    public String getSIFRE() {
        return SIFRE;
    }

    public void setSIFRE(String SIFRE) {
        this.SIFRE = SIFRE;
    }

    public int getADIM() {
        return ADIM;
    }

    public void setADIM(int ADIM) {
        this.ADIM = ADIM;
    }

}
