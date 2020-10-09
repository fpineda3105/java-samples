package com.fpineda.samples.choreographypattern.service;

import com.fpineda.samples.choreographypattern.core.command.PlaceOrderCommand;
import com.fpineda.samples.choreographypattern.core.event.PlaceOrderEvent;
import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.ports.PlaceOrderPort;
import com.fpineda.samples.choreographypattern.core.usecase.PlaceOrderUseCase;
import com.google.common.eventbus.EventBus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {

    private final EventBus eventbus;
    private final PlaceOrderPort port;    

    @Override
    public Order placeOrder(PlaceOrderCommand command) {
        Order result = port.placeOrder(command);

        PlaceOrderEvent event = new PlaceOrderEvent(result.getId());
        eventbus.post(event);
        
        return result;
    }    
    
}
