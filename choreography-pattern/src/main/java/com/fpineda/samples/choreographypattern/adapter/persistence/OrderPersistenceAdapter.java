/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:31:46
 * @modify date 2020-10-08 23:31:46
 * @desc Order Adapter for operatations on Order table
 */
package com.fpineda.samples.choreographypattern.adapter.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import com.fpineda.samples.choreographypattern.core.command.PlaceOrderCommand;
import com.fpineda.samples.choreographypattern.core.exception.OrderNotFoundException;
import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.model.OrderStatus;
import com.fpineda.samples.choreographypattern.core.ports.FetchOrderPort;
import com.fpineda.samples.choreographypattern.core.ports.PlaceOrderPort;
import com.fpineda.samples.choreographypattern.core.ports.UpdateOrderStatusPort;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


public class OrderPersistenceAdapter implements PlaceOrderPort, FetchOrderPort, UpdateOrderStatusPort {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public OrderPersistenceAdapter(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("orders")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Order placeOrder(PlaceOrderCommand command) {
        Order order = command.toOrder();
        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put("customer_id", order.getCustomerId());
        parameters.put("quantity", order.getQuantity());
        parameters.put("product_id", order.getProductId());
        parameters.put("status", order.getStatus().getValue());

        long orderId = simpleJdbcInsert.executeAndReturnKey(parameters).longValue();        
        order.setId(orderId);

        return order;
    }

    @Override
    public Order fetch(long orderId) {
        var sql = "select * from orders where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[] {orderId}, new OrderMapper());    
        } catch (EmptyResultDataAccessException e) {
            throw new OrderNotFoundException();
        }      
    }

    public class OrderMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Order.builder().id(rs.getLong("id")).customerId(rs.getLong("customer_id"))
                    .productId(rs.getLong("product_id")).quantity(rs.getInt("quantity"))
                    .status(OrderStatus.from(rs.getInt("status"))).build();
        }
    }

    @Override
    public boolean updateStatus(long orderId, OrderStatus status) {
        var sql = "UPDATE orders set status = ? where id = ?";        
        return jdbcTemplate.update(sql, status.getValue(), orderId) == 1;        
    }

}
