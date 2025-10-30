package com.Project.Register_Gym_Goers.services;

import com.Project.Register_Gym_Goers.entities.Goer;
import com.Project.Register_Gym_Goers.entities.Invoice;
import com.Project.Register_Gym_Goers.entities.enums.PlanCategory;
import com.Project.Register_Gym_Goers.entities.enums.StatusPayment;
import com.Project.Register_Gym_Goers.repositories.GoerRepository;
import com.Project.Register_Gym_Goers.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GoerService {

    @Autowired
    private GoerRepository goerRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Goer> findAll(){

        return goerRepository.findAll();
    }

    public Goer findById(Long id){

        if (LocalDate.now().plusMonths(1).equals(goerRepository.findById(id).get().getInvoices().get(0).getDueDay())){

            goerRepository.findById(id).get().getInvoices().get(0).setStatusPayment(StatusPayment.WAITING_PAYMENT);

        }

        return goerRepository.findById(id).get();
    }

    // em breve finalizado
    public Goer findByCpf(String cpf){

        return null;

    }

    public Goer insert(Goer goer){

        goerRepository.save(goer);

        Invoice invoice = new Invoice(null, StatusPayment.PAID, PlanCategory.MONTHLY, LocalDate.now(), LocalDate.now().plusMonths(1));

        goer.getInvoices().add(invoice);

        invoice.setGoer(goer);

        invoiceRepository.save(invoice);

        return goer;

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
