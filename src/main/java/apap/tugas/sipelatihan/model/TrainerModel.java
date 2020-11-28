package apap.tugas.sipelatihan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "trainer")
public class TrainerModel implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 16)
    @Column(name = "no_ktp", nullable = false, unique = true)
    private String no_ktp;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama_trainer", nullable = false)
    private String nama_trainer;

    @NotNull
    @Size(max = 200)
    @Column(name = "no_telepon", nullable = false)
    private String no_telepon;

    @NotNull
    @Size(max = 100)
    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PelatihanModel> listPelatihan;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo_ktp() {
        return this.no_ktp;
    }

    public void setNo_ktp(String no_ktp) {
        this.no_ktp = no_ktp;
    }

    public String getNama_trainer() {
        return this.nama_trainer;
    }

    public void setNama_trainer(String nama_trainer) {
        this.nama_trainer = nama_trainer;
    }

    public String getNo_telepon() {
        return this.no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PelatihanModel> getListPelatihan() {
        return this.listPelatihan;
    }

    public void setListPelatihan(List<PelatihanModel> listPelatihan) {
        this.listPelatihan = listPelatihan;
    }
    
}
