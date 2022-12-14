package com.ashish.component_processing.service;

import com.ashish.component_processing.model.PackagingAndDeliveryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IntegralItem extends ComponentProcessor {
    private int processingCharge;
    private int duration;

    public IntegralItem(PackagingAndDeliveryResponse packagingAndDeliveryResponse) {
        super(packagingAndDeliveryResponse);
    }
}
