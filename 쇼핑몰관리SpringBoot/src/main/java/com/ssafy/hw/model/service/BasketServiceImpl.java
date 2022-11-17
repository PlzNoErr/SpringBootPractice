package com.ssafy.hw.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.hw.model.dao.BasketDao;
import com.ssafy.hw.model.dto.Basket;

@Service
public class BasketServiceImpl implements BasketService{
	
	@Autowired
	BasketDao bdao;

	@Override
	public int registBasket(Basket basket) {
		return bdao.insertBasket(basket);
	}

	@Override
	public int deleteBasket(int basketId) {
		return bdao.deleteBasket(basketId);
	}

	@Override
	public List<Basket> selectAllBasket(String userId) {
		return bdao.selectAllBasket(userId);
	}
	
	
}
