package apap.tugas.sipelatihan.service;

import java.util.List;

import apap.tugas.sipelatihan.model.TrainerModel;

public interface TrainerService {
    void addTrainer(TrainerModel trainer);

    TrainerModel getTrainerByNomorKTP(String no_ktp);

    List<TrainerModel> getTrainerList();

    Boolean validateKTP(String no_ktp);
}
