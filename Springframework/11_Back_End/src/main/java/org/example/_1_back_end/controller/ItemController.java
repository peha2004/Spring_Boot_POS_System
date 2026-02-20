package org.example._1_back_end.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example._1_back_end.dto.ItemDTO;
import org.example._1_back_end.service.custom.ItemService;
import org.example._1_back_end.utill.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<APIResponse<String>> saveItem(
            @RequestBody @Valid ItemDTO dto) {

        itemService.saveItem(dto);

        return new ResponseEntity<>(
                new APIResponse<>(201, "Item Saved", null),
                HttpStatus.CREATED
        );
    }

    @PutMapping
    public ResponseEntity<APIResponse<String>> updateItem(
            @RequestBody @Valid ItemDTO dto) {

        itemService.updateItem(dto);

        return new ResponseEntity<>(
                new APIResponse<>(200, "Item Updated", null),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<APIResponse<String>> deleteItem(
            @PathVariable String code) {

        itemService.deleteItem(code);

        return new ResponseEntity<>(
                new APIResponse<>(200, "Item Deleted", null),
                HttpStatus.OK
        );
    }

    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/next-id")
    public String getNextId() {
        return itemService.getNextId();
    }
}
