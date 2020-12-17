package apap.tugas.sipelatihan.restservice;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.repository.PelatihanDb;

@Service
@Transactional
public class PelatihanRestServiceImpl implements PelatihanRestService {
    @Autowired
    private PelatihanDb pelatihanDb;

    @Override
    public PelatihanModel createPelatihan(PelatihanModel pelatihan) {
        return pelatihanDb.save(pelatihan);
    }

}
