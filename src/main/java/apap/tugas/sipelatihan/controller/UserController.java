package apap.tugas.sipelatihan.controller;

import java.security.Principal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import apap.tugas.sipelatihan.model.UserModel;
import apap.tugas.sipelatihan.repository.RoleDb;
import apap.tugas.sipelatihan.rest.BaseResponse;
import apap.tugas.sipelatihan.rest.PegawaiDetail;
import apap.tugas.sipelatihan.rest.UserDetail;
import apap.tugas.sipelatihan.service.RoleService;
import apap.tugas.sipelatihan.service.UserRestService;
import apap.tugas.sipelatihan.service.UserService;

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
    
    @PostMapping("/user/add")
    public String addUserSubmit(@ModelAttribute PegawaiDetail pegawai, Model model) {
        try {
            if (userService.getUserByUsername(pegawai.getUsername()) == null) {
                UserModel user = new UserModel();
                user.setUsername(pegawai.getUsername());
                user.setPassword(pegawai.getPassword());
                user.setRole(roleDb.findById(pegawai.getIdRole()).get());

                userRestService.addPegawai(pegawai);
                userService.addUser(user);
            }
            return "redirect:/login";    
        } catch (Exception e) {
            model.addAttribute("listRole", roleService.findAll());
            // model.addAttribute("user", new UserModel());
            model.addAttribute("pegawai", new PegawaiDetail());
            return "user/add-user"; 
        }
        
    }

    @RequestMapping(value = "/user/profil", method = RequestMethod.GET)
    public String userProfile(
        Principal principal, 
        Model model)
            throws JsonMappingException, JsonProcessingException {
        String username = principal.getName();
        UserModel user = userService.getUserByUsername(username);
        try {            
            BaseResponse<UserDetail> response = userRestService.getPegawai(username);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(response.getResult());
            UserDetail userD = new ObjectMapper().readValue(json, UserDetail.class);
            System.out.println(userD.getIdPegawai());
            
            System.out.println("ResultingJSONstring = " + json);

            
            model.addAttribute("user", user);
            model.addAttribute("pegawai", userD);
            return "user/profile";
        } catch (WebClientResponseException e) {
            e.printStackTrace();
            model.addAttribute("message", "gkada api");
            model.addAttribute("user", user);
            return "user/profile";
        }
        
    }
}
