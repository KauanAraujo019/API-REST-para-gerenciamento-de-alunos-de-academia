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

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class testConfig implements CommandLineRunner {

    @Autowired
    private GoerRepository goerRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public void run(String... args) throws Exception {

        Goer goer = new Goer(null, "jose", LocalDate.parse("16/10/1981", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "12211221", "09999999", "rua generica Qd01", LocalDate.now());

        goerRepository.save(goer);

        Invoice invoice1 = new Invoice(null, StatusPayment.PAID, PlanCategory.MONTHLY, LocalDate.now(), LocalDate.now());

        goer.getInvoices().add(invoice1);
        invoice1.setGoer(goer);

        invoiceRepository.save(invoice1);






    }
}
