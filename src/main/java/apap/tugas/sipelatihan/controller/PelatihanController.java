package apap.tugas.sipelatihan.controller;

import apap.tugas.sipelatihan.model.PelatihanModel;
import apap.tugas.sipelatihan.model.PesertaModel;
import apap.tugas.sipelatihan.model.PesertaPelatihanModel;
import apap.tugas.sipelatihan.model.UserModel;
import apap.tugas.sipelatihan.rest.KaryawanBaruResponse;
import apap.tugas.sipelatihan.restservice.KaryawanService;
import apap.tugas.sipelatihan.service.UserService;
import apap.tugas.sipelatihan.service.JenisPelatihanService;
import apap.tugas.sipelatihan.service.PelatihanService;
import apap.tugas.sipelatihan.service.PesertaService;
//import apap.tugas.sipelatihan.service.PesertaService;
import apap.tugas.sipelatihan.service.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
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

    @Autowired
    private PesertaService pesertaService;

    @Autowired
    private KaryawanService karyawanService;

    @GetMapping({ "/", "" })
    public String index(Authentication auth, Model model) {
        UserModel user = userService.getUserByUsername(auth.getName());
        List<PelatihanModel> listPelatihan;
        if (user.getRole().getId() == 1 || user.getRole().getId() == 2) {
            listPelatihan = pelatihanService.getAll();
        } else {
            listPelatihan = pelatihanService.getPelatihanFromPengguna(user);
        }
        model.addAttribute("listPelatihan", listPelatihan);
        return "pelatihan/index";
    }

    @GetMapping("/{id}/add/peserta")
    public String formTambahPesertaPelatihan(@PathVariable Long id, Model model) {
        PelatihanModel container = pelatihanService.getPelatihanById(id);
        container.setListPesertaPelatihan(new ArrayList<>());
        container.getListPesertaPelatihan().add(new PesertaPelatihanModel());
        model.addAttribute("pelatihan", container);
        List<PesertaModel> listPeserta = pesertaService.getAllPeserta();
        model.addAttribute("listPeserta", listPeserta);
        return "pelatihan/add-peserta-pelatihan";
    }

    @PostMapping(value = "/{id}/add/peserta", params = "add-row")
    public String formTambahPesertaPelatihanAddRow(@PathVariable Long id, @ModelAttribute PelatihanModel pelatihan,
            Model model) {
        pelatihan.getListPesertaPelatihan().add(new PesertaPelatihanModel());
        model.addAttribute("pelatihan", pelatihan);
        List<PesertaModel> listPeserta = pesertaService.getAllPeserta();
        model.addAttribute("listPeserta", listPeserta);
        return "pelatihan/add-peserta-pelatihan";
    }

    @PostMapping(value = "/{id}/add/peserta", params = "delete-row")
    public String formTambahPesertaPelatihanDeleteRow(@PathVariable Long id,
            @ModelAttribute("pelatihan") PelatihanModel pelatihan, @RequestParam("delete-row") int value, Model model) {
        pelatihan.getListPesertaPelatihan().remove(value);
        model.addAttribute("pelatihan", pelatihan);
        List<PesertaModel> listPeserta = pesertaService.getAllPeserta();
        model.addAttribute("listPeserta", listPeserta);
        return "pelatihan/add-peserta-pelatihan";
    }

    @PostMapping(value = "/{id}/add/peserta")
    public String formTambahPesertaPelatihanDeleteRow(@PathVariable Long id,
            @ModelAttribute("pelatihan") PelatihanModel pelatihan, Model model) {
        try {
            List<PesertaPelatihanModel> pesertaPelatihan = pesertaService
                    .assignManyPesertaToPelatihan(pelatihan.getListPesertaPelatihan(), pelatihan);

            model.addAttribute("pesertaPelatihan", pesertaPelatihan);
            model.addAttribute("pelatihan", pelatihan);
        } catch (Exception e) {
            model.addAttribute("error_msg", e.getMessage());
            model.addAttribute("pelatihan", pelatihan);
            model.addAttribute("listPeserta", pesertaService.getAllPeserta());
            return "pelatihan/add-peserta-pelatihan";
        }
        return "pelatihan/success-add-peserta";
    }

    @GetMapping("/{id}/impor")
    public RedirectView imporKaryawan(@PathVariable Long id, RedirectAttributes rattrs) {
        PelatihanModel pelatihan = pelatihanService.getPelatihanById(id);
        try {
            List<PesertaPelatihanModel> added = karyawanService.imporKaryawanToPelatihan(pelatihan);
            rattrs.addFlashAttribute("success_msg", "Berhasil mengimpor " + added.size() + " karyawan!");
        } catch (Exception e) {
            rattrs.addFlashAttribute("error_msg", "Tidak dapat mengimpor, cek kembali kuota peserta!");
        }
        // return "redirect:/pelatihan/view/" + id;
        return new RedirectView("/pelatihan/view/" + id, true);
    }

    @GetMapping("/view/{id}")
    public String viewDetailPelatihan(@PathVariable Long id, Model model) {

        PelatihanModel pelatihan = pelatihanService.getPelatihanById(id);
        List<PesertaPelatihanModel> listPeserta = pelatihan.getListPesertaPelatihan();
        if (!(listPeserta.isEmpty())) {
            model.addAttribute("title", "Daftar Peserta");
            model.addAttribute("status", true);

        } else {
            model.addAttribute("status", false);

        }
        model.addAttribute("pelatihan", pelatihan);
        model.addAttribute("listPeserta", listPeserta);

        return "pelatihan/view-pelatihan";
    }

    @PostMapping(value = "/view/{id}", params = "persetujuan")
    public String viewDetailPelatihanPersetujuan(@PathVariable Long id, @RequestParam("persetujuan") int value,
            Authentication auth, Model model) {
        PelatihanModel pelatihan = pelatihanService.getPelatihanById(id);
        pelatihan.setStatus_persetujuan(value);
        UserModel penyetuju = userService.getUserByUsername(auth.getName());
        pelatihan.setPenyetuju(penyetuju);
        pelatihanService.addPelatihan(pelatihan);
        return "redirect:/pelatihan/view/" + id;
    }

    @GetMapping("/add")
    public String addPelatihan(Model model) {
        model.addAttribute("pelatihan", new PelatihanModel());
        model.addAttribute("listJenisPelatihan", jenisPelatihanService.getJenisPelatihanList());
        model.addAttribute("listTrainer", trainerService.getTrainerList());

        return "pelatihan/add-pelatihan";
    }

    @PostMapping("/add")
    public String addPelatihanSubmit(@ModelAttribute("pelatihan") PelatihanModel pelatihan, Authentication auth,
            Model model) {

        if (pelatihan.getTanggal_mulai().after(pelatihan.getTanggal_selesai())) {
            return "redirect:/pelatihan/add?errort";
        } else {
            if (pelatihan.getWaktu_mulai().after(pelatihan.getWaktu_selesai())) {
                return "redirect:/pelatihan/add?errorw";
            } else if (pelatihan.getTanggal_mulai().equals(pelatihan.getTanggal_selesai()) && pelatihan.getWaktu_mulai()
                    .equals(pelatihan.getWaktu_selesai())) {
                return "redirect:/pelatihan/add?errorw";
            } else {
                UserModel pengaju = userService.getUserByUsername(auth.getName());
                pelatihan.setPengaju(pengaju);
                pelatihanService.addPelatihan(pelatihan);
            }
        }

        return "redirect:/pelatihan/add?success";
    }

    @GetMapping("/change/{id}")
    public String changePelatihan(
            @PathVariable("id") Long id, Model model
    ) {
        model.addAttribute("pelatihan", pelatihanService.getPelatihanById(id));
        model.addAttribute("listJenisPelatihan", jenisPelatihanService.getJenisPelatihanList());
        model.addAttribute("listTrainer", trainerService.getTrainerList());

        return "pelatihan/change-pelatihan";
    }

    @PostMapping("/change/{id}")
    public String changePelatihanSubmit(
            @ModelAttribute PelatihanModel pelatihan,
            @RequestParam("userPengaju") String userPengaju,
            @PathVariable("id") Long id,
            Model model
    ) {
        if (pelatihan.getTanggal_mulai().after(pelatihan.getTanggal_selesai())) {
            return String.format("redirect:/pelatihan/change/%s?errort", id);
        } else {
            if (pelatihan.getWaktu_mulai().after(pelatihan.getWaktu_selesai())) {
                return String.format("redirect:/pelatihan/change/%s?errorw", id);
            } else if (pelatihan.getTanggal_mulai().equals(pelatihan.getTanggal_selesai())
                    && pelatihan.getWaktu_mulai().equals(pelatihan.getWaktu_selesai())) {
                return String.format("redirect:/pelatihan/change/%s?errorw", id);
            } else {
                UserModel pengaju = userService.getUserByUsername(userPengaju);
                pelatihan.setPengaju(pengaju);
                if (!pelatihanService.changePelatihan(pelatihan))
                    return String.format("redirect:/pelatihan/change/%s?errork", id);
            }
        }

        return String.format("redirect:/pelatihan/view/%s?edits", id);
    }

    @GetMapping("/delete/{id}")
    public String deletePelatihan(
            @PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("id", id);
        if (id == null) {
            return "error-page";
        }
        PelatihanModel pelatihan = pelatihanService.getPelatihanById(id);
        if(pelatihan.getStatus_persetujuan() == 2) {
            return "redirect:/pelatihan/view/" + id + "?errorx";
        }else{
            pelatihanService.deletePelatihan(id);
            return "pelatihan/success-delete-pelatihan";
        }
    }
}
