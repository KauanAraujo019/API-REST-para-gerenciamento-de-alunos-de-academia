package com.Project.Register_Gym_Goers.services;

import com.Project.Register_Gym_Goers.entities.Goer;
import com.Project.Register_Gym_Goers.repositories.GoerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoerService {

    @Autowired
    private GoerRepository goerRepository;


    public List<Goer> findAll(){
        return goerRepository.findAll();
    }

    public Goer findById(Long id){
        return goerRepository.findById(id).get();
    }

    // em breve finalizado
    public Goer findByCpf(String cpf){

        return null;

    }

    public Goer insert(Goer goer){

        return goerRepository.save(goer);

    }


    /*
    public Goer update(Long id, Goer goer){
        Goer obj = new Goer();

        UpdateToGoer(obj, goer);



    }

    private void UpdateToGoer(Goer obj, Goer goer) {



    }


     */

}
