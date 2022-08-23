package com.example.shopproject.service;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.entity.dto.BasketDto;
import com.example.shopproject.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService{

    private final BasketRepository basketRepository;

    private final ModelMapper modelMapper;

    @Override
    public BasketDto createBasket(BasketDto basket){
        basketRepository.save(modelMapper.map(basket, Basket.class));
        return basket;
    }

    @Override
    public Page<BasketDto> getAllBaskets(Pageable pageable){
        return new PageImpl<>(basketRepository.findAll(pageable).stream()
                .map(basket -> modelMapper.map(basket,BasketDto.class))
                .collect(Collectors.toList()));
    }

    @Override
    public BasketDto getBasketById(Long id){
        return modelMapper.map(basketRepository.getReferenceById(id), BasketDto.class);
    }

    @Override
    public BasketDto updateBasket(BasketDto basket){
        basketRepository.save(modelMapper.map(basket, Basket.class));
        return basket;
    }

    @Override
    public void deleteBasketById(Long id){
        basketRepository.deleteById(id);
    }
}
