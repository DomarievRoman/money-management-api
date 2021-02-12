package com.domariev.financialproject.service.impl;

import com.domariev.financialproject.dto.ContributionDto;
import com.domariev.financialproject.exception.ResourceCreationException;
import com.domariev.financialproject.exception.ResourceNotFoundException;
import com.domariev.financialproject.mapper.ContributionMapper;
import com.domariev.financialproject.model.Contribution;
import com.domariev.financialproject.model.MoneyJar;
import com.domariev.financialproject.repository.ContributionRepository;
import com.domariev.financialproject.repository.MoneyJarRepository;
import com.domariev.financialproject.service.ContributionService;
import com.domariev.financialproject.util.GoalAchievedUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ContributionServiceImpl implements ContributionService {

    private final ContributionRepository contributionRepository;

    private final MoneyJarRepository moneyJarRepository;

    private final ContributionMapper contributionMapper = Mappers.getMapper(ContributionMapper.class);

    @Override
    public ContributionDto add(ContributionDto contributionDto, Long id) {
        Contribution contribution = contributionMapper.contributionDtoToContribution(contributionDto);
        Contribution newContribution = new Contribution();
        MoneyJar moneyJar = moneyJarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found money jar id" + id));
        newContribution.setAmounting(contribution.getAmounting());
        newContribution.setComment(contribution.getComment());
        moneyJar.getJarBalance().add(newContribution);
        GoalAchievedUtil.checkGoalAchievement(moneyJar);
        newContribution = contributionRepository.save(newContribution);
        if (contributionRepository.findById(newContribution.getId()).isPresent()) {
            log.info("add(): contribution with id " + newContribution.getId());
        } else {
            log.info("add(): failed to add contribution");
            throw new ResourceCreationException("Failed to add contribution");
        }
        return contributionMapper.contributionToContributionDto(newContribution);
    }

    @Override
    public ContributionDto getById(Long id) {
        Contribution contribution = contributionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found contribution with id " + id));
        log.info("get(): contribution id " + id);
        return contributionMapper.contributionToContributionDto(contribution);
    }

    @Override
    public List<ContributionDto> getAll() {
        List<Contribution> contributionList = contributionRepository.findAll();
        if (contributionList.isEmpty()) {
            throw new ResourceNotFoundException("There are no contributions yet");
        } else {
            log.info("getAll(): retrieved all contributions");
            return contributionMapper.contributionListToDto(contributionList);
        }
    }

    @Override
    public ContributionDto update(ContributionDto contributionDto, Long id) {
        ContributionDto newContributionDto = getById(id);
        newContributionDto.setAmounting(contributionDto.getAmounting());
        newContributionDto.setComment(contributionDto.getComment());
        Contribution contribution = contributionMapper.contributionDtoToContribution(newContributionDto);
        contribution = contributionRepository.save(contribution);
        log.info("update(): new wish with id " + id);
        return contributionMapper.contributionToContributionDto(contribution);

    }

    @Override
    public void delete(Long id) {
        ContributionDto contributionDto = getById(id);
        Contribution contribution = contributionMapper.contributionDtoToContribution(contributionDto);
        contributionRepository.delete(contribution);
        log.info("delete(): contribution id " + id + " deleted");
    }
}
