package com.example.cloud.service;

import com.example.cloud.dto.WeatherResponse;

/**
 * @Title: WeatherDataService.java
 * @date: 2018年10月29日 下午4:18:30
 */
public interface WeatherDataService {

	/**
	 * 根据城市ID查询天气数据
	 * 
	 * @param cityId
	 * @return
	 */
	WeatherResponse getDataByCityId(String cityId);

	/**
	 * 根据城市名称查询天气数据
	 * 
	 * @param cityId
	 * @return
	 */
	WeatherResponse getDataByCityName(String cityName);
}
