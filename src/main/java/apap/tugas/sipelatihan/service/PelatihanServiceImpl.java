package apap.tugas.sipelatihan.service;


import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.repository.PelatihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PelatihanServiceImpl implements PelatihanService{
    @Autowired
    PelatihanDb pelatihanDb;

    @Override
    public PelatihanModel getPelatihanById(Long id) {
        return pelatihanDb.findById(id).get();
    }

    @Override
    public void addPelatihan(PelatihanModel pelatihan) {
        pelatihanDb.save(pelatihan);
    }

}
