package apap.tugas.sipelatihan.restcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.tugas.sipelatihan.model.UserModel;
import apap.tugas.sipelatihan.rest.PegawaiDetail;
import apap.tugas.sipelatihan.service.RoleService;
import apap.tugas.sipelatihan.service.UserRestService;
import apap.tugas.sipelatihan.service.UserService;

@Controller
// @RequestMapping("/api/v1")
public class UserRestController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRestService userRestService;

    @GetMapping(value = "/user/add")
    public String addUser(Model model) {
        model.addAttribute("listRole", roleService.findAll());
        model.addAttribute("user", new UserModel());
        return "user/add-user";
    }

    @PostMapping(value = "/user/add")
    public String addUserSubmit(Model model, @ModelAttribute UserModel user) {
        String var = user.getPassword();
        System.out.println("====================++++++++++++++" + user.getPassword());
        System.out.println("====================++++++++++++++" + user.getNama());
        if (userService.getUserByUsername(user.getUsername()) == null) {
            PegawaiDetail pegawai = new PegawaiDetail();

            // pegawai.setIdPegawai(user.getId());
            pegawai.setUsername(user.getUsername());
            pegawai.setNama(user.getNama());
            pegawai.setNoTelepon(user.getNoTelepon());
            pegawai.setTempatLahir(user.getTempatLahir());
            pegawai.setTanggalLahir(user.getTanggalLahir());
            pegawai.setAlamat(user.getAlamat());
            pegawai.setIdRole(user.getRole().getId());

            ObjectMapper mapper = new ObjectMapper();
            try {
            String json = mapper.writeValueAsString(pegawai);
            System.out.println("ResultingJSONstring = " + json);
            //System.out.println(json);
            } catch (JsonProcessingException e) {
            e.printStackTrace();
            }

            userRestService.addPegawai(pegawai);
            userService.addUser(user);          
        }

        return "redirect:/login";
    }
}
