package com.Project.Register_Gym_Goers.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
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
    private Integer age;
    private String cpf;
    private String phone;
    private String address;
    private Instant dateRegister;


    private List<Invoice> invoices = new ArrayList<>();



    public Goer(){

    }

    public Goer(Long id, String name, Integer age, String cpf, String phone, String address, Instant dateRegister) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.cpf = cpf;
        this.phone = phone;
        this.address = address;
        this.dateRegister = dateRegister;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public Instant getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Instant dateRegister) {
        this.dateRegister = dateRegister;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
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
}
