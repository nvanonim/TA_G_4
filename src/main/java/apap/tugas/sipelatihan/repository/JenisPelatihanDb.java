package apap.tugas.sipelatihan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.sipelatihan.model.JenisPelatihanModel;

@Repository
public interface JenisPelatihanDb extends JpaRepository<JenisPelatihanModel, Long> {
    
}
