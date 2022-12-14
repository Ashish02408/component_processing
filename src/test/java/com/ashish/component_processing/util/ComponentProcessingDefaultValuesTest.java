package com.ashish.component_processing.util;

import com.ashish.component_processing.model.ComponentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ComponentProcessingDefaultValuesTest {
    @Autowired
    private ComponentProcessingDefaultValues componentProcessingDefaultValues;

    @Test
    void getProcessingChargeForIntegralItem() {
        int expected = 500;
        int actual = componentProcessingDefaultValues.getProcessingCharge(ComponentType.INTEGRAL_ITEM);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getProcessingChargeForAccessory() {
        int expected = 300;
        int actual = componentProcessingDefaultValues.getProcessingCharge(ComponentType.ACCESSORY);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getProcessingDurationForIntegralItem() {
        int expected = 5;
        int actual = componentProcessingDefaultValues.getProcessingDuration(ComponentType.INTEGRAL_ITEM);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getProcessingDurationForAccessory() {
        int expected = 2;
        int actual = componentProcessingDefaultValues.getProcessingDuration(ComponentType.ACCESSORY);
        Assertions.assertEquals(expected, actual);
    }
}