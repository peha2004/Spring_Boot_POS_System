package org.example._1_back_end.service.custom.impl;

import lombok.RequiredArgsConstructor;
import org.example._1_back_end.dto.CustomerDTO;
import org.example._1_back_end.entity.Customer;
import org.example._1_back_end.exception.CustomException;
import org.example._1_back_end.repository.CustomerRepository;
import org.example._1_back_end.service.custom.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

@Override
    public void saveCustomer(CustomerDTO customerDTO){
    customerRepository.save(modelMapper.map(customerDTO,Customer.class));

    }

    @Override
    public void updateCustomer(CustomerDTO dto) {
        if (dto==null){
            throw new CustomException("customer DTO is null");
        }
        customerRepository.save(modelMapper.map(dto,Customer.class));

    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }



    @Override
    public List<CustomerDTO> getAllCustomers() {
    List<Customer> list =customerRepository.findAll();
        return list.stream().map(customer -> modelMapper.map(customer,CustomerDTO.class)).collect(Collectors.toList());

    }
}
