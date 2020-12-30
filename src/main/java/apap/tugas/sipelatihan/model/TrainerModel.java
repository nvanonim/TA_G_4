package apap.tugas.sipelatihan.model;

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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 16)
    // @Column(name = "no_ktp", nullable = false, unique = true)
    @Column(name = "no_ktp", nullable = false)
    private String noKtp;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama_trainer", nullable = false)
    private String namaTrainer;

    @NotNull
    @Size(max = 200)
    @Column(name = "no_telepon", nullable = false)
    private String noTelepon;

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

    public String getNoKtp() {
        return this.noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getNamaTrainer() {
        return this.namaTrainer;
    }

    public void setNamaTrainer(String namaTrainer) {
        this.namaTrainer = namaTrainer;
    }

    public String getNoTelepon() {
        return this.noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
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


    public boolean isPresent() {
		return true;
	}
    
}
