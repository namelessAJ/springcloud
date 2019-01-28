package org.sharding.inline;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharding.inline.dao.mybatis.mapper.OrderDOMapper;
import org.sharding.inline.dao.mybatis.models.OrderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

	@Autowired
	private OrderDOMapper orderDOMapper;

	@Test
	public void contextLoads() {
	}

	/**
	 * 单片ID切分添加
	 */
	@Test
	public void add() {
		OrderDO order = new OrderDO();
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 30; j++) {
				order.setUserId(i);
				order.setOrderId(j);
				orderDOMapper.insert(order);
			}
		}
	}

	/**
	 * 单片查询
	 */
	@Test
	public void getByUserId() {
		List<OrderDO> list = orderDOMapper.findByUserId(20);
		for (OrderDO order : list) {
			System.out.println(order);
		}

	}
}
