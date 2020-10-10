/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-09 11:08:46
 * @modify date 2020-10-09 11:08:46
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.service;

import javax.sql.DataSource;
import com.fpineda.samples.choreographypattern.adapter.persistence.OrderPersistenceAdapter;
import com.fpineda.samples.choreographypattern.adapter.persistence.ProductPersistenceAdapter;
import com.fpineda.samples.choreographypattern.config.DatabaseInMemoryConfig;
import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.model.OrderStatus;
import com.fpineda.samples.choreographypattern.core.ports.CommitProductStockPort;
import com.fpineda.samples.choreographypattern.core.ports.FetchOrderPort;
import com.fpineda.samples.choreographypattern.core.ports.FetchProductPort;
import com.fpineda.samples.choreographypattern.core.ports.UpdateOrderStatusPort;
import com.fpineda.samples.choreographypattern.core.usecase.CommitOrderUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = DatabaseInMemoryConfig.class)
public class CommitOrderUseCaseTest {

    private CommitOrderUseCase commitOrderUseCase;

    @Autowired
    private DataSource datasource;

    @BeforeEach
    public void reset() {
        // Persistence adapters
        var productPersistence = new ProductPersistenceAdapter(datasource);
        var orderPersistence = new OrderPersistenceAdapter(datasource);

        //Ports
        FetchOrderPort fetchOrder = orderPersistence;
        CommitProductStockPort productStockPort = productPersistence;
        FetchProductPort fetchProductPort = productPersistence;
        UpdateOrderStatusPort orderStatusPort = orderPersistence;

        //Service
        commitOrderUseCase = new CommitOrderService(fetchOrder, productStockPort, fetchProductPort,
                orderStatusPort);
    }

    @Test
    @Sql(scripts = {"/order_table.sql", "/place_orders.sql", "/products_table.sql",
            "/populate_products.sql"})
    public void shouldReturn_TheOrder_WithStatus_Commited() {
        Order result = commitOrderUseCase.commitOrder(1L);        
        Assertions.assertEquals(OrderStatus.COMMITTED, result.getStatus());
    }



}
