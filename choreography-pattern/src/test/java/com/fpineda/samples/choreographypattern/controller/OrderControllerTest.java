/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:32:23
 * @modify date 2020-10-08 23:32:23
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpineda.samples.choreographypattern.adapter.web.OrderRestContoller;
import com.fpineda.samples.choreographypattern.adapter.web.dto.OrderRequest;
import com.fpineda.samples.choreographypattern.core.model.Order;
import com.fpineda.samples.choreographypattern.core.model.OrderStatus;
import com.fpineda.samples.choreographypattern.core.usecase.PlaceOrderUseCase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(OrderRestContoller.class)
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    private static ObjectMapper mapper;

    @MockBean
    private PlaceOrderUseCase orderService;

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void shouldReturnOrderId() throws Exception {
        Order order = Order.builder().id(231).quantity(5).customerId(927333444).productId(1).status(OrderStatus.PLACED).build();
        OrderRequest request = OrderRequest.builder().productId(927333444).quantity(5).build();
        when(orderService.placeOrder(request.toCommand())).thenReturn(order);
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(asJsonString(request)))
                .andExpect(status().isAccepted()).andExpect(jsonPath("$.id").value(231));
        verify(orderService, times(1)).placeOrder(request.toCommand());
    }

    static String asJsonString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

}
