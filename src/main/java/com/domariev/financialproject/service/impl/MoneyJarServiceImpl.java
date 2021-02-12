package com.domariev.financialproject.service.impl;

import com.domariev.financialproject.dto.MoneyJarDto;
import com.domariev.financialproject.dto.WishDto;
import com.domariev.financialproject.exception.ResourceCreationException;
import com.domariev.financialproject.exception.ResourceNotFoundException;
import com.domariev.financialproject.mapper.MoneyJarMapper;
import com.domariev.financialproject.model.MoneyJar;
import com.domariev.financialproject.model.Wish;
import com.domariev.financialproject.repository.MoneyJarRepository;
import com.domariev.financialproject.service.MoneyJarService;
import com.domariev.financialproject.util.GoalAchievedUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MoneyJarServiceImpl implements MoneyJarService {

    private final MoneyJarRepository moneyJarRepository;

    private final MoneyJarMapper moneyJarMapper = Mappers.getMapper(MoneyJarMapper.class);

    @Override
    public MoneyJarDto create(MoneyJarDto moneyJarDto) {
        MoneyJar moneyJar = moneyJarMapper.moneyJarDtoToMoneyJar(moneyJarDto);
        MoneyJar newMoneyJar = new MoneyJar();
        newMoneyJar.setName(moneyJar.getName());
        newMoneyJar.setDescription(moneyJar.getDescription());
        newMoneyJar.setGoal(moneyJar.getGoal());
        newMoneyJar = moneyJarRepository.save(newMoneyJar);
        if (moneyJarRepository.findById(newMoneyJar.getId()).isPresent()) {
            log.info("create(): money jar with id {}", newMoneyJar.getId());
        } else {
            log.info("create(): failed to create money jar");
            throw new ResourceCreationException("Failed to create money jar");
        }
        return moneyJarMapper.moneyJarToMoneyJarDto(newMoneyJar);
    }

    @Override
    public MoneyJarDto getById(Long id) {
        MoneyJar moneyJar = moneyJarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found money jar with id " + id));
        log.info("get(): money jar id " + id);
        GoalAchievedUtil.checkGoalAchievement(moneyJar);
        return moneyJarMapper.moneyJarToMoneyJarDto(moneyJarRepository.save(moneyJar));
    }

    @Override
    public List<MoneyJarDto> getAll() {
        List<MoneyJar> moneyJarList = moneyJarRepository.findAll();
        if (moneyJarList.isEmpty()) {
            throw new ResourceNotFoundException("There are no money jar yet");
        } else {
            log.info("getAll(): retrieved all money jars");
            return moneyJarMapper.moneyJarListToDto(moneyJarList);
        }
    }

    @Override
    public MoneyJarDto update(MoneyJarDto moneyJarDto, Long id) {
        MoneyJarDto newMoneyJarDto = getById(id);
        newMoneyJarDto.setName(moneyJarDto.getName());
        newMoneyJarDto.setDescription(moneyJarDto.getDescription());
        newMoneyJarDto.setJarBalance(newMoneyJarDto.getJarBalance());
        newMoneyJarDto.setGoal(moneyJarDto.getGoal());
        MoneyJar moneyJar = moneyJarMapper.moneyJarDtoToMoneyJar(newMoneyJarDto);
        GoalAchievedUtil.checkGoalAchievement(moneyJar);
        moneyJar = moneyJarRepository.save(moneyJar);
        log.info("update(): new money jar with id " + id);
        return moneyJarMapper.moneyJarToMoneyJarDto(moneyJar);

    }

    @Override
    public void delete(Long id) {
        MoneyJar moneyJar = moneyJarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found money jar with id " + id));
        log.info("get(): money jar id " + id);
        moneyJarRepository.delete(moneyJar);
        log.info("delete(): money jar id " + id + " deleted");
    }
}
