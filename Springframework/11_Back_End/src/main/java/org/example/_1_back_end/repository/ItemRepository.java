package org.example._1_back_end.repository;

import org.example._1_back_end.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item,String> {
    @Query(value = "SELECT item_code FROM item ORDER BY item_code DESC LIMIT 1", nativeQuery = true)
    String getLastId();
}
