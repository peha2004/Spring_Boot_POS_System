package org.example._1_back_end.service.custom;

import org.example._1_back_end.dto.OrderDTO;
import org.example._1_back_end.dto.OrderDetailDTO;

import java.util.List;

public interface OrderService {
    void placeOrder(OrderDTO dto);
    List<OrderDTO> getAllOrders();
    List<OrderDetailDTO> getOrderDetails(String orderId);
    String getNextId();
}
