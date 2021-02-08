package com.domariev.financialproject.repository;

import com.domariev.financialproject.model.Cashbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashbookRepository extends JpaRepository<Cashbook, Long> {
}
