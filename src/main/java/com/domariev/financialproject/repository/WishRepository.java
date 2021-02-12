package com.domariev.financialproject.repository;

import com.domariev.financialproject.model.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    @Modifying
    @Query("update Wish wish set wish.implemented = :implemented where wish.id = :id")
    int setUserById(@Param("implemented") Boolean implemented, @Param("id") Long id);

}
