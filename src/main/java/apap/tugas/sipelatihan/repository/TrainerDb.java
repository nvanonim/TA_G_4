package apap.tugas.sipelatihan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.sipelatihan.model.TrainerModel;

@Repository
public interface TrainerDb extends JpaRepository<TrainerModel, Long> {
    Optional<TrainerModel> findById(Long id);
    Optional<TrainerModel> findByNoKtp(String noKtp);
    // Optional<TrainerModel> findByNo_telepon(String no_telepon);
}
