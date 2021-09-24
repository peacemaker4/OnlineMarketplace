package org.itstep.onlinemarketplace.controllers;

import org.itstep.onlinemarketplace.entities.Advert;
import org.itstep.onlinemarketplace.entities.Basket;
import org.itstep.onlinemarketplace.entities.DbUser;
import org.itstep.onlinemarketplace.models.AdvertModel;
import org.itstep.onlinemarketplace.repositories.AdvertRepository;
import org.itstep.onlinemarketplace.repositories.BasketRepository;
import org.itstep.onlinemarketplace.service.AdvertService;
import org.itstep.onlinemarketplace.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class AdvertController {

    private final UserService userService;
    private final AdvertRepository advertRepository;
    private final AdvertService advertService;
    private final BasketRepository basketRepository;

    public AdvertController(UserService userService, AdvertRepository advertRepository, AdvertService advertService, BasketRepository basketRepository) {
        this.userService = userService;
        this.advertRepository = advertRepository;
        this.advertService = advertService;
        this.basketRepository = basketRepository;
    }

    @GetMapping("/adverts")
    public String getAllAdverts(Model model,
                                @RequestParam(defaultValue = "0") Integer pageNO,
                                @RequestParam(defaultValue = "5") Integer pageSize){
        List<Advert> all = advertService.getAdverts(pageNO, pageSize);

        model.addAttribute("adverts", all);
        model.addAttribute("user", getUser());
        model.addAttribute("pageNO", pageNO);
        model.addAttribute("pageSize", pageSize);

        return "adverts";
    }

    @GetMapping(value = "advert")
    public String addAdvert(Model model) {
        model.addAttribute("advertModel", new AdvertModel());
        model.addAttribute("user", getUser());
        return "addAdvert";
    }

    @PostMapping(value="advert")
    public String addAdvert(@ModelAttribute AdvertModel advertModel){

        if(advertModel!=null){
            var advert = new Advert(advertModel.getTitle(), advertModel.getDescription(), advertModel.getPrice(), getUser().getId());
            advertRepository.save(advert);
            return "redirect:/";
        }
        else{
            return "redirect:/advert?error";
        }
    }

    @GetMapping("/adverts/{id}")
    public String getAdvert(Model model, @PathVariable ("id") Long id){
        Advert advert = advertRepository.getById(id);

        model.addAttribute("basket", basketRepository.findBasketByAdvertId(id));
        model.addAttribute("advert", advert);
        model.addAttribute("user", getUser());
        return "advert";
    }

    @PostMapping("/adverts/delete/{id}")
    public String deleteAdvert(Model model, @PathVariable Long id){
        model.addAttribute("user", getUser());
        if(advertRepository.getById(id).getUser_id().equals(getUser().getId())){
            advertRepository.deleteById(id);
        }
        return "redirect:/adverts";
    }

    @PostMapping("/adverts/buy/{id}")
    public String buyAdvert(Model model, @PathVariable Long id){
        model.addAttribute("user", getUser());
        if(!Objects.equals(advertRepository.getById(id).getUser_id(), getUser().getId()) && basketRepository.findBasketByAdvertId(id)==null) {
            basketRepository.save(new Basket(id, getUser().getId()));
        }
        return "redirect:/adverts";
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
}
