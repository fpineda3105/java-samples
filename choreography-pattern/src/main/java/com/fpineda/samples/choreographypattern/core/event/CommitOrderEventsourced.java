/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-13 19:12:56
 * @modify date 2020-10-13 19:12:56
 * @desc publish an order with status committed.
 */
package com.fpineda.samples.choreographypattern.core.event;

import java.util.concurrent.CompletableFuture;
import com.fpineda.samples.choreographypattern.core.model.Order;
import com.google.common.eventbus.EventBus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CommitOrderEventsourced implements EventSource<Order> {
    
    private final EventBus eventBus;

    @Override
    public CompletableFuture<Void> emit(final Order order) {
        return CompletableFuture.runAsync(() -> {
            CommittedOrderEvent event = new CommittedOrderEvent(order.getId());
            eventBus.post(event);
            log.info("Published CommittedOrderEvent id: {} ", order.getId());
        }); 
    }
}
