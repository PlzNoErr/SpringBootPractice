package com.ssafy.hw.model.service;

import java.util.Map;

import com.ssafy.hw.model.dto.Product;
import com.ssafy.hw.model.dto.SearchCondition;

public interface ProductService {
	
	int insertProduct(Product product);
	
	int updateProduct(Product product);
	
	int deleteProduct(String pCode);
	
	Product selectProductBypCode(String pCode);
	
	Map<String, Object> selectProductBySearchConditionWithPaging(SearchCondition con);
	

}
