/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:32:09
 * @modify date 2020-10-08 23:32:09
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import javax.sql.DataSource;
import com.fpineda.samples.choreographypattern.adapter.persistence.OrderPersistenceAdapter;
import com.fpineda.samples.choreographypattern.core.command.PlaceOrderCommand;
import com.fpineda.samples.choreographypattern.core.event.PlaceOrderEvent;
import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.ports.PlaceOrderPort;
import com.fpineda.samples.choreographypattern.core.usecase.PlaceOrderUseCase;
import com.google.common.eventbus.EventBus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
public class PlaceOrderServiceTest {

    private PlaceOrderUseCase placeOrderUseCase;
    private PlaceOrderPort port;

    @Spy
    private EventBus eventBusSpy;

    @Autowired
    private DataSource datasource;

    @TestConfiguration
    static class PlaceOrderConfiguration {

        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.h2.Driver");
            dataSource.setUrl("jdbc:h2:mem:testdD;DB_CLOSE_DELAY=-1");
            dataSource.setUsername("test");
            dataSource.setPassword("test$123");
            return dataSource;
        }
    }

    @BeforeEach
    public void reset() {        
        port = new OrderPersistenceAdapter(datasource);
        placeOrderUseCase = new PlaceOrderService(eventBusSpy, port);
    }

    @Test
    @Sql(scripts = {"/order_table.sql"})
    public void placeOrderInDbAndEmitEvent() {
        PlaceOrderCommand command =
                PlaceOrderCommand.builder().customerId(1234).productId(017345).quantity(2).build();

        Order result = placeOrderUseCase.placeOrder(command);

        Assertions.assertNotNull(result);
        verify(eventBusSpy, times(1)).post(any(PlaceOrderEvent.class));

    }


}
