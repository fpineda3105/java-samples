/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-13 19:04:05
 * @modify date 2020-10-13 19:04:05
 * @desc Order Commited event listener test.
 */
package com.fpineda.samples.choreographypattern.adapter.message;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.concurrent.CompletableFuture;
import com.fpineda.samples.choreographypattern.core.event.CommittedOrderEvent;
import com.fpineda.samples.choreographypattern.core.usecase.PayOrderUseCase;
import com.google.common.eventbus.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommittedOrderEventListenerTest {

    private EventBus eventBus;

    private EventListener<CommittedOrderEvent> eventListenerSpy;
    private PayOrderUseCase payOrderUseCase;

    @BeforeEach
    public void setUp() {
        eventBus = new EventBus();
        payOrderUseCase = mock(PayOrderUseCase.class);
        when(payOrderUseCase.payOrder(anyLong())).thenReturn(CompletableFuture.completedFuture(null));
        var eventListener = new CommittedOrderEventListener(payOrderUseCase); 
        eventListenerSpy = spy(eventListener);
        eventBus.register(eventListenerSpy);
    }

    @Test
    void shouldHandle_OrderCommittedEvent() {
        CommittedOrderEvent event = new CommittedOrderEvent(1L);
        eventBus.post(event);
        verify(eventListenerSpy, times(1)).handleEvent(event);
        verify(payOrderUseCase, times(1)).payOrder(1L);
    }

}
