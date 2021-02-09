package com.domariev.financialproject.mapper;

import com.domariev.financialproject.dto.IncomeDto;
import com.domariev.financialproject.model.Income;
import org.mapstruct.Mapper;

@Mapper
public interface IncomeMapper {

    IncomeDto incomeToIncomeDto(Income income);

    Income incomeDtoToIncome(IncomeDto incomeDto);
}
