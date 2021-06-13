package by.yauheni.controller;

import by.yauheni.dto.NewUserDTO;
import by.yauheni.entity.Role;
import by.yauheni.entity.Status;
import by.yauheni.entity.UserAccount;
import by.yauheni.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserAccountController {
    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping
    public ModelAndView showUsers(ModelAndView modelAndView) {
        modelAndView.setViewName("list");
        List<UserAccount> allUsers = userAccountService.getAll();
        modelAndView.addObject("users", allUsers);
        checkRole(modelAndView);
        return modelAndView;
    }

    @GetMapping(path = "/")
    public ModelAndView showUser(ModelAndView modelAndView, @RequestParam long id) {
        UserAccount byId = userAccountService.findById(id);
        modelAndView.addObject("viewUser", byId);
        checkRole(modelAndView);
        modelAndView.setViewName("view");
        return modelAndView;
    }

    private void checkRole(ModelAndView modelAndView) {
        Collection<? extends GrantedAuthority> authorities =  SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object[] objects = authorities.toArray();
        if (objects[0].toString().equals("ADMIN")){
            modelAndView.addObject("admin", 1);
        }else{
            modelAndView.addObject("admin", 0);
        }
    }

    @GetMapping(path = "/edit")
    public ModelAndView editView(ModelAndView modelAndView, @RequestParam long id){
        UserAccount byId = userAccountService.findById(id);
        modelAndView.addObject("editableUser", byId);
        modelAndView.addObject("editedUser", new UserAccount());
        setRolesAndStatus(modelAndView);
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @PostMapping(path = "/edit")
    public ModelAndView edit(ModelAndView modelAndView,@Valid @ModelAttribute NewUserDTO newUserDTO, BindingResult bindingResult){
        setRolesAndStatus(modelAndView);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("edit");
        }else{
            modelAndView.setViewName("redirect:/user");
        }
        return modelAndView;
    }


    @GetMapping(path = "/new")
    public ModelAndView showUserCreation(ModelAndView modelAndView) {
        modelAndView.addObject("newUser", new NewUserDTO());
        setRolesAndStatus(modelAndView);
        modelAndView.setViewName("new");
        return modelAndView;
    }

    private void setRolesAndStatus(ModelAndView modelAndView) {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        roles.add(Role.ADMIN);
        modelAndView.addObject("roles", roles);
        List<Status> statuses = new ArrayList<>();
        statuses.add(Status.ACTIVE);
        statuses.add(Status.INACTIVE);
        modelAndView.addObject("statuses", statuses);
    }

    @PostMapping(path = "/new")
    public ModelAndView createNewUser(ModelAndView modelAndView, @Valid @ModelAttribute("newUser") NewUserDTO newUserDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            setRolesAndStatus(modelAndView);
            modelAndView.setViewName("/new");
        } else {
            if (userAccountService.existsByUsername(newUserDTO.getUsername())) {
                modelAndView.addObject("error", 1);
                setRolesAndStatus(modelAndView);
                modelAndView.setViewName("new");
            }else{
                modelAndView.addObject("error", 0);
                userAccountService.save(newUserDTO);
                modelAndView.setViewName("redirect:/user");
            }
        }
        return modelAndView;
    }
}
