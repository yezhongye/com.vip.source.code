package com.study.design.mode.service;

import org.springframework.stereotype.Service;

import com.study.design.mode.entity.Order;

@Service
public class DefaultPromotionCalculation implements PromotionCalculation {

	@Override
	public Order calculate(Order o) {
		// 不打折
		return o;
	}

}
