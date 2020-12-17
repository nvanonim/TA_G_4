package apap.tugas.sipelatihan.restservice;

import java.util.List;

import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.model.PesertaPelatihanModel;

public interface KaryawanService {

    List<PesertaPelatihanModel> imporKaryawanToPelatihan(PelatihanModel pelatihan) throws Exception;

}
