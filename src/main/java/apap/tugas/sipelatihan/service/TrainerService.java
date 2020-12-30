package apap.tugas.sipelatihan.service;

import java.util.List;

import apap.tugas.sipelatihan.model.TrainerModel;

public interface TrainerService {
    void addTrainer(TrainerModel trainer);

    void updateTrainer(TrainerModel trainer);

    void deleteTrainer(Long id);

    TrainerModel getTrainerByNomorKTP(String noKtp);

    TrainerModel getTrainerById(Long id);

    List<TrainerModel> getTrainerList();

    Boolean validateKTP(String noKtp);
}
