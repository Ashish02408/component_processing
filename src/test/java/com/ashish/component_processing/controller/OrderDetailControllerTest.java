package com.ashish.component_processing.controller;

import com.ashish.component_processing.model.ProcessRequest;
import com.ashish.component_processing.model.OrderRequest;
import com.ashish.component_processing.model.ProcessResponse;
import com.ashish.component_processing.service.OrderDetailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class OrderDetailControllerTest {
    @InjectMocks
    OrderDetailController orderDetailController;
    @Mock
    private OrderDetailService orderDetailService;

    @Test
    void placeOrder() {
        // Given
        String customerEmail = "ashish@gmail.com";
        OrderRequest orderRequest = OrderRequest.builder()
                .processRequest(
                        ProcessRequest.builder()
                                .name("Ashish")
                                .contactNumber("7717729897")
                                .componentType("integral-item")
                                .componentName("mock")
                                .quantity(1)
                                .build()
                )
                .processResponse(
                        ProcessResponse.builder()
                                .requestId(UUID.randomUUID().toString())
                                .processingCharge(500)
                                .packagingAndDeliveryCharge(300)
                                .dateOfDelivery(LocalDate.now().plusDays(3))
                                .build()
                )
                .build();

        // When
        orderDetailController.placeOrder(orderRequest, customerEmail);

        // Then
        Mockito.verify(orderDetailService).addOrderDetail(orderRequest, customerEmail);
    }

    @Test
    void getOrderDetailsByCustomerEmail() {
        // Given
        String customerEmail = "ashish@gamil.com";

        // When
        orderDetailController.getOrderDetailsByCustomerEmail(customerEmail);

        // Then
        Mockito.verify(orderDetailService).getOrderDetailsByCustomerEmail(customerEmail);
    }

    @Test
    void getOrderDetailByCustomerEmailAndOrderId() {
        // Given
        String customerEmail = "ashish@gamil.com";
        Long orderId = 1L;

        // When
        orderDetailController.getOrderDetailByCustomerEmailAndOrderId(customerEmail, 1L);

        // Then
        Mockito.verify(orderDetailService).getOrderDetailByCustomerEmailAndOrderId(customerEmail, orderId);
    }
}