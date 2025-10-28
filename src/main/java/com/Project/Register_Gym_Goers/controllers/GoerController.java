package com.Project.Register_Gym_Goers.controllers;

import com.Project.Register_Gym_Goers.entities.Goer;
import com.Project.Register_Gym_Goers.repositories.GoerRepository;
import com.Project.Register_Gym_Goers.services.GoerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/goers")
public class GoerController {

    @Autowired
    private GoerService goerService;


    @GetMapping
    public ResponseEntity<List<Goer>> findAll(){

        return ResponseEntity.ok().body(goerService.findAll());

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Goer> findById(@PathVariable Long id){

        return ResponseEntity.ok().body(goerService.findById(id));

    }


    // em breve finalizado
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Goer> findByCpf(@PathVariable String cpf){

       return ResponseEntity.ok().body(goerService.findByCpf(cpf));

    }

}
