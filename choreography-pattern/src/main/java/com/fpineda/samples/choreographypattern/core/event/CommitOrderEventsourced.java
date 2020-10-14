/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-13 19:12:56
 * @modify date 2020-10-13 19:12:56
 * @desc publish an order with status committed.
 */
package com.fpineda.samples.choreographypattern.core.event;

import java.util.concurrent.CompletableFuture;
import com.google.common.eventbus.EventBus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CommitOrderEventsourced {
    
    private final EventBus eventBus;

    public CompletableFuture<Void> publish(long orderId) {
        return CompletableFuture.runAsync(() -> {
            CommittedOrderEvent event = new CommittedOrderEvent(orderId);
            eventBus.post(event);
            log.info("Published CommittedOrderEvent id: {} ", orderId);
        });                
    }
}
