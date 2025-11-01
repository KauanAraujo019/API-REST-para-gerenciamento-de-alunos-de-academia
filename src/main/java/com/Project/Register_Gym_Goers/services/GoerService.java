package com.Project.Register_Gym_Goers.services;

import com.Project.Register_Gym_Goers.entities.Goer;
import com.Project.Register_Gym_Goers.entities.Invoice;
import com.Project.Register_Gym_Goers.entities.enums.PlanCategory;
import com.Project.Register_Gym_Goers.entities.enums.StatusPayment;
import com.Project.Register_Gym_Goers.repositories.GoerRepository;
import com.Project.Register_Gym_Goers.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        currentInvoiceStatus(id);

        return goerRepository.findById(id).get();
    }

    private void currentInvoiceStatus(Long id) {

        int lastInvoice = goerRepository.findById(id).get().getInvoices().size()-1;

        if (goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).g)


        if (goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).getStatusPayment().equals(StatusPayment.IN_PROGRESS)){

            if (LocalDate.now().getDayOfMonth() > goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).getDueDay().minusDays(7).getDayOfMonth()
                    && LocalDate.now().getDayOfMonth() < goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).getDueDay().getDayOfMonth()){

                goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).setStatusPayment(StatusPayment.WAITING_PAYMENT);

                invoiceRepository.save(goerRepository.getReferenceById(id).getInvoices().get(lastInvoice));

            }

        }
        else if (goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).getStatusPayment().equals(StatusPayment.WAITING_PAYMENT)){

            if (LocalDate.now().getDayOfYear() > goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).getDueDay().getDayOfYear()){

                int a = LocalDate.now().getDayOfYear();
                int b = goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).getDueDay().getDayOfYear();

                goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).setStatusPayment(StatusPayment.OVERDUE);

                invoiceRepository.save(goerRepository.getReferenceById(id).getInvoices().get(lastInvoice));

            }


        }
        else if (goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).getStatusPayment().equals(StatusPayment.PAID)){

            Invoice invoice = new Invoice(null, StatusPayment.IN_PROGRESS, goerRepository.getReferenceById(id).getPlanCategory(), goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).getReferenceMonth().plus(1));

            goerRepository.getReferenceById(id).getInvoices().add(invoice);

            invoice.setGoer(goerRepository.getReferenceById(id));

            invoiceRepository.save(invoice);


        }

    }


    // em breve finalizado
    public Goer findByCpf(String cpf){

        return null;

    }

    public Goer insert(Goer goer){

        goer.setAge();
        goerRepository.save(goer);

        goer.getInvoices().get(0).setGoer(goer);
        goer.getInvoices().get(0).setPrice();
        goer.getInvoices().get(0).setDueDay();

        invoiceRepository.save(goer.getInvoices().get(0));



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
