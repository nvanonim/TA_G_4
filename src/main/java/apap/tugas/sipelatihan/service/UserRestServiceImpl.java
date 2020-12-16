package apap.tugas.sipelatihan.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugas.sipelatihan.rest.PegawaiDetail;
import apap.tugas.sipelatihan.rest.Setting;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService {
    private final WebClient webClient;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.pegawaiUrl).build();
    }
    
    @Override
    public Mono<PegawaiDetail> addPegawai(PegawaiDetail pegawai) {
        return this.webClient.post()
                .uri("/pegawai")
                .retrieve()
                .bodyToMono(PegawaiDetail.class);
    }

    @Override
    public Mono<PegawaiDetail> updatePegawai(PegawaiDetail pegawai) {
        return this.webClient.put()
                .uri("/pegawai/" + pegawai.getUsername())
                .retrieve()
                .bodyToMono(PegawaiDetail.class);
    }

    @Override
    public Mono<PegawaiDetail> getPegawaiByUsername(String username) {
        return this.webClient.get()
                .uri("/pegawai/" + username)
                .retrieve()
                .bodyToMono(PegawaiDetail.class);
    }

    // @Override
    // public Mono<PegawaiDetail> getPegawai(String username) {
    //     return this.webClient.get().uri("/api/v1/pegawai/"+username).retrieve().bodyToMono(PegawaiDetail.class);
    // }
    
}
