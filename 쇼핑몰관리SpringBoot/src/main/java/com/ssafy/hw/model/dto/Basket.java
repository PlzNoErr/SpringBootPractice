package com.ssafy.hw.model.dto;

public class Basket {
	
	private int basketId;
	private String pCode;
	private String userId;
	private String fileName;
	private String pName;
	private String price;
	
	public int getBasketId() {
		return basketId;
	}
	public void setBasketId(int basketId) {
		this.basketId = basketId;
	}
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Basket [basketId=" + basketId + ", pCode=" + pCode + ", userId=" + userId + ", fileName=" + fileName
				+ ", pName=" + pName + ", price=" + price + "]";
	}

	

	
	
}
