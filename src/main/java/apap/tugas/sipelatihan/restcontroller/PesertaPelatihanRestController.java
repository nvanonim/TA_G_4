package apap.tugas.sipelatihan.restcontroller;

import javax.validation.Valid;

import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.rest.LaporanDetail;
import apap.tugas.sipelatihan.restservice.LaporanRestService;
import apap.tugas.sipelatihan.restservice.PelatihanRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import apap.tugas.sipelatihan.rest.BaseResponse;

import apap.tugas.sipelatihan.model.PesertaPelatihanModel;
import apap.tugas.sipelatihan.restservice.PesertaPelatihanRestService;

import apap.tugas.sipelatihan.model.PesertaModel;
import apap.tugas.sipelatihan.restservice.PesertaPelatihanRestService;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class PesertaPelatihanRestController {

    @Autowired
    private PesertaPelatihanRestService pesertaPelatihanRestService;

    @Autowired
    private LaporanRestService laporanRestService;

    @GetMapping(value = "/pesertapelatihans/{namaPeserta}")
    private List<Map<String, Object>>retrievePesertaPelatihan(@PathVariable(value = "namaPeserta") String namaPeserta){
        try {
            return pesertaPelatihanRestService.retrieveListPesertaPelatihan(namaPeserta);
        }catch (NoSuchElementException e){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
                );
            }

    }


    @PostMapping(value="/pelatihan-bonus")
    private String postLaporan(@Valid @RequestBody LaporanDetail laporan){
        try{

        return laporanRestService.postLaporan(laporan);
    }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Tidak ada peserta"
            );
        }

        }
}
