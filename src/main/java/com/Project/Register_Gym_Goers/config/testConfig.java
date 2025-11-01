package com.Project.Register_Gym_Goers.config;

import com.Project.Register_Gym_Goers.entities.Goer;
import com.Project.Register_Gym_Goers.entities.Invoice;
import com.Project.Register_Gym_Goers.entities.enums.PlanCategory;
import com.Project.Register_Gym_Goers.entities.enums.StatusPayment;
import com.Project.Register_Gym_Goers.repositories.GoerRepository;
import com.Project.Register_Gym_Goers.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;



@Configuration
public class testConfig implements CommandLineRunner {

    @Autowired
    private GoerRepository goerRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;


    @Override
    public void run(String... args) throws Exception {


    }
}
