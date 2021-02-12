package com.domariev.financialproject.mapper;

import com.domariev.financialproject.dto.ContributionDto;
import com.domariev.financialproject.model.Contribution;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ContributionMapper {

    ContributionDto contributionToContributionDto(Contribution contribution);

    Contribution contributionDtoToContribution(ContributionDto contributionDto);

    List<ContributionDto> contributionListToDto(List<Contribution> contributionList);
}
