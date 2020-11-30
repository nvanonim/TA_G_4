package apap.tugas.sipelatihan.service;

import java.util.List;

import apap.tugas.sipelatihan.model.TrainerModel;

public interface TrainerService {
    void addTrainer(TrainerModel trainer);

    List<TrainerModel> getTrainerList();

    Boolean validateKTP(TrainerModel trainer);
}
