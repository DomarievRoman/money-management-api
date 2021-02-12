package com.domariev.financialproject.mapper;

import com.domariev.financialproject.dto.WishDto;
import com.domariev.financialproject.model.Wish;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface WishMapper {

    WishDto wishToWishDto(Wish wish);

    Wish wishDtoToWish(WishDto wishDto);

    List<WishDto> wishListToDto(List<Wish> wishList);
}
