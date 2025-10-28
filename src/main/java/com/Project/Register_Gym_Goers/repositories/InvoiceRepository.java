package com.Project.Register_Gym_Goers.repositories;

import com.Project.Register_Gym_Goers.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
