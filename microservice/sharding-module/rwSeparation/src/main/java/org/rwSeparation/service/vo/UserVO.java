package org.rwSeparation.service.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 * 
	 */
	private Long id;
	/**
	 * 名字
	 * 
	 */
	private String name;
	/**
	 * 年龄
	 * 
	 */
	private Integer age;
	/**
	 * 备注
	 * 
	 */
	private String remark;

}
