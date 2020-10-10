package com.fpineda.samples.choreographypattern.core.usecase;

import com.fpineda.samples.choreographypattern.core.model.Order;

public interface FetchOrderUseCase {
    
    Order fetchOrder(long id);
}
