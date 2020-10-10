/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:31:28
 * @modify date 2020-10-08 23:31:28
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.config;

import com.fpineda.samples.choreographypattern.adapter.message.EventListener;
import com.fpineda.samples.choreographypattern.adapter.message.PlaceOrderEventListener;
import com.fpineda.samples.choreographypattern.adapter.persistence.OrderPersistenceAdapter;
import com.fpineda.samples.choreographypattern.adapter.persistence.ProductPersistenceAdapter;
import com.fpineda.samples.choreographypattern.core.event.PlaceOrderEvent;
import com.fpineda.samples.choreographypattern.core.ports.FetchOrderPort;
import com.fpineda.samples.choreographypattern.core.usecase.CommitOrderUseCase;
import com.fpineda.samples.choreographypattern.core.usecase.FetchOrderUseCase;
import com.fpineda.samples.choreographypattern.core.usecase.PlaceOrderUseCase;
import com.fpineda.samples.choreographypattern.service.CommitOrderService;
import com.fpineda.samples.choreographypattern.service.FetchOrderService;
import com.fpineda.samples.choreographypattern.service.PlaceOrderService;
import com.google.common.eventbus.EventBus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DatabaseConfig.class)
public class AppConfig {

    private final DatabaseConfig databaseConfig;

    public AppConfig(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Bean
    public EventBus eventBus() {
        EventBus eventBus = new EventBus();
        CommitOrderUseCase commitOrderUseCase = new CommitOrderService(orderPersistenceAdapter(),
                productPersistenceAdapter(), productPersistenceAdapter(), orderPersistenceAdapter());
        EventListener<PlaceOrderEvent> placeOrderListener =
                new PlaceOrderEventListener(commitOrderUseCase);
        eventBus.register(placeOrderListener);
        return eventBus;
    }

    @Bean
    public FetchOrderUseCase fetchOrderUseCase() {
        FetchOrderPort port = orderPersistenceAdapter();
        return new FetchOrderService(port);
    }

    @Bean
    public OrderPersistenceAdapter orderPersistenceAdapter() {
        return new OrderPersistenceAdapter(databaseConfig.dataSource());
    }

    @Bean
    public ProductPersistenceAdapter productPersistenceAdapter() {
        return new ProductPersistenceAdapter(databaseConfig.dataSource());
    }

    @Bean
    public PlaceOrderUseCase placeOrderUseCase() {
        return new PlaceOrderService(eventBus(), orderPersistenceAdapter());
    }
}
