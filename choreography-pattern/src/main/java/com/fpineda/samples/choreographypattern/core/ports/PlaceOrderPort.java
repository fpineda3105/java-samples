/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:32:59
 * @modify date 2020-10-08 23:32:59
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.core.ports;

import com.fpineda.samples.choreographypattern.core.command.PlaceOrderCommand;
import com.fpineda.samples.choreographypattern.core.model.Order;

public interface PlaceOrderPort {
    
    Order placeOrder(PlaceOrderCommand command);
}
