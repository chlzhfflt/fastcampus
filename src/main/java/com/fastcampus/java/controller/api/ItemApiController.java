package com.fastcampus.java.controller.api;

import com.fastcampus.java.ifs.CrudInterface;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.ItemApiRequest;
import com.fastcampus.java.model.network.response.ItemApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Override
    @PostMapping("")    // /api/item
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    @GetMapping("{id}") // /api/item/1 ... 1000
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        return null;
    }

    @Override
    @PutMapping("")     // /api/item
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")// /api/item/1 ... 1000
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
