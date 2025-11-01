package com.Project.Register_Gym_Goers.entities;

import com.Project.Register_Gym_Goers.entities.enums.PlanCategory;
import com.Project.Register_Gym_Goers.entities.enums.StatusPayment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;
    private Integer statusPayment;
    private Month referenceMonth = LocalDate.now().getMonth();
    private LocalDate dueDay;


    @JsonIgnore
    @ManyToOne
    @JoinTable(name = "tb_Orders_Goers",
            joinColumns = @JoinColumn(name = "tb_invoices"),
            inverseJoinColumns = @JoinColumn(name = "tb_goers"))
    private Goer goer;


    public Invoice(){

    }

    public Invoice(Long id, StatusPayment statusPayment, Month referenceMonth) {
        this.id = id;
        setStatusPayment(statusPayment);
        this.referenceMonth = referenceMonth;

    }

    public void finallySetDueDay(PlanCategory planCategory){

        dueDay = goer.getInvoices().get(goer.getInvoices().size()-1).getDueDay().plusMonths(6);

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusPayment getStatusPayment() {
        return StatusPayment.findById(statusPayment);
    }

    public void setStatusPayment(StatusPayment statusPayment) {
        this.statusPayment = statusPayment.getCode();
    }

    public Month getReferenceMonth() {
        return referenceMonth;
    }

    public void setReferenceMonth(Month referenceMonth) {
        this.referenceMonth = referenceMonth;
    }

    public LocalDate getDueDay() {
        return dueDay;
    }

    public void setDueDay() {

        this.dueDay = goer.getDateRegister();
    }

    public Goer getGoer() {
        return goer;
    }

    public void setGoer(Goer goer) {
        this.goer = goer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(){
        this.price = PlanCategory.defPricePlan(goer.getPlanCategory());

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
