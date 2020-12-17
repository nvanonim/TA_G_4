package apap.tugas.sipelatihan.restservice;

import apap.tugas.sipelatihan.rest.LaporanDetail;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface LaporanRestService {
    String postLaporan(LaporanDetail laporan);

}
