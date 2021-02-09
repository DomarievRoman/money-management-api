package com.domariev.financialproject.mapper;

import com.domariev.financialproject.dto.CostsDto;
import com.domariev.financialproject.model.Costs;
import org.mapstruct.Mapper;

@Mapper
public interface CostsMapper {

    CostsDto costsToCostsDto(Costs costs);

    Costs costsDtoToCosts(CostsDto costsDto);
}
