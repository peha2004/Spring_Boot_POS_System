package org.example._1_back_end.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private int cId;
    @NotBlank(message = "customer name is mandatory")
    private String cName;
   @NotBlank(message = "customer address is mandatory")
   @Size(min=6,message = "customer shold be at lest 10 characters")
    private String cAddress;


}
