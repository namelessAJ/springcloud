package org.sharding.inline.dao.mybatis.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer userId;

    private Integer orderId;

}
