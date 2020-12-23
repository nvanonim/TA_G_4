package apap.tugas.sipelatihan.service;

import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.model.UserModel;

import java.util.List;

public interface PelatihanService {
    PelatihanModel getPelatihanById(Long id);

    void addPelatihan(PelatihanModel pelatihan);

    List<PelatihanModel> getAll();

    List<PelatihanModel> getPelatihanFromPengguna(UserModel user);

    void deletePelatihan(Long id);

}
