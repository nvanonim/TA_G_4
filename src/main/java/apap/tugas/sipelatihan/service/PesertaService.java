package apap.tugas.sipelatihan.service;

import java.util.List;

import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.model.PesertaModel;
import apap.tugas.sipelatihan.model.PesertaPelatihanModel;

public interface PesertaService {

    public PesertaModel create(PesertaModel peserta);

    public PesertaModel inputValidation(PesertaModel peserta);

    public List<PesertaPelatihanModel> assignManyPesertaToPelatihan(List<PesertaPelatihanModel> peserta,
            PelatihanModel pelatihan) throws Exception;

    public List<PesertaModel> getAllPeserta();

    public PesertaPelatihanModel assignPelatihanToPesertaPelatihan(PelatihanModel pelatihan,
            PesertaPelatihanModel pesertaPelatihan);
}
