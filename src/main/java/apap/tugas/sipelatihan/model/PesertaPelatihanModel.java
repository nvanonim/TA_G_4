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
@Table(name = "peserta_pelatihan")
public class PesertaPelatihanModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // id pilot
    @ManyToOne
    @JoinColumn(name = "id_peserta", referencedColumnName = "id")
    PesertaModel peserta;

    // id penerbangan
    @ManyToOne
    @JoinColumn(name = "id_pelatihan", referencedColumnName = "id")
    PelatihanModel pelatihan;

    // tanggal pilot ditugaskan ke penerbangan
    @NotNull
    @Size(max = 200)
    @Column(name = "no_peserta", nullable = false)
    private String noPeserta;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PesertaModel getPeserta() {
        return this.peserta;
    }

    public void setPeserta(PesertaModel peserta) {
        this.peserta = peserta;
    }

    public PelatihanModel getPelatihan() {
        return this.pelatihan;
    }

    public void setPelatihan(PelatihanModel pelatihan) {
        this.pelatihan = pelatihan;
    }

    public String getNoPeserta() {
        return this.noPeserta;
    }

    public void setNoPeserta(String no_peserta) {
        this.noPeserta = no_peserta;
    }

}
