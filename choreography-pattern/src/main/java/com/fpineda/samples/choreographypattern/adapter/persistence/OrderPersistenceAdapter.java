/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:31:46
 * @modify date 2020-10-08 23:31:46
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.adapter.persistence;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import com.fpineda.samples.choreographypattern.core.command.PlaceOrderCommand;
import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.ports.PlaceOrderPort;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderPersistenceAdapter implements PlaceOrderPort {

    private final SimpleJdbcInsert simpleJdbcInsert;    

    public OrderPersistenceAdapter(DataSource dataSource) {        
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("orders")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Order placeOrder(PlaceOrderCommand command) {
        Order order = command.toOrder();
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("customer_id", order.getCustomerId());
        parameters.put("quantity", order.getQuantity());
        parameters.put("product_id", order.getProductId());
        parameters.put("status", order.getStatus().getValue());
        
        long orderId = simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
        log.info("order created: {}", orderId);

        
        order.setId(orderId);
        
        return order;
    }

}
