package service.impl;

import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsService.class);

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void deduct(String commodityCode, int count) {
        LOGGER.info("goods Service Begin ... xid: " + RootContext.getXID());
        LOGGER.info("Deducting inventory SQL: update goods_tbl set count = count - {} where commodity_code = {}",
                count, commodityCode);

        jdbcTemplate.update("update goods_tbl set count = count - ? where commodity_code = ?",
                new Object[]{count, commodityCode});
        LOGGER.info("goods Service End ... ");

    }
}
