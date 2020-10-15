/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-10 17:01:33
 * @modify date 2020-10-10 17:01:33
 * @desc Place Order Service, implements the Business Use Case
 */
package com.fpineda.samples.choreographypattern.service;

import com.fpineda.samples.choreographypattern.core.command.PlaceOrderCommand;
import com.fpineda.samples.choreographypattern.core.event.EventSource;
import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.ports.PlaceOrderPort;
import com.fpineda.samples.choreographypattern.core.usecase.PlaceOrderUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {

    private final EventSource<Order> placeOrderEventSourced;
    private final PlaceOrderPort port;    

    @Override
    public Order placeOrder(PlaceOrderCommand command) {
        Order result = port.placeOrder(command);

        placeOrderEventSourced.emit(result).thenAccept(ignored -> {});
    
        return result;
    }    
    
}
