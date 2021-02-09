package com.domariev.financialproject.service.impl;

import com.domariev.financialproject.exception.ResourceCreationException;
import com.domariev.financialproject.exception.ResourceNotFoundException;
import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.model.Costs;
import com.domariev.financialproject.repository.CashbookRepository;
import com.domariev.financialproject.repository.CostsRepository;
import com.domariev.financialproject.service.CostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CostsServiceImpl implements CostsService {

    private final CostsRepository costsRepository;

    private final CashbookRepository cashbookRepository;

    @Override
    public Costs add(Costs costs, Long id) {
        Costs newCosts = new Costs();
        Cashbook cashBook = cashbookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found cashbook with id " + id));
        newCosts.setFlowPurpose(costs.getFlowPurpose());
        newCosts.setPayment(costs.getPayment());
        newCosts.setTransactionDate(LocalDateTime.now());
        newCosts.setTo(costs.getTo());
        if (costs.getFullPaid() == null) {
            newCosts.setFullPaid(false);
        } else {
            newCosts.setFullPaid(costs.getFullPaid());
        }
        cashBook.getCosts().add(newCosts);
        newCosts = costsRepository.save(newCosts);
        if (costsRepository.findById(newCosts.getId()).isPresent()) {
            log.info("add(): costs with id " + newCosts.getId());
        } else {
            log.info("add(): failed to add costs");
            throw new ResourceCreationException("Failed to add costs");
        }
        return newCosts;
    }

    @Override
    public Costs getById(Long id) {
        Costs costs = costsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found costs with id " + id));
        log.info("get(): cashbook id " + id);
        return costs;
    }

    @Override
    public void delete(Long id) {
        Costs costs = getById(id);
        costsRepository.delete(costs);
        log.info("delete(): costs id " + id + " deleted");
    }
}
