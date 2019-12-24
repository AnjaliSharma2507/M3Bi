package com.m3bi.hotelbooking.model;

public class CustomResponse<T> {
	private Boolean isSuccess;
	private CustomError error;
	private T data;
	
	public CustomResponse(Boolean isSuccess, CustomError error, T data) {
		super();
		this.isSuccess = isSuccess;
		this.error = error;
		this.data = data;
	}
	
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public CustomError getError() {
		return error;
	}
	public void setError(CustomError error) {
		this.error = error;
	}
	public Object getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	

}
