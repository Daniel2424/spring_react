package com.ruzhkov.jwt.backend.controllers;

import com.ruzhkov.jwt.backend.entites.Message;
import com.ruzhkov.jwt.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessagesController {

    private final UserRepository userRepository;

    @Autowired
    public MessagesController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/messages")
    public ResponseEntity<List<String>> messages() {

        var name = getLogin();
        System.out.println(name);
        var user = userRepository.findByLogin(name);
        if(user.isPresent()){
            return ResponseEntity.ok(List.of(name));
        }else{

            return ResponseEntity.ok(List.of("плохо"));
        }

    }

    @GetMapping("/testReact")
    public ResponseEntity<List<Message>> getMessages(){
        var name = getLogin();
        var user = userRepository.findByLogin(name);
        if(user.isPresent()){
            List<String> list = new ArrayList<>();
            //user.get().getListMessages().forEach(msg-> list.add(msg.getMessage()));
            return ResponseEntity.ok(user.get().getListMessages());
        }else{
            return ResponseEntity.ok(List.of());
        }


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
