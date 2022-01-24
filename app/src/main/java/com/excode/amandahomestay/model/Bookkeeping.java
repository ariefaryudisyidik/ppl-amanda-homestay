package com.excode.amandahomestay.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "bookkeeping")
public class Bookkeeping implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int idPenyewa;

    @ColumnInfo(name = "nama_penyewa")
    String namaPenyewa;

    @ColumnInfo(name = "nomor_kamar")
    String nomorKamar;

    @ColumnInfo(name = "nomor_telepon")
    String nomorTelepon;

    @ColumnInfo(name = "tanggal_masuk")
    String tanggalMasuk;

    @ColumnInfo(name = "tanggal_keluar")
    String tanggalKeluar;

    @ColumnInfo(name = "biaya")
    String biaya;

    public int getIdPenyewa() {
        return idPenyewa;
    }

    public void setIdPenyewa(int idPenyewa) {
        this.idPenyewa = idPenyewa;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public String getNomorKamar() {
        return nomorKamar;
    }

    public void setNomorKamar(String nomorKamar) {
        this.nomorKamar = nomorKamar;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }

    public void setTanggalKeluar(String tanggalKeluar) {
        this.tanggalKeluar = tanggalKeluar;
    }

    public String getBiaya() {
        return biaya;
    }

    public void setBiaya(String biaya) {
        this.biaya = biaya;
    }
}
