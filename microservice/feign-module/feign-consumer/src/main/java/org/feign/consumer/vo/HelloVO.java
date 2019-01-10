package org.feign.consumer.vo;

import java.io.Serializable;

/**
 * @Title: HelloVO.java
 * @date: 2018年12月17日 下午5:52:33
 */
public class HelloVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
