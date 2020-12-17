package apap.tugas.sipelatihan.controller;

import javax.validation.Valid;

import apap.tugas.sipelatihan.rest.LaporanDetail;
import apap.tugas.sipelatihan.restservice.LaporanRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.tugas.sipelatihan.model.PesertaModel;
import apap.tugas.sipelatihan.service.PesertaService;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(value = "/peserta")
public class PesertaController {

    @Autowired
    PesertaService pesertaService;

    @Autowired
    LaporanRestService laporanRestService;

    @GetMapping(value = "/tambah")
    public String tambahPesertaForm(Model model) {
        PesertaModel peserta = new PesertaModel();
        model.addAttribute("peserta", peserta);
        return "peserta/tambah-peserta";
    }

    @PostMapping(value = "/tambah")
    public String tambahPesertaSubmit(@Valid @ModelAttribute("peserta") PesertaModel peserta,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("peserta", peserta);
            return "peserta/tambah-peserta";
        }
        try {
            PesertaModel created = pesertaService.create(peserta);
            model.addAttribute("peserta", created);
            return "peserta/tambah-peserta-sukses";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error_msg", e.getMessage());
            model.addAttribute("peserta", peserta);
            return "/peserta/tambah-peserta";
        }
    }

    @GetMapping("/laporan")
    public String addLaporanFormPage(Model model){
        model.addAttribute("laporan", new LaporanDetail());
        return "peserta/form-add-laporan";
    }

    //option 2 fitur 14
    @PostMapping(value="/laporan")
    private String postLaporan(@ModelAttribute LaporanDetail laporan, Model model){
        try {
            laporanRestService.postLaporan(laporan);
            model.addAttribute("message", "success");
        }catch (NoSuchElementException e){
            model.addAttribute("message", "error");
        }

        return "peserta/add-laporan";

    }





}
