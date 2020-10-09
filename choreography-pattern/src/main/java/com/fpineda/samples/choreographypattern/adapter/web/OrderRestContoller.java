/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:31:37
 * @modify date 2020-10-08 23:31:37
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.adapter.web;

import com.fpineda.samples.choreographypattern.adapter.web.dto.OrderRequest;
import com.fpineda.samples.choreographypattern.core.command.PlaceOrderCommand;
import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.usecase.PlaceOrderUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderRestContoller {

    private final PlaceOrderUseCase placeOrderService;

    public OrderRestContoller(PlaceOrderUseCase placeOrderUseCase) {
        this.placeOrderService = placeOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {

        PlaceOrderCommand command = request.toCommand();
        
        Order result = placeOrderService.placeOrder(command);
        
        return ResponseEntity.accepted().body(result);
    }

}
