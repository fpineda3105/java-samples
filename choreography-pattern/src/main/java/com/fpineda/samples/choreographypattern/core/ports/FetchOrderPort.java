package com.fpineda.samples.choreographypattern.core.ports;

import com.fpineda.samples.choreographypattern.core.model.Order;

public interface FetchOrderPort {

    Order fetch(long orderId);

}
