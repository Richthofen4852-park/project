package com.example.calcapi.service;

import com.example.calcapi.entity.Item;
import com.example.calcapi.ifs.CrudInterface;
import com.example.calcapi.model.Header;
import com.example.calcapi.model.request.ItemApiRequest;
import com.example.calcapi.model.response.ItemApiResponse;
import com.example.calcapi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.calcapi.model.Header.ERROR;
import static com.example.calcapi.model.Header.OK;

@Service
public class ItemApiService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {

        ItemApiRequest body = request.getData();

        Item item = Item.builder()
                .name(body.getName())
                .price(body.getPrice())
                .build();

        Item newItem = baseRepository.save(item);
        return OK(response(newItem));

    }

    @Override
    public Header<ItemApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(item -> response(item))
                .map(itemApiResponse -> OK(itemApiResponse))
                .orElseGet(() -> ERROR("데이터 없음"));
    }


    public ItemApiResponse response(Item item) {

        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .build();

        return body;
    }
}
