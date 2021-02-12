package com.domariev.financialproject.service.impl;

import com.domariev.financialproject.dto.WishDto;
import com.domariev.financialproject.exception.ResourceCreationException;
import com.domariev.financialproject.exception.ResourceNotFoundException;
import com.domariev.financialproject.mapper.WishMapper;
import com.domariev.financialproject.model.Wish;
import com.domariev.financialproject.repository.WishRepository;
import com.domariev.financialproject.service.WishService;
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
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;

    private final WishMapper wishMapper = Mappers.getMapper(WishMapper.class);

    @Override
    public WishDto create(WishDto wishDto) {
        Wish wish = wishMapper.wishDtoToWish(wishDto);
        Wish newWish = new Wish();
        newWish.setName(wish.getName());
        newWish.setPrice(wish.getPrice());
        newWish.setDateToBuy(wish.getDateToBuy());
        newWish.setImplemented(false);
        newWish = wishRepository.save(newWish);
        if (wishRepository.findById(newWish.getId()).isPresent()) {
            log.info("create(): wish with id {}", newWish.getId());
        } else {
            log.info("create(): failed to create wish");
            throw new ResourceCreationException("Failed to create wish");
        }
        return wishMapper.wishToWishDto(newWish);
    }

    @Override
    public WishDto getById(Long id) {
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found wish with id " + id));
        log.info("get(): cashbook id" + id);
        return wishMapper.wishToWishDto(wish);
    }

    @Override
    public List<WishDto> getAll() {
        List<Wish> wishList = wishRepository.findAll();
        if (wishList.isEmpty()) {
            throw new ResourceNotFoundException("There are no wishes yet");
        } else {
            log.info("getAll(): retrieved all wishes");
            return wishMapper.wishListToDto(wishList);
        }
    }

    @Override
    public WishDto update(WishDto wishDto, Long id) {
        WishDto newWishDto = getById(id);
        newWishDto.setName(wishDto.getName());
        newWishDto.setPrice(wishDto.getPrice());
        newWishDto.setDateToBuy(wishDto.getDateToBuy());
        Wish wish = wishMapper.wishDtoToWish(newWishDto);
        wish = wishRepository.save(wish);
        log.info("update(): new wish with id " + id);
        return wishMapper.wishToWishDto(wish);

    }

    @Override
    public void delete(Long id) {
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found wish with id " + id));
        log.info("get(): wish id " + id);
        wishRepository.delete(wish);
        log.info("delete(): wish " + id + " deleted");
    }

    @Override
    public WishDto updateImplementedStatus(Boolean implemented, Long id) {
        WishDto wishDto = getById(id);
        wishRepository.setUserById(implemented, id);
        wishDto.setImplemented(implemented);
        return wishDto;
    }
}
