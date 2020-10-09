/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:32:42
 * @modify date 2020-10-08 23:32:42
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.core.command;

import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.model.OrderStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class PlaceOrderCommand {

    private int quantity;

    private int productId;

    private long customerId;

    public Order toOrder() {
        return Order.builder().customerId(customerId).productId(productId).quantity(quantity).status(OrderStatus.PLACED)
                .build();        
    }

}
