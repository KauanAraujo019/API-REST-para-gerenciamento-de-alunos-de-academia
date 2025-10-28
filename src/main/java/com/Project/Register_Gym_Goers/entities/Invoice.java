package com.Project.Register_Gym_Goers.entities;

import com.Project.Register_Gym_Goers.entities.enums.PlanCategory;
import com.Project.Register_Gym_Goers.entities.enums.StatusPayment;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private StatusPayment statusPayment;
    private PlanCategory planCategory;
    private LocalDate referenceMonth;
    private LocalDate dueMonth;




    public Invoice(){

    }

    public Invoice(Long id, StatusPayment statusPayment, PlanCategory planCategory, LocalDate referenceMonth, LocalDate dueMonth) {
        this.id = id;
        this.statusPayment = statusPayment;
        this.planCategory = planCategory;
        this.referenceMonth = referenceMonth;
        this.dueMonth = dueMonth;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusPayment getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(StatusPayment statusPayment) {
        this.statusPayment = statusPayment;
    }

    public PlanCategory getPlanCategory() {
        return planCategory;
    }

    public void setPlanCategory(PlanCategory planCategory) {
        this.planCategory = planCategory;
    }

    public LocalDate getReferenceMonth() {
        return referenceMonth;
    }

    public void setReferenceMonth(LocalDate referenceMonth) {
        this.referenceMonth = referenceMonth;
    }

    public LocalDate getDueMonth() {
        return dueMonth;
    }

    public void setDueMonth(LocalDate dueMonth) {
        this.dueMonth = dueMonth;
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
