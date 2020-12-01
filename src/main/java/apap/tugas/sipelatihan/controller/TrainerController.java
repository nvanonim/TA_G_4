package apap.tugas.sipelatihan.controller;

import java.util.List;

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
            String msg = "Nomor KTP sudah terdaftar di dalam Database";
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
}
