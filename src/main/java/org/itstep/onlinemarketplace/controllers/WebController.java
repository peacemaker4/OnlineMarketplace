package org.itstep.onlinemarketplace.controllers;

import org.itstep.onlinemarketplace.config.StaticConfig;
import org.itstep.onlinemarketplace.entities.Advert;
import org.itstep.onlinemarketplace.entities.DbUser;
import org.itstep.onlinemarketplace.entities.Role;
import org.itstep.onlinemarketplace.entities.Tag;
import org.itstep.onlinemarketplace.models.AdvertModel;
import org.itstep.onlinemarketplace.models.TagList;
import org.itstep.onlinemarketplace.models.UserModel;
import org.itstep.onlinemarketplace.service.AdvertApiService;
import org.itstep.onlinemarketplace.service.AdvertApiServiceImpl;
import org.itstep.onlinemarketplace.service.AdvertService;
import org.itstep.onlinemarketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {


    private final UserService userService;

    private final AdvertApiService advertService;

    public WebController(UserService userService, AdvertApiService advertService) {
        this.userService = userService;
        this.advertService = advertService;
    }

    @GetMapping(value = "login")
    public String loginPage(){
        return "login";
    }

    @GetMapping(value = "/")
    public String indexPage(Model model){
        model.addAttribute("user", getUser());
        List<Advert> adverts = advertService.getAdverts();
        model.addAttribute("adverts", adverts.subList(Math.max(adverts.size()-3, 0), adverts.size()));

        return "index";
    }

    private DbUser getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User securityUser = (User) authentication.getPrincipal();
            DbUser user = userService.getUser(securityUser.getUsername());
            return user;
        }
        return null;
    }

    @GetMapping(value = "register")
    public String registerPage(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "register";
    }

    @PostMapping(value="register")
    public String registerUser(@ModelAttribute UserModel userModel){
        if(userModel.getPassword().equals(userModel.getConfirmPassword()) && userService.getUser(userModel.getEmail())==null){
            List<Role> roles = new ArrayList<Role>();
            roles.add(StaticConfig.ROLE_USER);
            var dbUser = new DbUser(userModel.getEmail(), userModel.getPassword(), userModel.getFullName(), roles);
            userService.registerUser(dbUser);
            return "redirect:/login";
        }
        else if(userService.getUser(userModel.getEmail()) != null){
            return "redirect:/register?error=2";
        }
        else{
            return "redirect:/register?error=1";
        }
    }


}
