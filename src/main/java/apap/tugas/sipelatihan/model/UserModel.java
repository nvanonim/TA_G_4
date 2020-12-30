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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false)
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
