package apap.tugas.sipelatihan.restcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import apap.tugas.sipelatihan.model.JenisPelatihanModel;
import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.model.TrainerModel;
import apap.tugas.sipelatihan.rest.BaseResponse;
import apap.tugas.sipelatihan.restservice.PelatihanRestService;
import apap.tugas.sipelatihan.service.JenisPelatihanService;
import apap.tugas.sipelatihan.service.PelatihanService;
import apap.tugas.sipelatihan.service.TrainerService;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class PelatihanRestController {

    @Autowired
    private PelatihanRestService pelatihanRestService;

    @PostMapping("/pelatihan")
    private BaseResponse<PelatihanModel> createPelatihan(@Valid @RequestBody PelatihanModel pelatihanModel,
            BindingResult bindingResult,
            BaseResponse<PelatihanModel> response
    ) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }
        else {
            if (pelatihanModel.getTanggal_mulai().after(pelatihanModel.getTanggal_selesai())) {
                response.setStatus(400);
                response.setMessage("Tanggal selesai tidak boleh mendahului tanggal mulai");
                response.setResult(null);

            } else {
                if (pelatihanModel.getWaktu_mulai().after(pelatihanModel.getWaktu_selesai())) {
                    response.setStatus(400);
                    response.setMessage("Waktu selesai tidak boleh sama atau mendahului waktu mulai");
                    response.setResult(null);

                } else if (pelatihanModel.getTanggal_mulai().equals(pelatihanModel.getTanggal_selesai())
                        && pelatihanModel.getWaktu_mulai().equals(pelatihanModel.getWaktu_selesai())) {
                    response.setStatus(400);
                    response.setMessage("Waktu selesai tidak boleh sama atau mendahului waktu mulai");
                    response.setResult(null);

                } else {
                    // Onboarding selalu id 1
                    // Trainer selalu ke id 1
                    long idDefault = 1;
                    JenisPelatihanModel jenisPelatihan = new JenisPelatihanModel();
                    jenisPelatihan.setId(idDefault);
                    TrainerModel trainer = new TrainerModel();
                    trainer.setId(idDefault);

                    pelatihanModel.setJenisPelatihan(jenisPelatihan);
                    pelatihanModel.setTrainer(trainer);

                    PelatihanModel pelatihan = pelatihanRestService.createPelatihan(pelatihanModel);

                    response.setStatus(200);
                    response.setMessage("success");
                    response.setResult(pelatihan);
                    
                }
            }

            return response;
        }
    }

}
