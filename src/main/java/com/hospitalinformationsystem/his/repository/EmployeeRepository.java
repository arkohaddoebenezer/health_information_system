package com.hospitalinformationsystem.his.repository;

import com.hospitalinformationsystem.his.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface EmployeeRepository<T extends Employee> extends JpaRepository<T, Long> {
    Optional<T> findByEmployeeNumber(String employeeNumber);
    void deleteByEmployeeNumber(String employeeNumber);
    boolean existsByEmployeeNumber(String employeeNumber);

    Collection<? extends Employee> findBySurname(String lastName);
}
