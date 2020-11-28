package apap.tugas.sipelatihan.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private String nama_peserta;

    @NotNull
    @Size(max = 20)
    @Column(name = "no_telepon", nullable = false)
    private String no_telepon;

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

    public String getNama_peserta() {
        return this.nama_peserta;
    }

    public void setNama_peserta(String nama_peserta) {
        this.nama_peserta = nama_peserta;
    }

    public String getNo_telepon() {
        return this.no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
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
