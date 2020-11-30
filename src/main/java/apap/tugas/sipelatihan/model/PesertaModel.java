package apap.tugas.sipelatihan.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "peserta")
public class PesertaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "nama_peserta", nullable = false)
    private String namaPeserta;

    @NotNull
    @Size(max = 20)
    @Column(name = "no_telepon", nullable = false)
    private String noTelepon;

    @NotNull
    @Size(max = 100)
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @NotNull
    @Size(max = 100)
    @Column(name = "departemen", nullable = false)
    private String departemen;

    @OneToMany(mappedBy = "peserta")
    private List<PesertaPelatihanModel> listPesertaPelatihan;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPeserta() {
        return this.namaPeserta;
    }

    public void setNamaPeserta(String namaPeserta) {
        this.namaPeserta = namaPeserta;
    }

    public String getNoTelepon() {
        return this.noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDepartemen() {
        return this.departemen;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }

    public List<PesertaPelatihanModel> getListPesertaPelatihan() {
        return this.listPesertaPelatihan;
    }

    public void setListPesertaPelatihan(List<PesertaPelatihanModel> listPesertaPelatihan) {
        this.listPesertaPelatihan = listPesertaPelatihan;
    }

}
