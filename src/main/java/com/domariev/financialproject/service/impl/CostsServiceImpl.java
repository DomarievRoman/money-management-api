package com.domariev.financialproject.service.impl;

import com.domariev.financialproject.dto.CostsDto;
import com.domariev.financialproject.exception.ResourceCreationException;
import com.domariev.financialproject.exception.ResourceNotFoundException;
import com.domariev.financialproject.mapper.CostsMapper;
import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.model.Costs;
import com.domariev.financialproject.repository.CashbookRepository;
import com.domariev.financialproject.repository.CostsRepository;
import com.domariev.financialproject.search.AbstractSearchValues;
import com.domariev.financialproject.service.CostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CostsServiceImpl implements CostsService {

    private final CostsRepository costsRepository;

    private final CashbookRepository cashbookRepository;

    private final CostsMapper costsMapper = Mappers.getMapper(CostsMapper.class);

    @Override
    public CostsDto add(CostsDto costsDto) {
        Costs costs = costsMapper.costsDtoToCosts(costsDto);
        Costs newCosts = new Costs();
        Cashbook cashbook = cashbookRepository.findById(costs.getCashbook().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found cashbook with id "
                        + costs.getCashbook().getId()));
        newCosts.setFlowPurpose(costs.getFlowPurpose());
        newCosts.setPayment(costs.getPayment());
        newCosts.setTransactionDate(costs.getTransactionDate());
        newCosts.setTo(costs.getTo());
        newCosts.setFullPaid(costs.getFullPaid());
        newCosts.setCashbook(cashbook);
        cashbook.getCosts().add(newCosts);
        newCosts = costsRepository.save(newCosts);
        if (costsRepository.findById(newCosts.getId()).isPresent()) {
            log.info("add(): costs with id " + newCosts.getId());
        } else {
            log.info("add(): failed to add costs");
            throw new ResourceCreationException("Failed to add costs");
        }
        return costsMapper.costsToCostsDto(newCosts);
    }

    @Override
    public CostsDto getById(Long id) {
        Costs costs = costsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found costs with id " + id));
        log.info("get(): cashbook id " + id);
        return costsMapper.costsToCostsDto(costs);
    }

    @Override
    public List<CostsDto> getAll() {
        List<Costs> costsList = costsRepository.findAll();
        if (costsList.isEmpty()) {
            throw new ResourceNotFoundException("There are no costs yet");
        } else {
            log.info("getAll(): retrieved all costs");
            return costsMapper.costsListToDto(costsList);
        }
    }

    @Override
    public List<CostsDto> search(AbstractSearchValues abstractSearchValues) {
        List<Costs> costsList = costsRepository.findByFlowPurpose(abstractSearchValues.getFlowPurpose());
        if (costsList.isEmpty()) {
            throw new ResourceNotFoundException("There are no wanted incomes");
        } else {
            log.info("search(): all found costs");
            return costsMapper.costsListToDto(costsList);
        }
    }

    @Override
    public CostsDto update(CostsDto costsDto) {
        CostsDto newCostsDto = new CostsDto();
        newCostsDto.setId(costsDto.getId());
        newCostsDto.setFlowPurpose(costsDto.getFlowPurpose());
        newCostsDto.setPayment(costsDto.getPayment());
        newCostsDto.setTo(costsDto.getTo());
        newCostsDto.setTransactionDate(costsDto.getTransactionDate());
        newCostsDto.setFullPaid(costsDto.getFullPaid());
        newCostsDto.setCashbook(costsDto.getCashbook());
        Costs costs = costsMapper.costsDtoToCosts(newCostsDto);
        costs = costsRepository.save(costs);
        log.info("update(): costs with id " + costs.getId());
        return costsMapper.costsToCostsDto(costs);
    }

    @Override
    public void delete(Long id) {
        CostsDto costsDto = getById(id);
        Costs costs = costsMapper.costsDtoToCosts(costsDto);
        costsRepository.delete(costs);
        log.info("delete(): costs id " + id + " deleted");
    }
}
