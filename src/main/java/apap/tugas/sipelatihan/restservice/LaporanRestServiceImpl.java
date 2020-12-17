package apap.tugas.sipelatihan.restservice;

import apap.tugas.sipelatihan.rest.LaporanDetail;
import apap.tugas.sipelatihan.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class LaporanRestServiceImpl implements LaporanRestService {
    private final WebClient webClient;

    public LaporanRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.laporanUrl).build();
    }



    @Override
    public String postLaporan(LaporanDetail laporan) {
        return this.webClient.post().uri("/api/v1/pelatihan-bonus")
                .header("Content-Type", "application/json")
                .body(Mono.just(laporan), LaporanDetail.class)
                .retrieve()
                .bodyToMono(String.class).block();
    }
}
