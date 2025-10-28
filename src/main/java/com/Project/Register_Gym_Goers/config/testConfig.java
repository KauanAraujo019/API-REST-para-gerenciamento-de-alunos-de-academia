package com.Project.Register_Gym_Goers.config;

import com.Project.Register_Gym_Goers.entities.Goer;
import com.Project.Register_Gym_Goers.repositories.GoerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

@Configuration
public class testConfig implements CommandLineRunner {

    @Autowired
    private GoerRepository goerRepository;

    @Override
    public void run(String... args) throws Exception {

        Goer goer = new Goer(null, "jose", 38, "12211221", "09999999", "rua generica Qd01", Instant.now());

        goerRepository.save(goer);

        

    }
}
