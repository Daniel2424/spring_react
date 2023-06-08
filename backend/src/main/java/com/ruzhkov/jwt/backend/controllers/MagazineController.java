package com.ruzhkov.jwt.backend.controllers;

import com.ruzhkov.jwt.backend.entites.Magazine;
import com.ruzhkov.jwt.backend.services.MagazineService;
import com.ruzhkov.jwt.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MagazineController {

    private final MagazineService magazineService;
    private final UserService userService;

    @Autowired
    public MagazineController(MagazineService magazineService, UserService userService) {
        this.magazineService = magazineService;
        this.userService = userService;
    }

    @GetMapping("/allMagazines")
    public ResponseEntity<List<Magazine>> showMagazines(){

        return ResponseEntity.ok(magazineService.getAllMagazines());

    }

    @GetMapping("/magazine/{id}")
    public ResponseEntity<Magazine> showMagazine(@PathVariable("id") int id){
        return ResponseEntity.ok(magazineService.getMagazineById(id));
    }

    @GetMapping("/addMagazine/{id}")
    public ResponseEntity<String> addMagazine(@PathVariable("id") int idMagazine){
        var name = getLogin();
        System.out.println("Yes Yes");
        magazineService.addMagazineToUser(idMagazine, name);
        return ResponseEntity.ok("Ok");

    }
    @GetMapping("/showMyMagazines")
    public ResponseEntity<List<Magazine>> showMyFavoriteMagazines(){
        System.out.println("It's ok");
        var login = getLogin();
        var user = userService.findByLog(login);
        var list = user.getListMagazines();
        list.forEach(x-> x.getId());

        return ResponseEntity.ok(user.getListMagazines());

    }
    private String getLogin(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String json = authentication.getName();
        System.out.println(json);
        var x = json.split(",");
        var y = x[3].split("=");
        var name = y[1];
        return name;
    }
}
