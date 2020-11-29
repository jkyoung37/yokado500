package com.jp.yokado.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Receipt {

	@Min(1)
	@Max(99999999)
	private Integer point;
	private Integer errorCode = 0;
    private String errorMessage;
    

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}