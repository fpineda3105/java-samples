/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:29:10
 * @modify date 2020-10-08 23:29:10
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.adapter.message;

import com.fpineda.samples.choreographypattern.core.event.PlaceOrderEvent;
import com.fpineda.samples.choreographypattern.core.usecase.CommitOrderUseCase;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class PlaceOrderEventListener implements EventListener<PlaceOrderEvent> {

    private final CommitOrderUseCase orderUseCase;
    
    @Override
    @Subscribe
    public void handleEvent(PlaceOrderEvent event) {
        log.info("Order placed: {}", event.getId());
        orderUseCase.commitOrder(event.getId());
    }

}
