package org.example._1_back_end.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @NotBlank(message = "Order ID is mandatory")
    private String orderId;
    @NotBlank(message = "Customer ID is mandatory")
    private String customerId;
    @NotBlank(message = "Date is mandatory")
    private String date;
    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderDetailDTO> items;
}

