package com.Repository;

import com.Entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employe,Integer> {
}
