package apap.tugas.sipelatihan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipelatihan.model.JenisPelatihanModel;
import apap.tugas.sipelatihan.repository.JenisPelatihanDb;

@Service
@Transactional
public class JenisPelatihanServiceImpl implements JenisPelatihanService {
    @Autowired
    JenisPelatihanDb jenisPelatihanDb;

    public List<JenisPelatihanModel> getJenisPelatihanList() {
        return jenisPelatihanDb.findAll();
    }
}
