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
import com.fpineda.samples.choreographypattern.core.usecase.FetchOrderUseCase;
import com.fpineda.samples.choreographypattern.core.usecase.PlaceOrderUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderRestContoller {

    private final PlaceOrderUseCase placeOrderService;
    private final FetchOrderUseCase fetchOrderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {

        PlaceOrderCommand command = request.toCommand();
        
        Order result = placeOrderService.placeOrder(command);
        
        return ResponseEntity.accepted().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> fetchOrder(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(fetchOrderService.fetchOrder(id));
    }

}
