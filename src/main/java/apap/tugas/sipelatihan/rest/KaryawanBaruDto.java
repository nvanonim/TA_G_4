package apap.tugas.sipelatihan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KaryawanBaruDto {
    private String nama;

    @JsonProperty("no_telepon")
    private String noTelepon;

    private String alamat;

    private String divisi;

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getDivisi() {
        return divisi;
    }

    public String getNama() {
        return nama;
    }

    public String getNoTelepon() {
        return noTelepon;
    }
}
