package com.ashish.component_processing.util;

import com.ashish.component_processing.model.ComponentType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix = "component-processing")
public class ComponentProcessingDefaultValues {
    private static final String PROCESSING_CHARGE = "processing-charge";
    private static final String PROCESSING_DURATION = "processing-duration";
    private Map<String, Integer> integralItem;
    private Map<String, Integer> accessory;

    public int getProcessingCharge(ComponentType componentType) {
        int processingCharge = 0;

        if (componentType == ComponentType.ACCESSORY) {
            processingCharge = accessory.get(PROCESSING_CHARGE);
        } else if (componentType == ComponentType.INTEGRAL_ITEM) {
            processingCharge = integralItem.get(PROCESSING_CHARGE);
        }

        return processingCharge;
    }

    public int getProcessingDuration(ComponentType componentType) {
        int duration = 0;

        if (componentType == ComponentType.ACCESSORY) {
            duration = accessory.get(PROCESSING_DURATION);
        } else if (componentType == ComponentType.INTEGRAL_ITEM) {
            duration = integralItem.get(PROCESSING_DURATION);
        }

        return duration;
    }
}
