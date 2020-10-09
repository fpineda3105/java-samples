/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:33:03
 * @modify date 2020-10-08 23:33:03
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.core.usecase;

import com.fpineda.samples.choreographypattern.core.command.PlaceOrderCommand;
import com.fpineda.samples.choreographypattern.core.model.Order;

public interface PlaceOrderUseCase {

    Order placeOrder(PlaceOrderCommand placeOrderCommand);

}
