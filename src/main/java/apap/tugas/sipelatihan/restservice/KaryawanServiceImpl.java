package apap.tugas.sipelatihan.restservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.model.PesertaModel;
import apap.tugas.sipelatihan.model.PesertaPelatihanModel;
import apap.tugas.sipelatihan.repository.PesertaDb;
import apap.tugas.sipelatihan.repository.PesertaPelatihanDb;
import apap.tugas.sipelatihan.rest.KaryawanBaruDto;
import apap.tugas.sipelatihan.rest.KaryawanBaruResponse;
import apap.tugas.sipelatihan.rest.Setting;
import apap.tugas.sipelatihan.service.PesertaService;

@Service
public class KaryawanServiceImpl implements KaryawanService {

    @Autowired
    private PesertaDb pesertaDb;

    @Autowired
    PesertaService pesertaService;

    @Autowired
    PesertaPelatihanDb pesertaPelatihanDb;

    private final WebClient webClient;

    public KaryawanServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.imporKaryawanUrl).build();
    }

    @Override
    public List<PesertaPelatihanModel> imporKaryawanToPelatihan(PelatihanModel pelatihan) throws Exception {
        KaryawanBaruResponse baseErr = new KaryawanBaruResponse();
        baseErr.setStatus(404);
        KaryawanBaruResponse response = this.webClient.get().uri("impor-karyawan").retrieve()
                .bodyToMono(KaryawanBaruResponse.class).onErrorReturn(baseErr).block();

        List<KaryawanBaruDto> query = response.getResult();

        List<PesertaPelatihanModel> added = new ArrayList<>();
        PesertaModel newPeserta;
        PesertaPelatihanModel newPesertaPelatihan;
        for (KaryawanBaruDto karyawan : query) {
            newPeserta = convertKaryawanToPesertaModel(karyawan);
            if (pesertaPelatihanDb.findByPelatihanAndPeserta(pelatihan, newPeserta).size() > 0) {
                continue;
            }
            newPesertaPelatihan = new PesertaPelatihanModel();
            newPesertaPelatihan.setPeserta(newPeserta);
            added.add(newPesertaPelatihan);
        }

        added = pesertaService.assignManyPesertaToPelatihan(added, pelatihan);
        return added;
    }

    private PesertaModel convertKaryawanToPesertaModel(KaryawanBaruDto karyawan) {
        PesertaModel isThereAny = pesertaDb.findByNamaPeserta(karyawan.getNama());
        if (isThereAny != null) {
            return isThereAny;
        }
        PesertaModel peserta = new PesertaModel();
        peserta.setAlamat(karyawan.getAlamat());
        peserta.setDepartemen(karyawan.getDivisi());
        peserta.setNamaPeserta(karyawan.getNama());
        peserta.setNoTelepon(karyawan.getNoTelepon());
        pesertaDb.save(peserta);
        return peserta;
    }

}
