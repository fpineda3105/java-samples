/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-09 17:34:55
 * @modify date 2020-10-09 17:34:55
 * @desc Fetch an Order
 */
package com.fpineda.samples.choreographypattern.service;

import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.ports.FetchOrderPort;
import com.fpineda.samples.choreographypattern.core.usecase.FetchOrderUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FetchOrderService implements FetchOrderUseCase {

    private final FetchOrderPort port;

    @Override
    public Order fetchOrder(long id) {        
        return port.fetch(id);
    }
    
}
