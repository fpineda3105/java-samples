/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:31:25
 * @modify date 2020-10-08 23:31:25
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.adapter.web.dto;

import com.fpineda.samples.choreographypattern.core.command.PlaceOrderCommand;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class OrderRequest {

    private int quantity;

    private int productId;
    
    private long customerId;

    public PlaceOrderCommand toCommand() {
        return PlaceOrderCommand.builder().customerId(customerId).productId(productId).quantity(quantity).build();
    }

}
