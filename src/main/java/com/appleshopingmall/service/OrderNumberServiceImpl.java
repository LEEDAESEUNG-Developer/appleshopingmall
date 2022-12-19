package com.appleshopingmall.service;

import com.appleshopingmall.entity.OrderNumberEntity;
import com.appleshopingmall.repository.OrderNumberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderNumberServiceImpl implements OrderNumberService {

    private final OrderNumberRepository repository;

    @Override
    public List<OrderNumberEntity> findByMemberId(Long memberId) {
        return repository.findByMemberId(memberId);
    }
}
