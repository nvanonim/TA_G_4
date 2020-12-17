package apap.tugas.sipelatihan.service;

import javax.transaction.Transactional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
        this.webClient = webClientBuilder
                        .baseUrl(Setting.pegawaiUrl)
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build();
    }
    
    // @Override
    // public Mono<PegawaiDetail> addPegawai(PegawaiDetail pegawai) {
    //     System.out.println("anak anjing");
    //     return this.webClient.post()
    //             .uri("/api/v1/pegawai")
    //             // .syncBody(pegawai)
    //             .body(Mono.just(pegawai), PegawaiDetail.class)
    //             .retrieve()
    //             .bodyToMono(PegawaiDetail.class);
    // }

    @Override
    public PegawaiDetail addPegawai(PegawaiDetail pegawai) {
        PegawaiDetail post = this.webClient.post()
                            .uri("/api/v1/pegawai")
                            .body(Mono.just(pegawai), PegawaiDetail.class)
                            .retrieve().bodyToMono(PegawaiDetail.class).block();
        return post;
    }

    // @Override
    // public Mono<PegawaiDetail> updatePegawai(PegawaiDetail pegawai) {
    //     return this.webClient.put()
    //             .uri("/api/v1/pegawai/" + pegawai.getUsername())
    //             .retrieve()
    //             .bodyToMono(PegawaiDetail.class);
    // }

    @Override
    public PegawaiDetail updatePegawai(PegawaiDetail pegawai) {
        PegawaiDetail update = this.webClient.put()
                            .uri("/api/v1/pegawai/" + pegawai.getUsername())
                            .body(Mono.just(pegawai), PegawaiDetail.class)
                            .retrieve().bodyToMono(PegawaiDetail.class).block();
        return update;
    }

    // @Override
    // public Mono<PegawaiDetail> getPegawaiByUsername(String username) {
    //     return this.webClient.get()
    //             .uri("/api/v1/pegawai/" + username)
    //             .retrieve()
    //             .bodyToMono(PegawaiDetail.class);
    // }

    @Override
    public PegawaiDetail getPegawaiByUsername(String username) {
        PegawaiDetail user = this.webClient.get()
                            .uri("/api/v1/pegawai/" + username)
                            .retrieve().bodyToMono(PegawaiDetail.class).block();
        return user;
    }

    @Override
    public String getPegawaiString(String username) {
        return this.webClient.get().uri("/api/v1/pegawai/"+username).retrieve().bodyToMono(String.class).block();
    }

    // @Override
    // public BaseResponse<PegawaiDetail> getPegawai(String username) {
    //     BaseResponse<PegawaiDetail> response;
    //     response = new BaseResponse<>();
    //     return this.webClient.get().uri("/api/v1/pegawai/"+username).retrieve().bodyToMono(PegawaiDetail.class);
    // }
    
}
