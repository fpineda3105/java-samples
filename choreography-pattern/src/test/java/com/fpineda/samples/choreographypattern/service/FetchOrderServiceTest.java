/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-14 08:44:34
 * @modify date 2020-10-14 08:44:34
 * @desc Fetch an Order by {@link org.checkerframework.checker.signature.qual.IdentifierOrArray}
 */
package com.fpineda.samples.choreographypattern.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.ports.FetchOrderPort;
import com.fpineda.samples.choreographypattern.core.usecase.FetchOrderUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FetchOrderServiceTest {
    
    private FetchOrderUseCase service;

    private FetchOrderPort port;

    @BeforeEach
    public void setUp() {
        port = mock(FetchOrderPort.class);
        when(port.fetch(anyLong())).thenReturn(Order.builder().id(1L).quantity(4).build());
        service = new FetchOrderService(port);
    }

    @Test
    void should_fetchOrder_ById(){
        Assertions.assertEquals(4, service.fetchOrder(1L).getQuantity());
    }

}
