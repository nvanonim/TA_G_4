package apap.tugas.sipelatihan.controller;

import java.util.NoSuchElementException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import apap.tugas.sipelatihan.model.UserModel;
import apap.tugas.sipelatihan.repository.RoleDb;
import apap.tugas.sipelatihan.rest.PegawaiDetail;
import apap.tugas.sipelatihan.service.RoleService;
import apap.tugas.sipelatihan.service.UserRestService;
import apap.tugas.sipelatihan.service.UserService;
import reactor.core.publisher.Mono;

@Controller
// @RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleDb roleDb;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRestService userRestService;

    @RequestMapping("/")
    public String home(Model model) {
        return "home";
    }
    
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping(value = "/user/add")
    public String addUser(Model model) {
        model.addAttribute("listRole", roleService.findAll());
        // model.addAttribute("user", new UserModel());
        model.addAttribute("pegawai", new PegawaiDetail());
        return "user/add-user";
    }

    // @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    // public String addUserSubmit(@ModelAttribute UserModel user) {
    //     String var = user.getPassword();
    //     System.out.println("====================++++++++++++++" + user.getPassword());
    //     System.out.println("====================++++++++++++++" + user.getNama());
    //     if (userService.getUserByUsername(user.getUsername()) == null) {
    //         PegawaiDetail pegawai = new PegawaiDetail();

    //         // pegawai.setIdPegawai(user.getId());
    //         pegawai.setUsername(user.getUsername());
    //         pegawai.setNama(user.getNama());
    //         pegawai.setNoTelepon(user.getNoTelepon());
    //         pegawai.setTempatLahir(user.getTempatLahir());
    //         pegawai.setTanggalLahir(user.getTanggalLahir());
    //         pegawai.setAlamat(user.getAlamat());
    //         pegawai.setIdRole(user.getRole().getId());

    //         ObjectMapper mapper = new ObjectMapper();
    //         try {
    //             String json = mapper.writeValueAsString(pegawai);
    //             System.out.println("ResultingJSONstring = " + json);
    //             //System.out.println(json);
    //         } catch (JsonProcessingException e) {
    //             e.printStackTrace();
    //         }
    //         userRestService.addPegawai(pegawai);
    //         userService.addUser(user);   
            
    //         System.out.println("====================++++++++++++++" + pegawai.getUsername());
    //         System.out.println("====================++++++++++++++" + pegawai.getNama());
    //     }

    //     return "redirect:/login";
    // }

    // @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUserSubmit(@ModelAttribute PegawaiDetail pegawai) {        
        if (userService.getUserByUsername(pegawai.getUsername()) == null) {
            UserModel user = new UserModel();
            user.setUsername(pegawai.getUsername());
            user.setPassword(pegawai.getPassword());
            user.setRole(roleDb.findById(pegawai.getIdRole()).get());

            userRestService.addPegawai(pegawai);
            userService.addUser(user);   
            System.out.println("====================++++++++++++++" + user.getPassword());
            System.out.println("====================++++++++++++++" + user.getUsername());
            System.out.println("====================");
            System.out.println("====================++++++++++++++" + pegawai.getUsername());
            System.out.println("====================++++++++++++++" + pegawai.getNama());
        }

        return "redirect:/login";
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    // @GetMapping("/user/{username}")
    public String userProfile(@PathVariable(value = "username", required = false) String username,
    Model model
    ) {
        // try {
        UserModel user = userService.getUserByUsername(username);
        PegawaiDetail pegawai = userRestService.getPegawaiByUsername(username);
        String userGet = userRestService.getPegawaiString(username);
        System.out.println(userGet);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(pegawai);
            System.out.println("ResultingJSONstring = " + json);
            //System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // if (pegawai == null) {
            model.addAttribute("user", user);
            model.addAttribute("pegawai", pegawai);
            return "user/profile";
        // } else {
        //     String message = "pegawai tidak ada";
        //     model.addAttribute("user", user);
        //     model.addAttribute("message", message);
        //     return "user/profile";
        // }
            
        // } catch (NoSuchElementException e) {
        //     UserModel user = userService.getUserByUsername(username);
        //     // PegawaiDetail pegawai = userRestService.getPegawaiByUsername(username);
        //     model.addAttribute("user", user);
        //     // model.addAttribute("pegawai", pegawai);
        //     return "user/profile";
        // }
        
    }

    // @GetMapping(value = "/user/{username}")
    // @ResponseBody
    // private Mono<PegawaiDetail> retrieveResep(@PathVariable(value = "username", required = false) String username) {
    //     try {
    //         return userRestService.getPegawai(username);
    //         // return userRestService.getPegawaiByUsername(username);
    //     } catch (NoSuchElementException e) {
    //         throw new ResponseStatusException(
    //             HttpStatus.NOT_FOUND, "Resep with Number " + String.valueOf(username) + " not found!"
    //         );
    //     }
        
    // }
}
