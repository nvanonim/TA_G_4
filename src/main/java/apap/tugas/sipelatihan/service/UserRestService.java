package apap.tugas.sipelatihan.service;

import apap.tugas.sipelatihan.rest.BaseResponse;
import apap.tugas.sipelatihan.rest.PegawaiDetail;
import apap.tugas.sipelatihan.rest.UserDetail;
import reactor.core.publisher.Mono;

public interface UserRestService {
    PegawaiDetail addPegawai(PegawaiDetail pegawai);

    PegawaiDetail updatePegawai(PegawaiDetail pegawai);

    PegawaiDetail getPegawaiByUsername(String username);

    BaseResponse<UserDetail> getPegawai(String username);

    String getPegawaiString(String username);
}
