package com.domariev.financialproject.repository;

import com.domariev.financialproject.model.MoneyJar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyJarRepository extends JpaRepository<MoneyJar, Long> {
}
