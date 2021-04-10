package com.domariev.financialproject.repository;

import com.domariev.financialproject.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("SELECT i from Income i WHERE (:flowPurpose is null or :flowPurpose='' or lower(i.flowPurpose)" +
            " like lower(concat('%', :flowPurpose, '%')))")
    List<Income> findByFlowPurpose(@Param("flowPurpose") String flowPurpose);
}
