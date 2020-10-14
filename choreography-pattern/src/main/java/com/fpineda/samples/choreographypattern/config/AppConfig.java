/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:31:28
 * @modify date 2020-10-08 23:31:28
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.config;

import javax.annotation.PostConstruct;
import com.fpineda.samples.choreographypattern.adapter.message.CommittedOrderEventListener;
import com.fpineda.samples.choreographypattern.adapter.message.EventListener;
import com.fpineda.samples.choreographypattern.adapter.message.PlaceOrderEventListener;
import com.fpineda.samples.choreographypattern.adapter.persistence.OrderPersistenceAdapter;
import com.fpineda.samples.choreographypattern.adapter.persistence.ProductPersistenceAdapter;
import com.fpineda.samples.choreographypattern.core.event.CommitOrderEventsourced;
import com.fpineda.samples.choreographypattern.core.event.CommittedOrderEvent;
import com.fpineda.samples.choreographypattern.core.event.PlaceOrderEvent;
import com.fpineda.samples.choreographypattern.core.event.PlaceOrderEventSourced;
import com.fpineda.samples.choreographypattern.core.ports.FetchOrderPort;
import com.fpineda.samples.choreographypattern.core.usecase.CommitOrderUseCase;
import com.fpineda.samples.choreographypattern.core.usecase.FetchOrderUseCase;
import com.fpineda.samples.choreographypattern.core.usecase.PayOrderUseCase;
import com.fpineda.samples.choreographypattern.core.usecase.PlaceOrderUseCase;
import com.fpineda.samples.choreographypattern.service.CommitOrderService;
import com.fpineda.samples.choreographypattern.service.FetchOrderService;
import com.fpineda.samples.choreographypattern.service.PayOrderService;
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

    @PostConstruct
    public void init() {
        eventBus().register(placeOrderListener());
        eventBus().register(committedOrderListener());
    }

    @Bean
    public EventBus eventBus() {
        return new EventBus();                                                 
    }

    @Bean
    public EventListener<PlaceOrderEvent> placeOrderListener(){
        return new PlaceOrderEventListener(commitOrderUseCase());
    }
    
    @Bean
    public EventListener<CommittedOrderEvent> committedOrderListener(){
        return new CommittedOrderEventListener(payOrderUseCase());
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
    public PlaceOrderEventSourced placeOrderEventSourced() {
        return new PlaceOrderEventSourced(eventBus());
    }

    @Bean
    public PlaceOrderUseCase placeOrderUseCase() {
        return new PlaceOrderService(placeOrderEventSourced(), orderPersistenceAdapter());
    }

    @Bean
    public CommitOrderEventsourced commitOrderEventsourced() {
        return new CommitOrderEventsourced(eventBus());
    }

    @Bean
    public CommitOrderUseCase commitOrderUseCase() {
        return new CommitOrderService(orderPersistenceAdapter(),
        productPersistenceAdapter(), productPersistenceAdapter(), orderPersistenceAdapter(), commitOrderEventsourced());
    }

    @Bean
    public PayOrderUseCase payOrderUseCase() {
        return new PayOrderService(orderPersistenceAdapter(), orderPersistenceAdapter());
    }
}
