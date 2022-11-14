package com.ssafy.hw.model.dao;

import java.util.List;

import com.ssafy.hw.model.dto.Basket;

public interface BasketDao {
	int insertBasket(Basket basket);
	
	int deleteBasket(int basketId);
	
	List<Basket> selectAllBasket(String userId);
	
}
