package com.example.arysugiarto.mykamuss.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class KamusModel implements Parcelable {
    private int id;
    private String kosakata;
    private String deskripsi;
    private String kategori;

    public KamusModel(String kosakata, String deskripsi){
        this.kosakata       = kosakata;
        this.deskripsi  = deskripsi;
    }

    public KamusModel(int id, String kata, String deskripsi){
        this.id         = id;
        this.kosakata       = kata;
        this.deskripsi  = deskripsi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKoskata() {
        return kosakata;
    }

    public void setKoskata(String kata) {
        this.kosakata = kata;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String category) {
        this.kategori = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.kosakata);
        dest.writeString(this.deskripsi);
        dest.writeString(this.kategori);
    }

    public KamusModel() {
    }

    protected KamusModel(Parcel in) {
        this.id = in.readInt();
        this.kosakata = in.readString();
        this.deskripsi = in.readString();
        this.kategori = in.readString();
    }

    public static final Creator<KamusModel> CREATOR = new Creator<KamusModel>() {
        @Override
        public KamusModel createFromParcel(Parcel source) {
            return new KamusModel(source);
        }

        @Override
        public KamusModel[] newArray(int size) {
            return new KamusModel[size];
        }
    };
}

