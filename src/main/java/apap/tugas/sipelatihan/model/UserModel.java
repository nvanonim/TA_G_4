package apap.tugas.sipelatihan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class UserModel implements Serializable {    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Size(max = 50)
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull
    @Lob
    @Column(name = "password", nullable = false, unique = true)
    private String password;

    // @NotNull
    // @Transient
    // @Size(max = 200)
    // private  String nama;

    // @NotNull
    // @Transient
    // @Size(max = 200)
    // private  String noTelepon;

    // @NotNull
    // @Transient
    // @Size(max = 200)
    // private  String tempatLahir;

    // @NotNull
    // @Transient
    // // @Temporal(TemporalType.DATE)
    // @Temporal(TemporalType.TIMESTAMP)
    // // @DateTimeFormat(pattern = "yyyy-MM-dd")
    // @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    // private  Date tanggalLahir;

    // @NotNull
    // // @Transient
    // @Size(max = 200)
    // private String alamat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRole", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RoleModel role;

    @OneToMany(mappedBy = "penyetuju", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PelatihanModel> listPelatihanDisetujui;

    @OneToMany(mappedBy = "pengaju", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PelatihanModel> listPelatihanDiajui;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public String getNama() {
    //     return this.nama;
    // }

    // public void setNama(String nama) {
    //     this.nama = nama;
    // }

    // public String getNoTelepon() {
    //     return this.noTelepon;
    // }

    // public void setNoTelepon(String noTelepon) {
    //     this.noTelepon = noTelepon;
    // }

    // public String getTempatLahir() {
    //     return this.tempatLahir;
    // }

    // public void setTempatLahir(String tempatLahir) {
    //     this.tempatLahir = tempatLahir;
    // }

    // public Date getTanggalLahir() {
    //     return this.tanggalLahir;
    // }

    // public void setTanggalLahir(Date tanggalLahir) {
    //     this.tanggalLahir = tanggalLahir;
    // }

    // public String getAlamat() {
    //     return this.alamat;
    // }

    // public void setAlamat(String alamat) {
    //     this.alamat = alamat;
    // }

    public RoleModel getRole() {
        return this.role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public List<PelatihanModel> getListPelatihanDisetujui() {
        return this.listPelatihanDisetujui;
    }

    public void setListPelatihanDisetujui(List<PelatihanModel> listPelatihanDisetujui) {
        this.listPelatihanDisetujui = listPelatihanDisetujui;
    }

    public List<PelatihanModel> getListPelatihanDiajui() {
        return this.listPelatihanDiajui;
    }

    public void setListPelatihanDiajui(List<PelatihanModel> listPelatihanDiajui) {
        this.listPelatihanDiajui = listPelatihanDiajui;
    }

}
