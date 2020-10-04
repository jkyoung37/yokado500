package com.jp.yokado.model;

import javax.validation.constraints.Min;


public class Receipt {

	@Min(1)
    private Integer point;

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}
}