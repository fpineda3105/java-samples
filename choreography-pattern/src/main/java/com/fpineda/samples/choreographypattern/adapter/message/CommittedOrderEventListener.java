/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-13 19:14:52
 * @modify date 2020-10-13 19:14:52
 * @desc handle an Order commited event.
 */
package com.fpineda.samples.choreographypattern.adapter.message;

import com.fpineda.samples.choreographypattern.core.event.CommittedOrderEvent;
import com.fpineda.samples.choreographypattern.core.usecase.PayOrderUseCase;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class CommittedOrderEventListener implements EventListener<CommittedOrderEvent> {

    private final PayOrderUseCase useCase;

    @Override
    @Subscribe
    public void handleEvent(CommittedOrderEvent event) {
        log.info("Processing event : {}", event);
        useCase.payOrder(event.getOrderId()).thenAccept(ignore -> {});

    }
    
}
