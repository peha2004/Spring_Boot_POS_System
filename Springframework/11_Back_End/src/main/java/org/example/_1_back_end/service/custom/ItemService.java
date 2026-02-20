package org.example._1_back_end.service.custom;

import org.example._1_back_end.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    void saveItem(ItemDTO dto);
    void updateItem(ItemDTO dto);
    void deleteItem(String code);
    List<ItemDTO> getAllItems();
    String getNextId();
}
