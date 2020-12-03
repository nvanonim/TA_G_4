package apap.tugas.sipelatihan.controller;

import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.model.PesertaModel;
import apap.tugas.sipelatihan.model.PesertaPelatihanModel;
import apap.tugas.sipelatihan.service.PelatihanService;
//import apap.tugas.sipelatihan.service.PesertaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class PelatihanController {
    @Autowired
    private PelatihanService pelatihanService;
//
//    @Autowired
//    private PesertaService pesertaService;

    @GetMapping("/pelatihan/view/{id}")
    public String viewDetailPelatihan(
            @PathVariable Long id, Model model) {

        PelatihanModel pelatihan = pelatihanService.getPelatihanById(id);
        List<PesertaPelatihanModel> listPeserta = pelatihan.getListPesertaPelatihan();
        if (!(listPeserta.isEmpty())){
            model.addAttribute("title", "Daftar Peserta");
            model.addAttribute("status", true);


        }else{
            model.addAttribute("status", false);

        }
        model.addAttribute("pelatihan", pelatihan);
        model.addAttribute("listPeserta", listPeserta);


        return "/pelatihan/view-pelatihan";


    }


}
