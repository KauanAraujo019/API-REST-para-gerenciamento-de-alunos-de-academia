package com.Project.Register_Gym_Goers.services;

import com.Project.Register_Gym_Goers.entities.Goer;
import com.Project.Register_Gym_Goers.repositories.GoerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoerService {

    private GoerRepository goerRepository;


    public List<Goer> findAll(){
        return goerRepository.findAll();
    }

    public Goer findById(Long id){
        return goerRepository.findById(id).get();
    }

    public Goer findByCpf(String cpf){
        String goer = goerRepository.findAll().stream().map(x -> x.getCpf().equals(cpf)).toString();
        System.out.println(goer);

        return null;

    }


}
