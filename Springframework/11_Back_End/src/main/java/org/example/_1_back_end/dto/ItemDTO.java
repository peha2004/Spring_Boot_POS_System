package org.example._1_back_end.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    @NotBlank(message = "Item code is mandatory")
    private String itemCode;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Unit price is mandatory")
    @Min(value = 1, message = "Price must be greater than 0")
    private double unitPrice;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 0, message = "Quantity cannot be negative")
    private int qty;
}
