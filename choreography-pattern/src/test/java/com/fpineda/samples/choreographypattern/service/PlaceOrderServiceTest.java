/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:32:09
 * @modify date 2020-10-08 23:32:09
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.service;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import javax.sql.DataSource;
import com.fpineda.samples.choreographypattern.adapter.persistence.OrderPersistenceAdapter;
import com.fpineda.samples.choreographypattern.config.DatabaseInMemoryConfig;
import com.fpineda.samples.choreographypattern.core.command.PlaceOrderCommand;
import com.fpineda.samples.choreographypattern.core.event.PlaceOrderEventSourced;
import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.ports.PlaceOrderPort;
import com.fpineda.samples.choreographypattern.core.usecase.PlaceOrderUseCase;
import com.google.common.eventbus.EventBus;
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
public class PlaceOrderServiceTest {

    private PlaceOrderUseCase placeOrderUseCase;
    private PlaceOrderPort port;

    private PlaceOrderEventSourced eventSourcedSpy;

    @Autowired
    private DataSource datasource;

    @BeforeEach
    public void reset() {        
        EventBus eventBus = new EventBus();
        PlaceOrderEventSourced eventsourced = new PlaceOrderEventSourced(eventBus);
        eventSourcedSpy = spy(eventsourced);
        port = new OrderPersistenceAdapter(datasource);
        placeOrderUseCase = new PlaceOrderService(eventSourcedSpy, port);
    }

    @Test
    @Sql(scripts = {"/order_table.sql"})
    public void placeOrderInDbAndEmitEvent() {
        PlaceOrderCommand command =
                PlaceOrderCommand.builder().customerId(1234).productId(017345).quantity(2).build();

        Order result = placeOrderUseCase.placeOrder(command);

        Assertions.assertNotNull(result);
        verify(eventSourcedSpy, times(1)).publish(result.getId());

    }


}
