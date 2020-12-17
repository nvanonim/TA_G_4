package apap.tugas.sipelatihan.repository;

import java.util.Optional;

import apap.tugas.sipelatihan.model.PelatihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.sipelatihan.model.PesertaModel;

@Repository
public interface PesertaDb extends JpaRepository<PesertaModel, Long> {
    PesertaModel findByNamaPeserta(String namaPeserta);
    Optional <PesertaModel> findById(Long id);
    
}
