/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-13 19:44:59
 * @modify date 2020-10-13 19:44:59
 * @desc Implementation of Pay an Order
 */
package com.fpineda.samples.choreographypattern.service;

import java.util.concurrent.CompletableFuture;
import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.model.PaymentStatus;
import com.fpineda.samples.choreographypattern.core.ports.FetchOrderPort;
import com.fpineda.samples.choreographypattern.core.ports.UpdateOrderStatusPort;
import com.fpineda.samples.choreographypattern.core.usecase.PayOrderUseCase;
import lombok.AllArgsConstructor;

import static com.fpineda.samples.choreographypattern.core.model.PaymentStatus.*;
import static com.fpineda.samples.choreographypattern.core.model.OrderStatus.PAID;

@AllArgsConstructor
public class PayOrderService implements PayOrderUseCase {

    private final FetchOrderPort fetchOrderPort;
    private final UpdateOrderStatusPort updateOrderPort;

    @Override
    public CompletableFuture<Void> payOrder(long orderId) {
        return CompletableFuture.runAsync(() -> {
            Order order = fetchOrderPort.fetch(orderId);
            // get payment info
            PaymentStatus status = doPay();
            if (SUCCESSFUL.equals(status)) {
                updateOrderPort.updateStatus(orderId, PAID);
            }
            order.setStatus(PAID);
        });

    }

    private PaymentStatus doPay() {
        return SUCCESSFUL;
    }

}
