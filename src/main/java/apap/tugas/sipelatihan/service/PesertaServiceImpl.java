package apap.tugas.sipelatihan.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.model.PesertaModel;
import apap.tugas.sipelatihan.model.PesertaPelatihanModel;
import apap.tugas.sipelatihan.repository.PesertaDb;

@Service
@Transactional
public class PesertaServiceImpl implements PesertaService {

    @Autowired
    PesertaDb pesertaDb;

    @Override
    public List<PesertaPelatihanModel> assignManyPesertaToPelatihan(List<PesertaModel> peserta,
            PelatihanModel pelatihan) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PesertaModel inputValidation(PesertaModel peserta) {
        if (peserta.getAlamat().length() > 100 || peserta.getDepartemen().length() > 100
                || peserta.getNo_telepon().length() > 20 || peserta.getNama_peserta().length() > 100) {
            throw new IllegalArgumentException("Input tidak valid");
        }
        try {
            Integer.parseInt(peserta.getNo_telepon());
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
}
