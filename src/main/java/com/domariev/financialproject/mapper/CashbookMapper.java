package com.domariev.financialproject.mapper;

import com.domariev.financialproject.dto.CashbookDto;
import com.domariev.financialproject.dto.ContributionDto;
import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.model.Contribution;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CashbookMapper {

    CashbookDto cashbookToCashbookDto(Cashbook cashbook);

    Cashbook cashbookDtoToCashbook(CashbookDto cashbookDto);

    List<CashbookDto> cashbookListToDto(List<Cashbook> cashbookList);

}
