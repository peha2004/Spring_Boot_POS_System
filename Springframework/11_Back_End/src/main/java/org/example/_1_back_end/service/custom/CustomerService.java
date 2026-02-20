package org.example._1_back_end.service.custom;

import org.example._1_back_end.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public  void saveCustomer(CustomerDTO customerDTO);
    public void updateCustomer(CustomerDTO customerDTO);
    public void deleteCustomer(int id);
    List<CustomerDTO> getAllCustomers();


}
