package apap.tugas.sipelatihan.service;

import apap.tugas.sipelatihan.rest.PegawaiDetail;
import reactor.core.publisher.Mono;

public interface UserRestService {
    Mono<PegawaiDetail> addPegawai(PegawaiDetail pegawai);

    Mono<PegawaiDetail> updatePegawai(PegawaiDetail pegawai);

    Mono<PegawaiDetail> getPegawaiByUsername(String username);
}
