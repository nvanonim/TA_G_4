package apap.tugas.sipelatihan.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pelatihan")
public class PelatihanModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama_pelatihan", nullable = false)
    private String namaPelatihan;

    @NotNull
    @Size(max = 200)
    @Column(name = "deskripsi", nullable = false)
    private String deskripsi;

    @NotNull
    @Column(name = "kapasitas", nullable = false)
    private Integer kapasitas;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_mulai", nullable = false)
    private Date tanggal_mulai;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_selesai", nullable = false)
    private Date tanggal_selesai;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "waktu_mulai", nullable = false)
    private Date waktu_mulai;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "waktu_selesai", nullable = false)
    private Date waktu_selesai;

    @NotNull
    @Column(name = "status_persetujuan")
    private Integer status_persetujuan = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_trainer", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TrainerModel trainer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jenis_pelatihan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private JenisPelatihanModel jenisPelatihan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid_penyetuju", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserModel penyetuju = null;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid_pengaju", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserModel pengaju;

    @OneToMany(mappedBy = "pelatihan")
    private List<PesertaPelatihanModel> listPesertaPelatihan;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPelatihan() {
        return this.namaPelatihan;
    }

    public void setNamaPelatihan(String namaPelatihan) {
        this.namaPelatihan = namaPelatihan;
    }

    public String getDeskripsi() {
        return this.deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getKapasitas() {
        return this.kapasitas;
    }

    public void setKapasitas(Integer kapasitas) {
        this.kapasitas = kapasitas;
    }

    public Date getTanggal_mulai() {
        return this.tanggal_mulai;
    }

    public void setTanggal_mulai(Date tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    public Date getTanggal_selesai() {
        return this.tanggal_selesai;
    }

    public void setTanggal_selesai(Date tanggal_selesai) {
        this.tanggal_selesai = tanggal_selesai;
    }

    public Date getWaktu_mulai() {
        return this.waktu_mulai;
    }

    public void setWaktu_mulai(Date waktu_mulai) {
        this.waktu_mulai = waktu_mulai;
    }

    public Date getWaktu_selesai() {
        return this.waktu_selesai;
    }

    public void setWaktu_selesai(Date waktu_selesai) {
        this.waktu_selesai = waktu_selesai;
    }

    public Integer getStatus_persetujuan() {
        return this.status_persetujuan;
    }

    public void setStatus_persetujuan(Integer status_persetujuan) {
        this.status_persetujuan = status_persetujuan;
    }

    public TrainerModel getTrainer() {
        return this.trainer;
    }

    public void setTrainer(TrainerModel trainer) {
        this.trainer = trainer;
    }

    public JenisPelatihanModel getJenisPelatihan() {
        return this.jenisPelatihan;
    }

    public void setJenisPelatihan(JenisPelatihanModel jenisPelatihan) {
        this.jenisPelatihan = jenisPelatihan;
    }

    public UserModel getPenyetuju() {
        return this.penyetuju;
    }

    public void setPenyetuju(UserModel penyetuju) {
        this.penyetuju = penyetuju;
    }

    public UserModel getPengaju() {
        return this.pengaju;
    }

    public void setPengaju(UserModel pengaju) {
        this.pengaju = pengaju;
    }

    public List<PesertaPelatihanModel> getListPesertaPelatihan() {
        return this.listPesertaPelatihan;
    }

    public void setListPesertaPelatihan(List<PesertaPelatihanModel> listPesertaPelatihan) {
        this.listPesertaPelatihan = listPesertaPelatihan;
    }

}
