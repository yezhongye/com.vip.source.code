package com.study.design.mode.service;

import com.study.design.mode.entity.Order;

public interface PromotionCalculation {
	/**
	 * 计算订单金额
	 * @param o 
	 * @return
	 */
	Order calculate(Order o);
}
