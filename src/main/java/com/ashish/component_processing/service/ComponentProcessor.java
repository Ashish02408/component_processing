package com.ashish.component_processing.service;

import com.ashish.component_processing.model.PackagingAndDeliveryResponse;
import com.ashish.component_processing.model.ProcessResponse;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
public abstract class ComponentProcessor {
    private PackagingAndDeliveryResponse packagingAndDeliveryResponse;
    private int processingCharge;
    private int duration;

    protected ComponentProcessor(PackagingAndDeliveryResponse packagingAndDeliveryResponse) {
        this.packagingAndDeliveryResponse = packagingAndDeliveryResponse;
    }

    abstract int getProcessingCharge();

    abstract void setProcessingCharge(int processingCharge);

    abstract int getDuration();

    abstract void setDuration(int duration);

    public ProcessResponse processComponent() {
        int packagingAndDeliveryCharge = packagingAndDeliveryResponse.getPackagingCharge() +
                packagingAndDeliveryResponse.getDeliveryCharge();

        return ProcessResponse.builder()
                .requestId(UUID.randomUUID().toString())
                .processingCharge(getProcessingCharge())
                .packagingAndDeliveryCharge(packagingAndDeliveryCharge)
                .dateOfDelivery(LocalDate.now().plusDays(getDuration()))
                .build();
    }
}
