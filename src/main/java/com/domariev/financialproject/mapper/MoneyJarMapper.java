package com.domariev.financialproject.mapper;

import com.domariev.financialproject.dto.MoneyJarDto;
import com.domariev.financialproject.model.MoneyJar;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MoneyJarMapper {

    MoneyJarDto moneyJarToMoneyJarDto(MoneyJar moneyJar);

    MoneyJar moneyJarDtoToMoneyJar(MoneyJarDto moneyJarDto);

    List<MoneyJarDto> moneyJarListToDto(List<MoneyJar> moneyJarList);
}
