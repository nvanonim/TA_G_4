package apap.tugas.sipelatihan.restservice;

import apap.tugas.sipelatihan.model.PesertaPelatihanModel;
import java.util.List;
import java.util.Map;

public interface PesertaPelatihanRestService {
    List<Map<String, Object>> retrieveListPesertaPelatihan(String namaPeserta);



}
