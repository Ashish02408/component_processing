package com.ashish.component_processing.entity;

import com.ashish.component_processing.model.ProcessRequest;
import com.ashish.component_processing.model.ProcessResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String customerEmail;
    @Embedded
    private ProcessRequest processRequest;
    @Embedded
    private ProcessResponse processResponse;
}
