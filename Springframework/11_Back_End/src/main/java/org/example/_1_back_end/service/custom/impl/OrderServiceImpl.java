package org.example._1_back_end.service.custom.impl;

import org.example._1_back_end.dto.OrderDTO;
import org.example._1_back_end.dto.OrderDetailDTO;
import org.example._1_back_end.entity.Item;
import org.example._1_back_end.entity.OrderDetail;
import org.example._1_back_end.entity.Orders;
import org.example._1_back_end.exception.CustomException;
import org.example._1_back_end.repository.ItemRepository;
import org.example._1_back_end.repository.OrderDetailRepository;
import org.example._1_back_end.repository.OrdersRepository;
import org.example._1_back_end.service.custom.OrderService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrdersRepository ordersRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    @Override
    public void placeOrder(OrderDTO dto) {
        if(dto == null){
            throw new CustomException("Order DTO is null");
        }

        ordersRepository.save(
                new Orders(
                        dto.getOrderId(),
                        dto.getCustomerId(),
                        dto.getDate()
                )
        );
        dto.getItems().forEach(detailDTO -> {

            Item item = itemRepository.findById(detailDTO.getItemCode())
                    .orElseThrow(() -> new CustomException("Item not found"));

            if(item.getQty() < detailDTO.getQty()){
                throw new CustomException(
                        "Not enough stock for item: " + detailDTO.getItemCode()
                );
            }
            orderDetailRepository.save(
                    new OrderDetail(
                            dto.getOrderId(),
                            detailDTO.getItemCode(),
                            detailDTO.getQty(),
                            detailDTO.getUnitPrice()
                    )
            );

            item.setQty(item.getQty() - detailDTO.getQty());
            itemRepository.save(item);
        });
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return ordersRepository.findAll()
                .stream()
                .map(o -> new OrderDTO(
                        o.getOrderId(),
                        o.getCustomerId(),
                        o.getDate(),
                        null
                )).toList();
    }

    @Override
    public List<OrderDetailDTO> getOrderDetails(String orderId) {
        return orderDetailRepository.findAll()
                .stream()
                .filter(d -> d.getOrderId().equals(orderId))
                .map(d -> new OrderDetailDTO(
                        d.getItemCode(),
                        d.getQty(),
                        d.getUnitPrice()
                )).toList();
    }

    @Override
    public String getNextId() {
        String lastId = ordersRepository.getLastId();

        if (lastId == null) {
            return "O001";
        }

        int num = Integer.parseInt(lastId.substring(1));
        num++;

        return String.format("O%03d", num);
    }
}