package org.example._1_back_end.service.custom.impl;

import org.example._1_back_end.dto.ItemDTO;
import org.example._1_back_end.entity.Item;
import org.example._1_back_end.exception.CustomException;
import org.example._1_back_end.repository.ItemRepository;
import org.example._1_back_end.service.custom.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveItem(ItemDTO dto) {
        if (dto == null) {
            throw new CustomException("Item DTO is null");
        }

        itemRepository.save(
                modelMapper.map(dto, Item.class)
        );
    }


    @Override
    public void updateItem(ItemDTO dto) {
        if (!itemRepository.existsById(dto.getItemCode())) {
            throw new CustomException("Item not found");
        }

        itemRepository.save(
                modelMapper.map(dto, Item.class)
        );
    }

    @Override
    public void deleteItem(String code) {
        if (!itemRepository.existsById(code)) {
            throw new CustomException("Item not found");
        }

        itemRepository.deleteById(code);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .toList();
    }

    @Override
    public String getNextId() {
        String lastId = itemRepository.getLastId();

        if (lastId == null) {
            return "I001";
        }

        int num = Integer.parseInt(lastId.substring(1));
        num++;

        return String.format("I%03d", num);
    }
}
