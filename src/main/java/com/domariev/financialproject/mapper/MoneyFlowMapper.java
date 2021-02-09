package com.domariev.financialproject.mapper;

import com.domariev.financialproject.dto.MoneyFlowDto;
import com.domariev.financialproject.model.abstractFlow.MoneyFlow;
import org.mapstruct.Mapper;

@Mapper
public interface MoneyFlowMapper {

    MoneyFlowDto moneyFlowToMoneyFlowDto(MoneyFlow moneyFlow);

    MoneyFlow moneyFlowDtoToMoneyFlow(MoneyFlowDto moneyFlowDto);
}
