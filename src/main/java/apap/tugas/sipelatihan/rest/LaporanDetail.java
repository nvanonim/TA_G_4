package apap.tugas.sipelatihan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LaporanDetail {
    @JsonProperty("username")
    private String username;

    @JsonProperty("jumlahPelatihan")
    private Integer jumlahPelatihan;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getJumlahPelatihan() {
        return jumlahPelatihan;
    }

    public void setJumlahPelatihan(Integer jumlahPelatihan) {
        this.jumlahPelatihan = jumlahPelatihan;
    }





}
