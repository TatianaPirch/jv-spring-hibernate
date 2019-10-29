package mate.academy.spring.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import mate.academy.spring.dto.UserDto;
import mate.academy.spring.entity.Role;
import mate.academy.spring.entity.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private static final String ROLE_NAME = "ROLE_USER";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute @Valid UserDto userDto,
            BindingResult result, Model model) {
        User newUser = new User(userDto.getUsername(), userDto.getPassword(),
                userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
        if (result.hasErrors()) {
            model.addAttribute("message", "User creating error");
            return "errorPage";
        }
        Set<Role> roles = new HashSet<>();
        Role role = roleService.getRoleByName(ROLE_NAME).get();
        roles.add(role);
        newUser.setRoles(roles);
        userService.add(newUser);
        return "login";
    }
}
