package com.example.calcapi.service;

import com.example.calcapi.entity.User;
import com.example.calcapi.model.Header;
import com.example.calcapi.model.request.UserApiRequest;
import com.example.calcapi.model.response.UserApiResponse;
import com.example.calcapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.calcapi.model.Header.ERROR;
import static com.example.calcapi.model.Header.OK;

@Service
public class UserApiService extends BaseService<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. User 생성
        User user = User.builder()
                .name(userApiRequest.getName())
                .build();

        User newUser = baseRepository.save(user);

        // 3. 생성된 데이터 -> userApiResponse return


        return OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // id -> repository getOne, getById
        return baseRepository.findById(id)
                .map(user -> response(user))
                .map(userApiResponse -> OK(userApiResponse))
                .orElseGet(() -> ERROR("데이터 없음"));

    }

    private UserApiResponse response(User user) {
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();

        return userApiResponse;
    }
}
