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

import java.time.LocalDate;


@Configuration
public class testConfig implements CommandLineRunner {

    @Autowired
    private GoerRepository goerRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;


    @Override
    public void run(String... args) throws Exception {

        Goer goer = new Goer(null, "kauan", LocalDate.parse("2004-12-12"), "4541515","12212221","rua generica", LocalDate.now());

        goerRepository.save(goer);

        Invoice invoice1 = new Invoice(null, StatusPayment.PAID, PlanCategory.MONTHLY, LocalDate.now().plusMonths(1), LocalDate.now());

        invoice1.setGoer(goer);
        goer.getInvoices().add(invoice1);

        invoiceRepository.save(invoice1);

    }
}
