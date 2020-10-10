/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-09 17:01:50
 * @modify date 2020-10-09 17:01:50
 * @desc update order status contract
 */
package com.fpineda.samples.choreographypattern.core.ports;

import com.fpineda.samples.choreographypattern.core.model.OrderStatus;

public interface UpdateOrderStatusPort {

    boolean updateStatus(long orderId, OrderStatus status);
}
