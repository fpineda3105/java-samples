/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-14 09:07:29
 * @modify date 2020-10-14 09:07:29
 * @desc Paying order Business Logic use case.
 */
package com.fpineda.samples.choreographypattern.service;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import javax.sql.DataSource;
import com.fpineda.samples.choreographypattern.adapter.persistence.OrderPersistenceAdapter;
import com.fpineda.samples.choreographypattern.config.DatabaseInMemoryConfig;
import com.fpineda.samples.choreographypattern.core.model.OrderStatus;
import com.fpineda.samples.choreographypattern.core.ports.FetchOrderPort;
import com.fpineda.samples.choreographypattern.core.ports.UpdateOrderStatusPort;
import com.fpineda.samples.choreographypattern.core.usecase.PayOrderUseCase;
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
class PayOrderUseCaseTest {

    @Autowired
    private DataSource datasource;

    private PayOrderUseCase payOrderUseCase;
    private FetchOrderPort fetchOrderPort;
    private UpdateOrderStatusPort orderStatusPort;

    @BeforeEach
    public void setUp() {
        var orderPersistence = new OrderPersistenceAdapter(datasource);

        //Ports
        fetchOrderPort = spy(orderPersistence);
        orderStatusPort = spy(orderPersistence);
        
        payOrderUseCase = new PayOrderService(fetchOrderPort, orderStatusPort);
    }

    @Test
    @Sql(scripts = {"/order_table.sql", "/place_orders.sql"})
    void shouldSet_TheOrder_WithStatus_Paid() {
       payOrderUseCase.payOrder(1L).join(); 
       verify(fetchOrderPort, times(1)).fetch(1L);
       verify(orderStatusPort, times(1)).updateStatus(1L, OrderStatus.PAID);
       Assertions.assertEquals(OrderStatus.PAID, fetchOrderPort.fetch(1L).getStatus());
    }
}
