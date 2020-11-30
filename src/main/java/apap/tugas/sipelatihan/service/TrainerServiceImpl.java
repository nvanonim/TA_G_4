package apap.tugas.sipelatihan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipelatihan.model.TrainerModel;
import apap.tugas.sipelatihan.repository.TrainerDb;

@Service
@Transactional
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    TrainerDb trainerDb;

    @Override
    public void addTrainer(TrainerModel trainer) {
        trainerDb.save(trainer);
    }

    @Override
    public List<TrainerModel> getTrainerList() {
        return trainerDb.findAll();
    }

    
    
    
}
