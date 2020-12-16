package apap.tugas.sipelatihan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.model.UserModel;

@Repository
public interface PelatihanDb extends JpaRepository<PelatihanModel, Long> {
    Optional<PelatihanModel> findById(Long Id);

    List<PelatihanModel> findByPengaju(UserModel pengaju);
}
