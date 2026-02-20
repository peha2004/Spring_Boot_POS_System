package org.example._1_back_end.repository;

import org.example._1_back_end.entity.OrderDetail;
import org.example._1_back_end.entity.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
}
