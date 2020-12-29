package apap.tugas.sipelatihan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.model.PesertaModel;
import apap.tugas.sipelatihan.model.PesertaPelatihanModel;
import apap.tugas.sipelatihan.repository.PelatihanDb;
import apap.tugas.sipelatihan.repository.PesertaDb;
import apap.tugas.sipelatihan.repository.PesertaPelatihanDb;

@Service
@Transactional
public class PesertaServiceImpl implements PesertaService {

    @Autowired
    PesertaDb pesertaDb;

    @Autowired
    PelatihanDb pelatihanDb;

    @Autowired
    PesertaPelatihanDb pesertaPelatihanDb;

    @Override
    public List<PesertaPelatihanModel> assignManyPesertaToPelatihan(List<PesertaPelatihanModel> peserta,
            PelatihanModel pelatihan) throws Exception {

        PelatihanModel target = checkIfPelatihanValidCanAssignedManyPeserta(pelatihan, peserta);

        List<PesertaPelatihanModel> tambah = new ArrayList<>();
        for (PesertaPelatihanModel pesertaPelatihanModel : peserta) {
            if (pesertaPelatihanDb.findByPelatihanAndPeserta(target, pesertaPelatihanModel.getPeserta()).size() > 0) {
                continue;
            }
            assignPelatihanToPesertaPelatihan(pelatihan, pesertaPelatihanModel);
            tambah.add(pesertaPelatihanModel);
        }
        return tambah;
    }

    public PesertaPelatihanModel assignPelatihanToPesertaPelatihan(PelatihanModel pelatihan,
            PesertaPelatihanModel pesertaPelatihan) {
        pesertaPelatihan.setPelatihan(pelatihan);
        pesertaPelatihan.setNoPeserta(
                pelatihan.getId() + "-" + pesertaPelatihan.getPeserta().getDepartemen().toUpperCase().substring(0, 2)
                        + "-" + pesertaPelatihan.getPeserta().getId());
        pesertaPelatihanDb.save(pesertaPelatihan);
        return pesertaPelatihan;
    }

    private PelatihanModel checkIfPelatihanValidCanAssignedManyPeserta(PelatihanModel pelatihan,
            List<PesertaPelatihanModel> peserta) throws Exception {
        Optional<PelatihanModel> optTargetPelatihan = pelatihanDb.findById(pelatihan.getId());
        if (!optTargetPelatihan.isPresent()) {
            throw new Exception("ID pelatihan tidak ada");
        }
        if (peserta.size() + optTargetPelatihan.get().getListPesertaPelatihan().size() > optTargetPelatihan.get()
                .getKapasitas()) {
            throw new Exception("Melebihi kapasitas");
        }

        return optTargetPelatihan.get();
    }

    @Override
    public PesertaModel inputValidation(PesertaModel peserta) {
        if (peserta.getAlamat().length() > 100 || peserta.getDepartemen().length() > 100
                || peserta.getNoTelepon().length() > 20 || peserta.getNamaPeserta().length() > 100) {
            throw new IllegalArgumentException("Input tidak valid");
        }
        try {
            Integer.parseInt(peserta.getNoTelepon());
        } catch (Exception e) {
            throw new IllegalArgumentException("Nomor telepon harus angka!");
        }
        return peserta;
    }

    @Override
    public PesertaModel create(PesertaModel peserta) {
        inputValidation(peserta);
        return pesertaDb.save(peserta);
    }

    @Override
    public List<PesertaModel> getAllPeserta() {
        return pesertaDb.findAll();
    }

}
