package com.ssafy.hw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hw.model.dto.Basket;
import com.ssafy.hw.model.service.BasketService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/basketapi")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class BasketRestController {
	@Autowired
	BasketService bService;
	
	@PostMapping()
	@ApiOperation(value = "장바구니 정보를 삽입한다.")
	public ResponseEntity<?> registBasket(@RequestBody Basket basket){
		int result = bService.registBasket(basket);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{basketId}")
	@ApiOperation(value = "{basketId}에 해당하는 장바구니 상품을 삭제한다.")
	public ResponseEntity<?> deleteBasket(@PathVariable Integer basketId){
		int result = bService.deleteBasket(basketId);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping()
	@ApiOperation(value = "해당 사용자의 장바구니 정보를 모두 가져온다.")
	public ResponseEntity<?> getBasketList(String userId){
		List<Basket> list = bService.selectAllBasket(userId);
		return new ResponseEntity<List<Basket>> (list, HttpStatus.OK);
	}
}
