package apap.tugas.sipelatihan.controller;

import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.model.PesertaModel;
import apap.tugas.sipelatihan.model.PesertaPelatihanModel;
import apap.tugas.sipelatihan.model.UserModel;
import apap.tugas.sipelatihan.service.UserService;
import apap.tugas.sipelatihan.service.JenisPelatihanService;
import apap.tugas.sipelatihan.service.PelatihanService;
//import apap.tugas.sipelatihan.service.PesertaService;
import apap.tugas.sipelatihan.service.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(value = "/pelatihan")
public class PelatihanController {
    @Autowired
    private PelatihanService pelatihanService;

    @Autowired
    private JenisPelatihanService jenisPelatihanService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private UserService userService;
//
//    @Autowired
//    private PesertaService pesertaService;

    @GetMapping("/view/{id}")
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

    @GetMapping("/add")
    public String addPelatihan(Model model) {
        model.addAttribute("pelatihan", new PelatihanModel());
        model.addAttribute("listJenisPelatihan", jenisPelatihanService.getJenisPelatihanList());
        model.addAttribute("listTrainer", trainerService.getTrainerList());

        return "pelatihan/add-pelatihan";
    }

    @PostMapping("/add")
    public String addPelatihanSubmit(@ModelAttribute("pelatihan") PelatihanModel pelatihan,
            Authentication auth,
            Model model
        ) {

        System.out.println("-------------------------------------------------------------");
        System.out.println(pelatihan.getWaktu_mulai());
        System.out.println(pelatihan.getWaktu_selesai());
        System.out.println("-------------------------------------------------------------");

        if (pelatihan.getTanggal_mulai().after(pelatihan.getTanggal_selesai())) {
            return "redirect:/pelatihan/add?errort";
        }
        else {
            if(pelatihan.getWaktu_mulai().after(pelatihan.getWaktu_selesai()) || pelatihan.getWaktu_mulai()
                    .equals(pelatihan.getWaktu_selesai())) {
                return "redirect:/pelatihan/add?errorw";
            }
            else {
                UserModel pengaju = userService.getUserByUsername(auth.getName());
                pelatihan.setPengaju(pengaju);
                pelatihanService.addPelatihan(pelatihan);
            }
        }

        return "redirect:/pelatihan/add?success";
    }

}