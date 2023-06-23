package com.example.demo.employeerepository;

import com.example.demo.employeemodel.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeerepo extends JpaRepository<employee,Integer> {

}
