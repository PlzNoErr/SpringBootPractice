package com.ssafy.hw.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hw.model.dto.Product;
import com.ssafy.hw.model.dto.Review;
import com.ssafy.hw.model.dto.SearchCondition;
import com.ssafy.hw.model.service.ProductService;
import com.ssafy.hw.model.service.ReviewService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/productapi")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class ProductRestController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/product/{pCode}")
	@ApiOperation(value = "{pCode}에 해당하는 상품 정보를 반환한다.", response = Product.class)
	public ResponseEntity<?> getProduct(@PathVariable String pCode){
		try {
			Product product = productService.selectProductBypCode(pCode);
			if(product != null) {
				return new ResponseEntity<Product>(product, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e){
			return exceptionHandling(e);		
		}
	}
	
	@GetMapping("/product")
	@ApiOperation(value = "query string에 해당하는 검색 조건에 해당하는 상품목록을 반환한다.", response = Product.class)
	public ResponseEntity<?> searchProduct(SearchCondition condition){
		try {
			Map<String, Object> result = productService.selectProductBySearchConditionWithPaging(condition);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping(value = "/product")
	@ApiOperation(value="Product 객체를 저장한다.", response = Integer.class)
	public ResponseEntity<?> registProduct(@RequestBody Product product){
		try {
			int result = productService.insertProduct(product);
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/product")
	@ApiOperation(value="Product 객체를 수정한다.", response = Integer.class)
	public ResponseEntity<?> updateProduct(@RequestBody Product product){
		try {
			int result = productService.updateProduct(product);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/product/{pCode}")
	@ApiOperation(value = "Product 객체를 삭제한다.")
	public ResponseEntity<?> deleteProduct(@PathVariable String pCode){
		try {
			int result = productService.deleteProduct(pCode);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/review")
	@ApiOperation(value = "상품에 대해 리뷰를 작성합니다.", response = Integer.class)
	public ResponseEntity<?> registReview(@RequestBody Review review) {
		try {
			int result = reviewService.insertReview(review);
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}
	
	@GetMapping("/review/{pCode}")
	@ApiOperation(value = "해당 상품의 리뷰를 모두 가져옵니다.", response = Review.class)
	public ResponseEntity<?> getReviewList(@PathVariable String pCode){
		try {
			List<Review> list = reviewService.selectAllReview(pCode);
			return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/review/{reviewId}")
	@ApiOperation(value="상품에 등록된 리뷰를 삭제합니다.", response = Integer.class)
	public ResponseEntity<?> deleteReview(@PathVariable Integer reviewId){
		try {
			int result = reviewService.deleteReview(reviewId);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping(value="/download"
			,produces= MediaType.APPLICATION_OCTET_STREAM_VALUE )
	@ResponseBody
	public ResponseEntity<Resource> getDownLoad(String fileName) {
		
		String filePath = "C:\\temp\\" + fileName;
		
		Resource res = new FileSystemResource(filePath);
		
		String resourceName = res.getFilename();
		
		resourceName = resourceName.substring(resourceName.indexOf("_") + 1);
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", "attachment; filename=" + new String(resourceName.getBytes("UTF-8") , "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(res, headers, HttpStatus.OK);
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
