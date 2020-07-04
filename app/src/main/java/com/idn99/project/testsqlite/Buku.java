package com.idn99.project.testsqlite;

public class Buku {
    private int kode_buku;
    private String nama_buku;
    private String pengarang;

    public Buku(int kode_buku, String nama_buku, String pengarang) {
        this.kode_buku = kode_buku;
        this.nama_buku = nama_buku;
        this.pengarang = pengarang;
    }

    public int getKode_buku() {
        return kode_buku;
    }

    public String getNama_buku() {
        return nama_buku;
    }

    public String getPengarang() {
        return pengarang;
    }
}
