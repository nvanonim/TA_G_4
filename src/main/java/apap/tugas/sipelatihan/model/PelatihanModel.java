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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "tanggal_mulai", nullable = false)
    private Date tanggalMulai;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "tanggal_selesai", nullable = false)
    private Date tanggalSelesai;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "waktu_mulai", nullable = false)
    private Date waktuMulai;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "waktu_selesai", nullable = false)
    private Date waktuSelesai;

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

    public Date getTanggalMulai() {
        return this.tanggalMulai;
    }

    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public Date getTanggalSelesai() {
        return this.tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public Date getWaktuMulai() {
        return this.waktuMulai;
    }

    public void setWaktuMulai(Date waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public Date getWaktuSelesai() {
        return this.waktuSelesai;
    }

    public void setWaktuSelesai(Date waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
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
