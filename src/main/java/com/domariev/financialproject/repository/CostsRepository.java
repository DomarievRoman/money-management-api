package com.domariev.financialproject.repository;

import com.domariev.financialproject.model.Costs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostsRepository extends JpaRepository<Costs, Long> {

    @Query("SELECT c from Costs c WHERE (:flowPurpose is null or :flowPurpose='' or lower(c.flowPurpose)" +
            " like lower(concat('%', :flowPurpose, '%')))")
    List<Costs> findByFlowPurpose(@Param("flowPurpose") String flowPurpose);
}
