package apap.tugas.sipelatihan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.sipelatihan.model.PesertaPelatihanModel;

@Repository
public interface PesertaPelatihanDb extends JpaRepository<PesertaPelatihanModel, Long> {
    
}
