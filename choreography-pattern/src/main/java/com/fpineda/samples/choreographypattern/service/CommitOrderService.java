package com.fpineda.samples.choreographypattern.service;

import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.model.OrderStatus;
import com.fpineda.samples.choreographypattern.core.model.Product;
import com.fpineda.samples.choreographypattern.core.ports.CommitProductStockPort;
import com.fpineda.samples.choreographypattern.core.ports.FetchOrderPort;
import com.fpineda.samples.choreographypattern.core.ports.FetchProductPort;
import com.fpineda.samples.choreographypattern.core.ports.UpdateOrderStatusPort;
import com.fpineda.samples.choreographypattern.core.usecase.CommitOrderUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommitOrderService implements CommitOrderUseCase {

    private final FetchOrderPort fetchOrderPort;
    private final CommitProductStockPort commitProductPort;
    private final FetchProductPort fetchProductPort;
    private final UpdateOrderStatusPort updateOrderPort;

    @Override
    public Order commitOrder(long orderId) {
        Order order = fetchOrderPort.fetch(orderId);
        Product product = fetchProduct(order.getProductId());

        if (product.isInStockForQuantity(order.getQuantity())
                && isProductCommited(product.getId(), order.getQuantity())) {
            updateOrderStatus(order.getId(), OrderStatus.COMMITTED);
            order.setStatus(OrderStatus.COMMITTED);
        }

        return order;
    }

    private Product fetchProduct(long id) {
        return fetchProductPort.fetch(id);
    }

    private boolean isProductCommited(long id, int quantity) {
        return commitProductPort.commit(id, quantity);
    }

    private boolean updateOrderStatus(long id, OrderStatus status) {
        return updateOrderPort.updateStatus(id, status);
    }

}
