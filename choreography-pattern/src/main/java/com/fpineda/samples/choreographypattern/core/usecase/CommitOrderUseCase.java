/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-09 09:53:56
 * @modify date 2020-10-09 09:53:56
 * @desc commit order -> verify stock, separate stock, change status order
 */
package com.fpineda.samples.choreographypattern.core.usecase;

import com.fpineda.samples.choreographypattern.core.model.Order;

public interface CommitOrderUseCase {

    Order commitOrder(long orderId);
     
}
