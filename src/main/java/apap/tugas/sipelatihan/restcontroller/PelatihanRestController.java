package apap.tugas.sipelatihan.restcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import apap.tugas.sipelatihan.model.PesertaPelatihanModel;
import apap.tugas.sipelatihan.restservice.PesertaPelatihanRestService;
import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.restservice.PelatihanRestService;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PelatihanRestController {

    @Autowired
    private PelatihanRestService pelatihanRestService;

    @Autowired
    private PesertaPelatihanRestService pesertaPelatihanRestService;
    
    @PostMapping("/pelatihan")
    private PelatihanModel createPelatihan(@Valid @RequestBody PelatihanModel pelatihan,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }
        else {
            return pelatihanRestService.createPelatihan(pelatihan);
        }
    }

}
