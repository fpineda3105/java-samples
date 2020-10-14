/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-13 19:24:09
 * @modify date 2020-10-13 19:24:09
 * @desc generate an order payment
 */
package com.fpineda.samples.choreographypattern.core.usecase;

import java.util.concurrent.CompletableFuture;

public interface PayOrderUseCase {

    CompletableFuture<Void> payOrder(long orderId);
}
