package com.example.shopproject.service;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.entity.dto.BasketDto;
import com.example.shopproject.repository.BasketRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BasketServiceImpl implements BasketService{

    private final BasketRepository basketRepository;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public BasketDto createBasket(Basket basket){
        Basket basketEntity = basketRepository.save(basket);
        return modelMapper.map(basketEntity, BasketDto.class);
    }

    @Override
    public List<Basket> getAllBasketsByUserId(Long userId){
        return basketRepository.getBasketsByUserId(userId);
    }

    @Override
    public BasketDto getBasketById(Long id){
        return modelMapper.map(basketRepository.getById(id), BasketDto.class);
    }

    @Override
    public BasketDto updateBasket(Basket basket){
        Basket basketEntity = basketRepository.save(basket);
        return modelMapper.map(basketEntity, BasketDto.class);
    }

    @Override
    public void deleteBasketById(Long id){
        basketRepository.deleteById(id);
    }
}
