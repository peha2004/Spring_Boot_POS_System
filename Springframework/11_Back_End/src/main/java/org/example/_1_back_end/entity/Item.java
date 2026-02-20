package org.example._1_back_end.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {

    @Id
    private String itemCode;
    private String description;
    private double unitPrice;
    private int qty;
}