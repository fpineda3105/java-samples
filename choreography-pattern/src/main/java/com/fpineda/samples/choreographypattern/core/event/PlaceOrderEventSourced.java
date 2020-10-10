/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-10 17:00:50
 * @modify date 2020-10-10 17:00:50
 * @desc generator of Place order events
 */
package com.fpineda.samples.choreographypattern.core.event;

import java.util.concurrent.CompletableFuture;
import com.google.common.eventbus.EventBus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class PlaceOrderEventSourced {

    private final EventBus eventBus;

    public CompletableFuture<Void> publish(long orderId) {
        return CompletableFuture.runAsync(() -> {                      
            PlaceOrderEvent event = new PlaceOrderEvent(orderId);
            eventBus.post(event);
            log.info("Published PlaceOrderEvent id: {} ", orderId);
        });
    }
    
}
