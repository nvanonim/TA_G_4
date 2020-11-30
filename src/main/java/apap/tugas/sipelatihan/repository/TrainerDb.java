package apap.tugas.sipelatihan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.sipelatihan.model.TrainerModel;

@Repository
public interface TrainerDb extends JpaRepository<TrainerModel, Long> {
    Optional<TrainerModel> findByNo_ktp(String no_ktp);
}
