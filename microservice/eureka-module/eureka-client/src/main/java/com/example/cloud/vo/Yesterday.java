package com.example.cloud.vo;

import java.io.Serializable;

/**
 * @Title: Yesterday.java
 * @date: 2018年10月29日 下午4:11:17
 */
public class Yesterday implements Serializable {

	private static final long serialVersionUID = 1L;

	private String date;
	private String high;
	private String fx;
	private String low;
	private String fl;
	private String type;

	public Yesterday() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getFx() {
		return fx;
	}

	public void setFx(String fx) {
		this.fx = fx;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getFl() {
		return fl;
	}

	public void setFl(String fl) {
		this.fl = fl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
