package org.example._1_back_end.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(OrderDetailPK.class)
public class OrderDetail {
    @Id
    private String orderId;
    @Id
    private String itemCode;

    private int qty;
    private double unitPrice;
}
