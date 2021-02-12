package com.domariev.financialproject.service;

import com.domariev.financialproject.dto.WishDto;

import java.util.List;

public interface WishService {

    WishDto create(WishDto wishDto);

    WishDto getById(Long id);

    List<WishDto> getAll();

    WishDto update(WishDto wishDto, Long id);

    void delete(Long id);

    WishDto updateImplementedStatus(Boolean implemented, Long id);
}
