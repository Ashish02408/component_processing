package com.ashish.component_processing.service;

import com.ashish.component_processing.model.ComponentType;
import com.ashish.component_processing.model.PackagingAndDeliveryResponse;
import org.springframework.stereotype.Component;

@Component
public class ComponentFactory {
    public ComponentProcessor make(
            ComponentType componentType,
            PackagingAndDeliveryResponse packagingAndDeliveryResponse
    ) {
        ComponentProcessor componentProcessor = null;

        if (componentType == ComponentType.ACCESSORY) {
            componentProcessor = new Accessory(packagingAndDeliveryResponse);
        } else if (componentType == ComponentType.INTEGRAL_ITEM) {
            componentProcessor = new IntegralItem(packagingAndDeliveryResponse);
        }

        return componentProcessor;
    }
}
