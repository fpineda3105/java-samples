package com.fpineda.samples.choreographypattern.adapter.persistence;

import javax.sql.DataSource;
import com.fpineda.samples.choreographypattern.config.DatabaseInMemoryConfig;
import com.fpineda.samples.choreographypattern.core.exception.OrderNotFoundException;
import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.model.OrderStatus;
import com.fpineda.samples.choreographypattern.core.ports.FetchOrderPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {DatabaseInMemoryConfig.class})
class FetchOrderPortTest {
      
    private FetchOrderPort fetchOrderPort;

    @Autowired
    private DataSource datasource;

    @BeforeEach
    public void setUp() {
        fetchOrderPort = new OrderPersistenceAdapter(datasource);
    }
    
    @Test
    @Sql(scripts = {"/order_table.sql", "/place_orders.sql"})
    void should_fetchOrder_ById() {
        long orderId = 1L;
        Order result = fetchOrderPort.fetch(orderId);
        Assertions.assertEquals(OrderStatus.PLACED, result.getStatus());       
    }

    @Test
    @Sql(scripts = {"/order_table.sql"})
    void should_throw_notfoundexception() {
        long orderId = 400L;       
        Assertions.assertThrows(OrderNotFoundException.class, () -> fetchOrderPort.fetch(orderId));
    }
}
