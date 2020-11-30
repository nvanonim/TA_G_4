package apap.tugas.sipelatihan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.tugas.sipelatihan.model.PesertaModel;
import apap.tugas.sipelatihan.service.PesertaService;

@Controller
@RequestMapping(value = "/peserta")
public class PesertaController {

    @Autowired
    PesertaService pesertaService;

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
}
