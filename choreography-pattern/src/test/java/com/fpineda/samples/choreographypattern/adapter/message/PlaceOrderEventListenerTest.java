package com.fpineda.samples.choreographypattern.adapter.message;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import com.fpineda.samples.choreographypattern.core.event.PlaceOrderEvent;
import com.fpineda.samples.choreographypattern.core.usecase.CommitOrderUseCase;
import com.fpineda.samples.choreographypattern.service.CommitOrderService;
import com.google.common.eventbus.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlaceOrderEventListenerTest {
  
    private EventBus eventBus;

    private CommitOrderUseCase commitOrderUseCase;

    private EventListener<PlaceOrderEvent> eventListenerSpy;

    @BeforeEach
    void reset() {
        eventBus = new EventBus();
        commitOrderUseCase = mock(CommitOrderService.class);
        var eventListener = new PlaceOrderEventListener(commitOrderUseCase);
        eventListenerSpy = spy(eventListener);
        
        eventBus.register(eventListenerSpy);        
    }

    @Test
    void onPlaceOrderEventShouldHandle() {
        PlaceOrderEvent event = new PlaceOrderEvent(123);
        eventBus.post(event);
        verify(eventListenerSpy, times(1)).handleEvent(event);
        verify(commitOrderUseCase, times(1)).commitOrder(event.getId());        
    }
    

}
