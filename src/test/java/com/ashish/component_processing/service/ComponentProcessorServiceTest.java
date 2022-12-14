package com.ashish.component_processing.service;


import com.ashish.component_processing.client.PackagingAndDeliveryClient;
import com.ashish.component_processing.model.PackagingAndDeliveryResponse;
import com.ashish.component_processing.model.ProcessRequest;
import com.ashish.component_processing.model.ProcessResponse;
import com.ashish.component_processing.util.ComponentProcessingDefaultValues;
import com.ashish.component_processing.exception.PackagingAndDeliveryServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ComponentProcessorServiceTest {
    @Mock
    private PackagingAndDeliveryClient packagingAndDeliveryClient;
    @Autowired
    private ComponentFactory componentFactory;
    @Autowired
    private ComponentProcessingDefaultValues componentProcessingDefaultValues;
    private ComponentProcessingService componentProcessingService;

    @BeforeEach
    public void beforeAll() {
        componentProcessingService = new ComponentProcessingService(
                packagingAndDeliveryClient,
                componentFactory,
                componentProcessingDefaultValues
        );
    }

    @Test
    void processDetailWithAccessory() {
        // Given
        String componentName = "Shifter";
        String componentType = "accessory";
        String name = "Ashish";
        int quantity = 2;
        String contactNumber = "7717729897";
        ProcessRequest processRequest = ProcessRequest.builder()
                .componentName(componentName)
                .componentType(componentType)
                .name(name)
                .quantity(quantity)
                .contactNumber(contactNumber)
                .build();
        var packagingAndDeliveryResponse = PackagingAndDeliveryResponse.builder()
                .packagingCharge(200)
                .deliveryCharge(100)
                .build();

        // When
        Mockito.when(packagingAndDeliveryClient.getPackagingAndDeliveryCharge(componentType, quantity))
                .thenReturn(new ResponseEntity<>(packagingAndDeliveryResponse, HttpStatus.OK));
        ProcessResponse processResponse = componentProcessingService.processDetail(processRequest);

        // Then
        Assertions.assertEquals(300, processResponse.getProcessingCharge());
    }

    @Test
    void processDetailWithIntegralItem() {
        // Given
        String componentName = "Shifter";
        String componentType = "integral-item";
        String name = "Ashish";
        int quantity = 2;
        String contactNumber = "7717729897";
        ProcessRequest processRequest = ProcessRequest.builder()
                .componentName(componentName)
                .componentType(componentType)
                .name(name)
                .quantity(quantity)
                .contactNumber(contactNumber)
                .build();
        var packagingAndDeliveryResponse = PackagingAndDeliveryResponse.builder()
                .packagingCharge(200)
                .deliveryCharge(100)
                .build();

        // When
        Mockito.when(packagingAndDeliveryClient.getPackagingAndDeliveryCharge(componentType, quantity))
                .thenReturn(new ResponseEntity<>(packagingAndDeliveryResponse, HttpStatus.OK));
        ProcessResponse processResponse = componentProcessingService.processDetail(processRequest);

        // Then
        Assertions.assertEquals(500, processResponse.getProcessingCharge());
    }

    @Test
    void processDetailWithDownPackagingAndDeliveryService() {
        // Given
        String componentName = "Shifter";
        String componentType = "accessory";
        String name = "Ashish";
        int quantity = 2;
        String contactNumber = "7717729897";
        ProcessRequest processRequest = ProcessRequest.builder()
                .componentName(componentName)
                .componentType(componentType)
                .name(name)
                .quantity(quantity)
                .contactNumber(contactNumber)
                .build();
        String errorMessage = "ComponentProcessingService processDetail(ProcessRequest processRequest) : Packaging and Delivery microservice failed with message : mock error";

        // When
        Mockito.when(packagingAndDeliveryClient.getPackagingAndDeliveryCharge(componentType, quantity))
                .thenThrow(new RuntimeException("mock error"));

        // Then
        Assertions.assertThrows(
                PackagingAndDeliveryServiceException.class,
                () -> componentProcessingService.processDetail(processRequest),
                errorMessage
        );
    }

    @Test
    void processDetailsWithUnsuccessfulPackagingAndDeliveryClientStatus() {
        // Given
        String componentName = "Shifter";
        String componentType = "accessory";
        String name = "Ashish";
        int quantity = 2;
        String contactNumber = "7717729897";
        ProcessRequest processRequest = ProcessRequest.builder()
                .componentName(componentName)
                .componentType(componentType)
                .name(name)
                .quantity(quantity)
                .contactNumber(contactNumber)
                .build();

        // When
        Mockito.when(packagingAndDeliveryClient.getPackagingAndDeliveryCharge(componentType, quantity))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

        // Then
        String expectedErrorMessage = "ComponentProcessingService processDetail(ProcessRequest processRequest) : Packaging and Delivery microservice returned null object";

        Assertions.assertThrows(
                PackagingAndDeliveryServiceException.class,
                () -> componentProcessingService.processDetail(processRequest),
                expectedErrorMessage
        );
    }

    @Test
    void processDetailsWithPackagingAndDeliveryClientHavingNoBody() {
        // Given
        String componentName = "Shifter";
        String componentType = "integral-item";
        String name = "Ashish";
        int quantity = 2;
        String contactNumber = "7717729897";
        ProcessRequest processRequest = ProcessRequest.builder()
                .componentName(componentName)
                .componentType(componentType)
                .name(name)
                .quantity(quantity)
                .contactNumber(contactNumber)
                .build();

        // When
        Mockito.when(packagingAndDeliveryClient.getPackagingAndDeliveryCharge(componentType, quantity))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.OK));

        // Then
        String expectedErrorMessage = "ComponentProcessingService processDetail(ProcessRequest processRequest) : Packaging and Delivery microservice returned null object";

        Assertions.assertThrows(
                PackagingAndDeliveryServiceException.class,
                () -> componentProcessingService.processDetail(processRequest),
                expectedErrorMessage
        );
    }

    @Test
    void processDetailWithException() {
        ProcessRequest processRequest = ProcessRequest.builder()
                .componentName("Shifters")
                .componentType("unknown")
                .name("Ashish")
                .quantity(2)
                .contactNumber("7717729897")
                .build();
        Assertions.assertThrows(
                PackagingAndDeliveryServiceException.class,
                () -> componentProcessingService.processDetail(processRequest),
                "ComponentProcessingService processDetail(ProcessRequest processRequest) : Packaging and Delivery microservice failed with status : 500"
        );
    }
}