package apap.tugas.sipelatihan.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// import apap.tugas.sipelatihan.model.UserModel;
// import apap.tugas.sipelatihan.rest.PegawaiDetail;
// import apap.tugas.sipelatihan.service.RoleService;
// import apap.tugas.sipelatihan.service.UserRestService;
// import apap.tugas.sipelatihan.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class PageController {
    // @Autowired
    // private RoleService roleService;

    // @Autowired
    // private UserService userService;

    // @Autowired
    // private UserRestService userRestService;

    @RequestMapping("/")
    public String home(Model model) {
        return "home";
    }
    
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    // @GetMapping(value = "/user/add")
    // public String addUser(Model model) {
    //     model.addAttribute("listRole", roleService.findAll());
    //     model.addAttribute("user", new UserModel());
    //     return "user/add-user";
    // }

    // @PostMapping(value = "/user/add")
    // public String addUserSubmit(Model model, @ModelAttribute UserModel user) {
    //     String var = user.getPassword();
    //     System.out.println("====================++++++++++++++" + user.getPassword());
    //     System.out.println("====================++++++++++++++" + user.getNama());
    //     if (userService.getUserByUsername(user.getUsername()) == null) {
    //         PegawaiDetail pegawai = new PegawaiDetail();

    //         pegawai.setIdPegawai(user.getId());
    //         pegawai.setUsername(user.getUsername());
    //         pegawai.setNama(user.getNama());
    //         pegawai.setNoTelepon(user.getNoTelepon());
    //         pegawai.setTempatLahir(user.getTempatLahir());
    //         pegawai.setTanggalLahir(user.getTanggalLahir());
    //         pegawai.setAlamat(user.getAlamat());
    //         pegawai.setRole(user.getRole());

    //         userRestService.addPegawai(pegawai);
    //         userService.addUser(user);   
            
    //         System.out.println("====================++++++++++++++" + pegawai.getUsername());
    //         System.out.println("====================++++++++++++++" + pegawai.getNama());
    //     }

    //     return "redirect:/login";
    // }

    // @GetMapping("/user/add")
    // public String addUser(Model model) {
    //     model.addAttribute("listRole", roleService.findAll());

    //     return "user/add-user";
    // }

    // @PostMapping("user/add")
    // public String addUserSubmit(Model model, @ModelAttribute UserModel user) {
    //     String var = user.getPassword();
    //     if (userService.getUserByUsername(user.getUsername()) == null) {
    //         userService.addUser(user);
    //     }
    //     System.out.println(user.getPassword());

    //     // UserModel userlocal = new UserModel();
    //     // userlocal.set(user.get)
    //     // service1.addlocal()
    //     // service2.addweb()

    //     return "redirect:/login";
    // }
}
