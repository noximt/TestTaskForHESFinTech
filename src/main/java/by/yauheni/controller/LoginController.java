package by.yauheni.controller;

import by.yauheni.dto.UserAccountDTO;
import by.yauheni.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(path = "/login")
public class LoginController {
    private final UserAccountService service;

    @Autowired
    public LoginController(UserAccountService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView getLoginPage(ModelAndView modelAndView) {
        modelAndView.addObject("user", new UserAccountDTO());
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
