package org.example._1_back_end.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example._1_back_end.dto.CustomerDTO;
import org.example._1_back_end.service.custom.impl.CustomerServiceImpl;
import java.util.List;

import org.example._1_back_end.utill.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {
    private final CustomerServiceImpl customerService;

@PostMapping
    public ResponseEntity<APIResponse<String>> saveCustomer(@RequestBody @Valid CustomerDTO customerDTO){
    customerService.saveCustomer(customerDTO);
    return new ResponseEntity<>(new APIResponse<>(
            201,"customer saved",null
    ), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable  int id){
        customerService.deleteCustomer(id);
    }
    @PutMapping
    public void updateCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(customerDTO);
    }


}
