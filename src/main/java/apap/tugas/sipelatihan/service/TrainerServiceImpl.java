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

    @Override
    public Boolean validateKTP(String no_ktp) {
        TrainerModel temp_trainer = getTrainerByNomorKTP(no_ktp);
        String ktp = temp_trainer.getNo_ktp();
        if (!ktp.isEmpty() && ktp != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public TrainerModel getTrainerByNomorKTP(String no_ktp) {
        return trainerDb.findByNo_ktp(no_ktp).get();
    }
    
    
}
