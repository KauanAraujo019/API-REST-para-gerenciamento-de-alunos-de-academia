package com.Project.Register_Gym_Goers.entities;

import com.Project.Register_Gym_Goers.entities.enums.PlanCategory;
import com.Project.Register_Gym_Goers.entities.enums.StatusPayment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer statusPayment;
    private Integer planCategory;
    private LocalDate referenceMonth;
    private LocalDate dueDay;


    @JsonIgnore
    @ManyToOne
    @JoinTable(name = "tb_Orders_Goers",
            joinColumns = @JoinColumn(name = "tb_goer"),
            inverseJoinColumns = @JoinColumn(name = "tb_invoices"))
    private Goer goer;


    public Invoice(){

    }

    public Invoice(Long id, StatusPayment statusPayment, PlanCategory planCategory, LocalDate referenceMonth, LocalDate dueDay) {
        this.id = id;
        setStatusPayment(statusPayment);
        setPlanCategory(planCategory);
        this.referenceMonth = referenceMonth;
        this.dueDay = dueDay;

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

    public PlanCategory getPlanCategory() {
        return PlanCategory.findById(planCategory);
    }

    public void setPlanCategory(PlanCategory planCategory) {
        this.planCategory = planCategory.getCode();
    }

    public LocalDate getReferenceMonth() {
        return referenceMonth;
    }

    public void setReferenceMonth(LocalDate referenceMonth) {
        this.referenceMonth = referenceMonth;
    }

    public LocalDate getDueDay() {
        return dueDay;
    }

    public void setDueDay(LocalDate dueDay) {
        this.dueDay = dueDay;
    }

    public Goer getGoer() {
        return goer;
    }

    public void setGoer(Goer goer) {
        this.goer = goer;
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
