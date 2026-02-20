package org.example._1_back_end.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    @NotBlank(message = "Item code is mandatory")
    private String itemCode;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 1, message = "Quantity must be at least 1")
    private int qty;

    @NotNull(message = "Unit price is mandatory")
    @Min(value = 1, message = "Price must be greater than 0")
    private double unitPrice;
}
