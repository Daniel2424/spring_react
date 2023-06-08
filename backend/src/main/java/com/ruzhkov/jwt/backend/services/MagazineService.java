package com.ruzhkov.jwt.backend.services;

import com.ruzhkov.jwt.backend.entites.Magazine;
import com.ruzhkov.jwt.backend.repositories.MagazineRepository;
import com.ruzhkov.jwt.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MagazineService {
    private final MagazineRepository magazineRepository;
    private final UserRepository userRepository;

    @Autowired
    public MagazineService(MagazineRepository magazineRepository, UserRepository userRepository) {
        this.magazineRepository = magazineRepository;
        this.userRepository = userRepository;
    }


    public List<Magazine> getAllMagazines() {
        return magazineRepository.findAll();
    }

    public Magazine getMagazineById(int id) {
        return magazineRepository.findById(id).get();
    }

    public void addMagazineToUser(int idMagazine, String name) {
        var user = userRepository.findByLogin(name).get();
        var magazine = magazineRepository.findById(idMagazine).get();

        if(user.getListMagazines() == null){
            user.setListMagazines(new ArrayList<>(List.of(magazine)));
        }else{
            if(!user.getListMagazines().contains(magazine)){
                user.getListMagazines().add(magazine);
            }
        }

        if(magazine.getUserList() == null){
            magazine.setUserList(new ArrayList<>(List.of(user)));
        }else{
            if(!magazine.getUserList().contains(user)){
                magazine.getUserList().add(user);
            }
        }
        userRepository.save(user);
        magazineRepository.save(magazine);

    }
}
