package com.ashish.component_processing.controller;

import com.ashish.component_processing.model.ProcessRequest;
import com.ashish.component_processing.service.ComponentProcessingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ComponentProcessingControllerTest {
    @Mock
    private ComponentProcessingService componentProcessingService;
    @InjectMocks
    private ComponentProcessingController componentProcessingController;

    @Test
    void processDetail() {
        // Given
        ProcessRequest processRequest = ProcessRequest.builder()
                .name("Ashish")
                .contactNumber("7717729897")
                .componentType("integral-item")
                .componentName("mock")
                .quantity(1)
                .build();

        // When
        componentProcessingController.processDetail(processRequest);

        // Then
        Mockito.verify(componentProcessingService).processDetail(processRequest);
    }
}