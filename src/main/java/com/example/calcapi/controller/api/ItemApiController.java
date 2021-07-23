package com.example.calcapi.controller.api;

import com.example.calcapi.controller.CrudController;
import com.example.calcapi.entity.Item;
import com.example.calcapi.model.request.ItemApiRequest;
import com.example.calcapi.model.response.ItemApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {


}
