package org.sharding.inline.dao.mybatis.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderDO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer userId;
	
	private Integer orderId;

}
