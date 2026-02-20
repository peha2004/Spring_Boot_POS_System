package org.example._1_back_end.repository;

import org.example._1_back_end.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "SELECT c_id FROM customer ORDER BY c_id DESC LIMIT 1", nativeQuery = true)
    String getLastId();
}
