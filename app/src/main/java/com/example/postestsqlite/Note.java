package com.example.postestsqlite;

public class Note {

    private String tanggal;
    private String judul;
    private String deskripsi;

    public String getTanggal(){
        return tanggal;
    }

    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }

    public String getJudul(){
        return judul;
    }

    public void setJudul(String judul){
        this.judul = judul;
    }

    public String getDeskripsi(){
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }
}
