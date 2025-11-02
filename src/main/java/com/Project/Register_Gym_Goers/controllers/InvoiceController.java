package com.Project.Register_Gym_Goers.controllers;

import com.Project.Register_Gym_Goers.entities.Invoice;
import com.Project.Register_Gym_Goers.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;


    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id){

        invoiceService.update(id);

        return ResponseEntity.noContent().build();

    }



}
