package org.example._1_back_end.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example._1_back_end.dto.OrderDTO;
import org.example._1_back_end.dto.OrderDetailDTO;
import org.example._1_back_end.service.custom.OrderService;
import org.example._1_back_end.utill.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
@CrossOrigin

public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<APIResponse<String>> placeOrder(
            @RequestBody @Valid OrderDTO dto){

        orderService.placeOrder(dto);

        return new ResponseEntity<>(
                new APIResponse<>(201,"Order Placed Successfully",null),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public List<OrderDetailDTO> getOrderDetails(@PathVariable String id){
        return orderService.getOrderDetails(id);
    }

    @GetMapping("/next-id")
    public String getNextId(){
        return orderService.getNextId();
    }
}
