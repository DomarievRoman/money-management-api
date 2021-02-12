package com.domariev.financialproject.mapper;

import com.domariev.financialproject.dto.CostsDto;
import com.domariev.financialproject.model.Costs;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CostsMapper {

    CostsDto costsToCostsDto(Costs costs);

    Costs costsDtoToCosts(CostsDto costsDto);

    List<CostsDto> costsListToDto(List<Costs> costsList);
}
