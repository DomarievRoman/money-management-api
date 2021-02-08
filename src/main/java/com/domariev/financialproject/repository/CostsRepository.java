package com.domariev.financialproject.repository;

import com.domariev.financialproject.model.Costs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostsRepository extends JpaRepository<Costs, Long> {
}
