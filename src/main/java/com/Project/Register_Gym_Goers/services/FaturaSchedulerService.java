package com.Project.Register_Gym_Goers.services;

import com.Project.Register_Gym_Goers.entities.Invoice;
import com.Project.Register_Gym_Goers.entities.enums.StatusPayment;
import com.Project.Register_Gym_Goers.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.ScheduledFuture;

import static java.time.ZoneId.systemDefault;


@Service
public class FaturaSchedulerService {

    @Autowired
    private TaskScheduler scheduler;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public void agendarProximaFatura(Invoice currentInvoice) {


        LocalDateTime date = LocalDateTime.now().plusSeconds(10);


        Instant instantExecution = date.atZone(ZoneId.systemDefault()).toInstant();

        scheduler.schedule(() -> gerarProximaFatura(currentInvoice), instantExecution);

        System.out.println("Fatura futura agendada para: " + date);

    }

    private void gerarProximaFatura(Invoice faturaAnterior) {

        Invoice nova = new Invoice();
        nova.setGoer(faturaAnterior.getGoer());
        nova.setReferenceMonth(faturaAnterior.getReferenceMonth().plusMonths(1));
    //    nova.setDueMonth(faturaAnterior.getDueMonth().plusMonths(1));
        nova.setStatusPayment(StatusPayment.IN_PROGRESS);
        nova.setPlanCategory(faturaAnterior.getPlanCategory());

        invoiceRepository.save(nova);

        System.out.println("Fatura gerada para aluno: " + faturaAnterior.getGoer().getName());
    }


}