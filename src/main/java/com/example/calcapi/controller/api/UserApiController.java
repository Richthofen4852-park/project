package com.example.calcapi.controller.api;

import com.example.calcapi.controller.CrudController;
import com.example.calcapi.entity.User;
import com.example.calcapi.model.request.UserApiRequest;
import com.example.calcapi.model.response.UserApiResponse;
import com.example.calcapi.service.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private UserApiService userApiService;

}
