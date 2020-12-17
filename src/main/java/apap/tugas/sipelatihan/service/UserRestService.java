package apap.tugas.sipelatihan.service;

import apap.tugas.sipelatihan.rest.PegawaiDetail;
import reactor.core.publisher.Mono;

public interface UserRestService {
    PegawaiDetail addPegawai(PegawaiDetail pegawai);

    PegawaiDetail updatePegawai(PegawaiDetail pegawai);

    PegawaiDetail getPegawaiByUsername(String username);
}
