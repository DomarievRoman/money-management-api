package com.domariev.financialproject.mapper;

import com.domariev.financialproject.dto.IncomeDto;
import com.domariev.financialproject.model.Income;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IncomeMapper {

    IncomeDto incomeToIncomeDto(Income income);

    Income incomeDtoToIncome(IncomeDto incomeDto);

    List<IncomeDto> incomeListToDto(List<Income> incomeList);
}
