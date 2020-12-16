package apap.tugas.sipelatihan.rest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.DateTimeFormat;

import apap.tugas.sipelatihan.model.RoleModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PegawaiDetail {
    // @JsonProperty("idPegawai")
    // private String idPegawai;

    @JsonProperty("username")
    private String username;

    @JsonProperty("nama")
    private String nama;

    @JsonProperty("noTelepon")
    private String noTelepon;

    @JsonProperty("tempatLahir")
    private String tempatLahir;

    @JsonProperty("tanggalLahir")
    // @DateTimeFormat(pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date tanggalLahir;

    @JsonProperty("alamat")
    private String alamat;

    // @JsonProperty("role")
    // private RoleModel role;

    @JsonProperty("roleId")
    private Long idRole;



    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelepon() {
        return this.noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getTempatLahir() {
        return this.tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return this.tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Long getIdRole() {
        return this.idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }
    

}
