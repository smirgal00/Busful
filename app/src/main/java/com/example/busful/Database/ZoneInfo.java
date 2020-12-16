package com.example.busful.Database;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;

public class ZoneInfo {

    private Double bilete;
    private Double a1;
    private Double a5;
    private Double a7;
    private Double a15;
    private Double a21;
    private Double a30;

    public void update(Double old, Double neww) {
        if (bilete.equals(old)) {
            bilete = neww;
        }
        if (a1 != null && a1.equals(old)) {
            a1 = neww;
        }
        else if (a5 != null && a5.equals(old)) {
            a5 = neww;
        }
        else if (a7 != null && a7.equals(old)) {
            a7 = neww;
        }
        else if (a15 != null && a15.equals(old)) {
            a15 = neww;
        }
        else if (a21 != null && a21.equals(old)) {
            a21 = neww;
        }
        else if (a30 != null && a30.equals(old)) {
            a30 = neww;
        }
    }

    @NonNull
    @Override
    public String toString() {
        if (a7 != null) {
            return "Pret bilet: " + this.bilete + " lei.\n"
                    + "Pret abonament 1 zile: " + this.a1 + " lei. \n"
                    + "Pret abonament 5 zile: " + this.a5 + " lei. \n"
                    + "Pret abonament 7 zile: " + this.a7 + " lei. \n"
                    + "Pret abonament 15 zile: " + this.a15 + " lei. \n"
                    + "Pret abonament 1 luna: " + this.a30 + " lei. \n";
        }
        else {
            return "Pret bilet: " + this.bilete + " lei.\n"
                    + "Pret abonament 2 saptamani: " + this.a15 + " lei. \n"
                    + "Pret abonament 3 saptamani: " + this.a21 + " lei. \n"
                    + "Pret abonament 1 luna: " + this.a30 + " lei. \n";
        }
    }

    public ZoneInfo() {

    }

    public ZoneInfo(Double bilete, Double a1, Double a5, Double a7, Double a15, Double a21, Double a30) {
        this.bilete = bilete;
        this.a1 = a1;
        this.a5 = a5;
        this.a7 = a7;
        this.a15 = a15;
        this.a21 = a21;
        this.a30 = a30;
    }

    public Double getBilete() {
        return bilete;
    }

    public void setBilete(Double bilete) {
        this.bilete = bilete;
    }

    public Double getA1() {
        return a1;
    }

    public void setA1(Double a1) {
        this.a1 = a1;
    }

    public Double getA5() {
        return a5;
    }

    public void setA5(Double a5) {
        this.a5 = a5;
    }

    public Double getA7() {
        return a7;
    }

    public void setA7(Double a7) {
        this.a7 = a7;
    }

    public Double getA15() {
        return a15;
    }

    public void setA15(Double a15) {
        this.a15 = a15;
    }

    public Double getA21() {
        return a21;
    }

    public void setA21(Double a21) {
        this.a21 = a21;
    }

    public Double getA30() {
        return a30;
    }

    public void setA30(Double a30) {
        this.a30 = a30;
    }

}
