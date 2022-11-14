package com.ssafy.hw.model.service;

import java.util.List;

import com.ssafy.hw.model.dto.Review;

public interface ReviewService {
	
	int insertReview(Review rev);
	
	int deleteReview(int revId);
	
	List<Review> selectAllReview(String pCode);

}
