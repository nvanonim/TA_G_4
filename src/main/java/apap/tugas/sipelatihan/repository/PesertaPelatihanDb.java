package apap.tugas.sipelatihan.repository;

import java.util.List;
import java.util.Optional;

import apap.tugas.sipelatihan.model.PelatihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.model.PesertaModel;
import apap.tugas.sipelatihan.model.PesertaPelatihanModel;

@Repository
public interface PesertaPelatihanDb extends JpaRepository<PesertaPelatihanModel, Long> {
    List<PesertaPelatihanModel> findByPelatihanAndPeserta(PelatihanModel pelatihan, PesertaModel peserta);
    Optional<PesertaPelatihanModel> findById(Long Id);
    List<PesertaPelatihanModel> findByPeserta(Long id_peserta);
}
