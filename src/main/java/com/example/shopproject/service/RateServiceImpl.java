package com.example.shopproject.service;

import com.example.shopproject.entity.Rate;
import com.example.shopproject.entity.dto.RateDto;
import com.example.shopproject.repository.RateRepository;
import com.example.shopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RateServiceImpl implements RateService{
    private final RateRepository rateRepository;
    private final ModelMapper modelMapper;

    @Override
    public RateDto createRate(RateDto rate) {
        rateRepository.save(modelMapper.map(rate, Rate.class));
        return rate;
    }

    @Override
    public List<Rate> getAllRates(Pageable pageable) {
        return rateRepository.findAll(pageable).getContent();
    }

    @Override
    public RateDto getRateById(Long id) {
        return modelMapper.map(rateRepository.getReferenceById(id), RateDto.class);
    }

    @Override
    public RateDto updateRate(RateDto rate) {
        rateRepository.save(modelMapper.map(rate, Rate.class));
        return rate;
    }

    @Override
    public void deleteRateById(Long id) {
        rateRepository.deleteById(id);
    }
}
