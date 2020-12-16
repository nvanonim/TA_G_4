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
        Optional<PelatihanModel> optTargetPelatihan = pelatihanDb.findById(pelatihan.getId());
        if (!optTargetPelatihan.isPresent()) {
            throw new Exception("ID pelatihan tidak ada");
        }
        PelatihanModel target = optTargetPelatihan.get();
        if (peserta.size() + target.getListPesertaPelatihan().size() > target.getKapasitas()) {
            throw new Exception("Melebihi kapasitas");
        }

        List<PesertaPelatihanModel> tambah = new ArrayList<>();
        for (PesertaPelatihanModel pesertaPelatihanModel : peserta) {
            if (pesertaPelatihanDb.findByPelatihanAndPeserta(target, pesertaPelatihanModel.getPeserta()).size() > 0) {
                continue;
            }
            pesertaPelatihanModel.setPelatihan(target);
            pesertaPelatihanModel.setNoPeserta(target.getId() + "-"
                    + pesertaPelatihanModel.getPeserta().getDepartemen().toUpperCase().substring(0, 2) + "-"
                    + pesertaPelatihanModel.getPeserta().getId());
            pesertaPelatihanDb.save(pesertaPelatihanModel);
            tambah.add(pesertaPelatihanModel);
        }
        return tambah;
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
