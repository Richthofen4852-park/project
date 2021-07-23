package com.example.calcapi.controller.api;

import com.example.calcapi.controller.CrudController;
import com.example.calcapi.entity.OrderGroup;
import com.example.calcapi.model.request.OrderGroupApiRequest;
import com.example.calcapi.model.response.OrderGroupApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* CREATE TABLE IF NOT EXISTS `project`.`order_group` (
        `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
        `user_id` BIGINT(20) NOT NULL,
        `item_id` INT NOT NULL,
        PRIMARY KEY (`id`)) */

@RestController
@RequestMapping("/settlement")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {


}
