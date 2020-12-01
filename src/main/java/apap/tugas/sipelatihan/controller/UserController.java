package apap.tugas.sipelatihan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.tugas.sipelatihan.model.UserModel;
import apap.tugas.sipelatihan.service.RoleService;
import apap.tugas.sipelatihan.service.UserService;

@Controller
// @RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/user/add")
    public String addUser(Model model) {
        model.addAttribute("listRole", roleService.findAll());

        return "user/add-user";
    }

    @PostMapping("user/add")
    public String addUserSubmit(Model model, @ModelAttribute UserModel user) {
        String var = user.getPassword();
        if (userService.getUserByUsername(user.getUsername()) == null) {
            userService.addUser(user);
        }
        System.out.println(user.getPassword());

        return "redirect:/login";
    }
}
