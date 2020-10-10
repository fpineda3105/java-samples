/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:32:53
 * @modify date 2020-10-08 23:32:53
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@Setter
@ToString
public class Order {

    private long id;
    
    private int quantity;

    private long productId;

    private long customerId;

    private OrderStatus status;

}
