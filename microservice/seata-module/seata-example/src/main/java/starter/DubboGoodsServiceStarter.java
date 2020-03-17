package starter;

import applications.ApplicationKeeper;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class DubboGoodsServiceStarter {
    /**
     * 1. Goods service is ready . A seller add 100 goods to a sku: C00321
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext goodsContext = new ClassPathXmlApplicationContext(
                new String[]{"spring/dubbo-goods-service.xml"});
        goodsContext.getBean("service");
        JdbcTemplate goodsJdbcTemplate = (JdbcTemplate) goodsContext.getBean("jdbcTemplate");
        goodsJdbcTemplate.update("delete from goods_tbl where commodity_code = 'C00321'");
        goodsJdbcTemplate.update("insert into goods_tbl(commodity_code, count) values ('C00321', 100)");
        new ApplicationKeeper(goodsContext).keep();
    }
}
