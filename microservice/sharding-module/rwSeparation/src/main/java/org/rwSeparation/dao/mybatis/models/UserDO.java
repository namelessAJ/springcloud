package org.rwSeparation.dao.mybatis.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 * 
	 */
	private Integer id;
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
