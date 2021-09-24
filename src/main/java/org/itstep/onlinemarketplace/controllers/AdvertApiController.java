package org.itstep.onlinemarketplace.controllers;

import org.itstep.onlinemarketplace.entities.Advert;
import org.itstep.onlinemarketplace.repositories.AdvertRepository;
import org.itstep.onlinemarketplace.service.AdvertService;
import org.itstep.onlinemarketplace.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("market")
public class AdvertApiController {

    private final AdvertRepository advertRepository;

    public AdvertApiController(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

//    private final AdvertService advertService;
//
//    public AdvertController(AdvertService advertService) {
//        this.advertService = advertService;
//    }
    @ResponseBody
    @GetMapping("/adverts")
    public ResponseEntity<List<Advert>> getAllAdverts(){
//        List<Advert> all = advertService.getAdverts();
        List<Advert> all = advertRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/adverts")
    public ResponseEntity<String> addAdvert(@RequestBody Advert advert){
//        advertService.addAdvert(advert);
        advertRepository.save(advert);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @DeleteMapping("/adverts/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        advertRepository.deleteById(id);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
