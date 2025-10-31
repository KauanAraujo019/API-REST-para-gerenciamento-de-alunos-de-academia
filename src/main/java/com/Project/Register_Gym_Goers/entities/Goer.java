package com.Project.Register_Gym_Goers.entities;

import com.Project.Register_Gym_Goers.entities.enums.PlanCategory;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "goers")
public class Goer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate birthDate;
    private Integer age;
    private String cpf;
    private String phone;
    private String address;
    private Integer planCategory;

    private LocalDate dateRegister = LocalDate.now();


    @OneToMany(mappedBy = "goer")
    private List<Invoice> invoices = new ArrayList<>();


    public Goer(){

    }

    public Goer(Long id, String name, LocalDate birthDate, String cpf, String phone, String address, LocalDate dateRegister, PlanCategory planCategory) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.phone = phone;
        this.address = address;
        this.dateRegister = dateRegister;
        setPlanCategory(planCategory);

        age = LocalDate.now().getYear() - birthDate.getYear();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDate dateRegister) {
        this.dateRegister = dateRegister;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public PlanCategory getPlanCategory() {
        return PlanCategory.findById(planCategory);
    }

    public void setPlanCategory(PlanCategory planCategory) {
        this.planCategory = planCategory.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Goer goer = (Goer) o;
        return Objects.equals(id, goer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
