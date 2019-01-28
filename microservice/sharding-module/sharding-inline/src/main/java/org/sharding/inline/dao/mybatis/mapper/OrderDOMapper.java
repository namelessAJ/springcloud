package org.sharding.inline.dao.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.sharding.inline.dao.mybatis.mapper.common.BaseMapper;
import org.sharding.inline.dao.mybatis.models.OrderDO;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDOMapper extends BaseMapper<OrderDO> {

	/**
	 * @param userId
	 * @return
	 */
	List<OrderDO> findByUserId(Integer userId);

	/**
	 * 范围查询
	 * 
	 * @param map
	 * @return
	 */
	List<OrderDO> findByUserIdBetween(Map<String, Integer> map);

	/**
	 * @param orderID
	 * @return
	 */
	List<OrderDO> findByOrderId(Integer orderID);
}
