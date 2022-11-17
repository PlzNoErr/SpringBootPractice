package com.ssafy.hw.model.service;

import java.util.List;

import com.ssafy.hw.model.dto.Basket;

public interface BasketService {
	
	int registBasket(Basket basket);
	int deleteBasket(int basketId);
	List<Basket> selectAllBasket(String userId);
}
