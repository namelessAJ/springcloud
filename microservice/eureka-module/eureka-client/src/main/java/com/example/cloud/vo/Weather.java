package com.example.cloud.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: Weather.java
 * @date: 2018年10月29日 下午4:09:02
 */
public class Weather implements Serializable {

	private static final long serialVersionUID = 1L;

	private String city;
	private String aqi;
	private String wendu;
	private String ganmao;
	private Yesterday yesterday;
	private List<Forecast> forecast;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAqi() {
		return aqi;
	}

	public void setAqi(String aqi) {
		this.aqi = aqi;
	}

	public String getWendu() {
		return wendu;
	}

	public void setWendu(String wendu) {
		this.wendu = wendu;
	}

	public String getGanmao() {
		return ganmao;
	}

	public void setGanmao(String ganmao) {
		this.ganmao = ganmao;
	}

	public Yesterday getYesterday() {
		return yesterday;
	}

	public void setYesterday(Yesterday yesterday) {
		this.yesterday = yesterday;
	}

	public List<Forecast> getForecast() {
		return forecast;
	}

	public void setForecast(List<Forecast> forecast) {
		this.forecast = forecast;
	}

}
