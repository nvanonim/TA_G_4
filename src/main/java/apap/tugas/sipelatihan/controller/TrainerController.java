package apap.tugas.sipelatihan.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugas.sipelatihan.model.TrainerModel;
import apap.tugas.sipelatihan.service.TrainerService;

@Controller
public class TrainerController {
    @Qualifier("trainerServiceImpl")
    @Autowired
    private TrainerService trainerService;

    @GetMapping("/trainer/add")
    public String addTrainerFormPage(Model model) {
        model.addAttribute("trainer", new TrainerModel());
        return "trainer/form-add-trainer";
    }

    @PostMapping("/trainer/add")
    public String addTrainerSubmit(
        @ModelAttribute TrainerModel trainer,
        Model model
    ) {
        String noKtp = trainer.getNoKtp();
        if (trainerService.validateKTP(noKtp)) {
            trainerService.addTrainer(trainer);
            model.addAttribute("id", trainer.getId());
            return "trainer/add-trainer";
        } else {
            String msg = "Nomor KTP yang anda masukkan sudah terdaftar di dalam Database";
            model.addAttribute("msg", msg);
            return "trainer/add-trainer";
        }
    }

    @GetMapping("/trainer")
    public String listTrainer(Model model) {
        List<TrainerModel> listTrainer = trainerService.getTrainerList();
        model.addAttribute("listTrainer", listTrainer);
        return "trainer/list-trainer";
    }

    @GetMapping("/trainer/update/{id}")
    public String updateTrainerFormPage(
        @PathVariable("id") Long id,
        Model model
    ) {
        TrainerModel trainer = trainerService.getTrainerById(id);
        model.addAttribute("trainer", trainer);
        return "trainer/form-update-trainer";
    }

    @PostMapping("/trainer/update/{id}")
    public String updateTrainerSubmit(
        @ModelAttribute TrainerModel trainer,
        Model model
    ) {
        Long id = trainer.getId();
        String noKtp = trainer.getNoKtp();
        String noKtp_old = trainerService.getTrainerById(id).getNoKtp();
        if (noKtp.equals(noKtp_old)) {
            trainerService.updateTrainer(trainer);
            model.addAttribute("id", trainer.getId());
            return "trainer/update-trainer";
        } else {
            if (trainerService.validateKTP(noKtp)) {
                trainerService.updateTrainer(trainer);
                model.addAttribute("id", trainer.getId());
                return "trainer/update-trainer";
            } else {
                String msg = "Nomor KTP yang anda masukkan sudah terdaftar di dalam Database";
                model.addAttribute("msg", msg);
                return "trainer/update-trainer";
            }   
        }        
    }

    @GetMapping({"/trainer/delete/{id}", "/trainer/delete/"})
    public String deleteTrainer(
        @PathVariable(value ="id", required = false) Long id,
        Model model
    ) throws Exception {
        if (id != null) {
            if (isTrainerExists(id)) {
                trainerService.deleteTrainer(id);
                model.addAttribute("id", id);
                return "trainer/delete-trainer";    
            } else {
                String msg = "Id tidak dapat ditemukan!";
                model.addAttribute("msg", msg);
                return "trainer/delete-trainer";
            }         
        } else {
            String msg = "Id tidak dapat ditemukan!";
            model.addAttribute("msg", msg);
            return "trainer/delete-trainer";
        }
        
    }

    private boolean isTrainerExists(Long id) {
        try {
            boolean cek = trainerService.getTrainerById(id).isPresent();
            System.out.println(cek);
            return cek;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
