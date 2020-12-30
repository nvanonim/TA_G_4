package apap.tugas.sipelatihan.service;

import java.util.List;
import java.util.NoSuchElementException;

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
    public TrainerModel getTrainerById(Long id) {
        return trainerDb.findById(id).get();
    }

    @Override
    public List<TrainerModel> getTrainerList() {
        return trainerDb.findAll();
    }

    @Override
    public void addTrainer(TrainerModel trainer) {
        trainerDb.save(trainer);
    }

    @Override
    public Boolean validateKTP(String noKtp) {
        try {
            TrainerModel temp_trainer = getTrainerByNomorKTP(noKtp);
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    @Override
    public TrainerModel getTrainerByNomorKTP(String noKtp) {
        return trainerDb.findByNoKtp(noKtp).get();
    }

    @Override
    public void updateTrainer(TrainerModel trainer) {
        trainerDb.save(trainer);

    }

    @Override
    public void deleteTrainer(Long id) {
        trainerDb.deleteById(id);
    }   
}
