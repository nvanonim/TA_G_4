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



}
