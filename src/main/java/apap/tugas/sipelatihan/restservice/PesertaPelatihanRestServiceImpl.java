package apap.tugas.sipelatihan.restservice;

import javax.transaction.Transactional;

import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.model.PesertaModel;
import apap.tugas.sipelatihan.repository.PelatihanDb;
import apap.tugas.sipelatihan.repository.PesertaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.sipelatihan.model.PesertaPelatihanModel;
import apap.tugas.sipelatihan.repository.PesertaPelatihanDb;

import java.util.*;

@Service
@Transactional
public class PesertaPelatihanRestServiceImpl implements PesertaPelatihanRestService {
    @Autowired
    private PesertaPelatihanDb pesertaPelatihanDb;

    @Autowired
    private PesertaDb pesertaDb;

    @Autowired
    private PelatihanDb pelatihanDb;


    @Override
    public List<Map<String, Object>> retrieveListPesertaPelatihan(String namaPeserta){
        List<Map<String, Object>> result = new ArrayList<>();
        Long  id_peserta = pesertaDb.findByNamaPeserta(namaPeserta).getId();
        System.out.println(id_peserta);
        Optional <PesertaModel> peserta = pesertaDb.findById(id_peserta);
        List<PesertaPelatihanModel> List = peserta.get().getListPesertaPelatihan();
        for(PesertaPelatihanModel l : List ){
            PelatihanModel pelatihan = l.getPelatihan();
            System.out.println(pelatihan);
            Map<String,Object> map = new HashMap<>();
            map.put("id", pelatihan.getId());
            map.put("nama_pelatihan", pelatihan.getNamaPelatihan());
            map.put("tanggal_mulai", pelatihan.getTanggal_mulai());
            map.put("tanggal_selesai", pelatihan.getTanggal_selesai());
            result.add (map);
        }
        return result;
    }


}

