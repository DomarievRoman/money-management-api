package com.domariev.financialproject.mapper;

import com.domariev.financialproject.dto.CashbookDto;
import com.domariev.financialproject.model.Cashbook;
import org.mapstruct.Mapper;

@Mapper
public interface CashbookMapper {

    CashbookDto cashbookToCashbookDto(Cashbook cashbook);

    Cashbook cashbookDtoToCashbook(CashbookDto cashbookDto);

}
