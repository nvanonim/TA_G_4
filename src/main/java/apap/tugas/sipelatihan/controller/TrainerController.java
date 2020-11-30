package apap.tugas.sipelatihan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

        return "form-add-trainer";
    }

    @PostMapping("/trainer/add")
    public String addTrainerSubmit(
        @ModelAttribute TrainerModel trainer,
        Model model
    ) {
        String no_ktp = trainer.getNo_ktp();
        if (isTrainerExist(no_ktp)) {
            trainerService.addTrainer(trainer);
            model.addAttribute("id", trainer.getId());
            return "add-trainer";
        } else {
            String msg = "Nomor KTP sudah terdaftar di dalam Database";
            model.addAttribute("msg", msg);
            return "add-trainer";
        }
    }

    private boolean isTrainerExist(String no_ktp) {
        return trainerService.getTrainerByNomorKTP(no_ktp).isPresent();
    }
}
