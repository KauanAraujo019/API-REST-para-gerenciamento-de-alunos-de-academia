package com.Project.Register_Gym_Goers.services;

import com.Project.Register_Gym_Goers.entities.Invoice;
import com.Project.Register_Gym_Goers.entities.enums.StatusPayment;
import com.Project.Register_Gym_Goers.repositories.GoerRepository;
import com.Project.Register_Gym_Goers.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private GoerRepository goerRepository;


    public void update(Long id){

        goerRepository.getReferenceById(id).getInvoices().get(goerRepository.getReferenceById(id).getInvoices().size()-1).setStatusPayment(StatusPayment.PAID);

        goerRepository.save(goerRepository.getReferenceById(id));

    }



}
