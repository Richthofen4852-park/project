package com.example.calcapi.service;

import com.example.calcapi.entity.OrderGroup;
import com.example.calcapi.model.Header;
import com.example.calcapi.model.Pagination;
import com.example.calcapi.model.request.OrderGroupApiRequest;
import com.example.calcapi.model.response.OrderGroupApiResponse;
import com.example.calcapi.repository.ItemRepository;
import com.example.calcapi.repository.OrderGroupRepository;
import com.example.calcapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.calcapi.model.Header.ERROR;
import static com.example.calcapi.model.Header.OK;

@Service
public class OrderGroupApiService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .user(userRepository.getOne(body.getUserId()))
                .item(itemRepository.getOne(body.getItemId()))
                .build();

        OrderGroup newOrderGroup = baseRepository.save(orderGroup);

        return OK(response(newOrderGroup));
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(orderGroup -> response(orderGroup))
                .map(orderGroupApiResponse -> OK(orderGroupApiResponse))
                .orElseGet(() -> ERROR("데이터 없음"));
    }

    public OrderGroupApiResponse response(OrderGroup orderGroup) {

        OrderGroupApiResponse body = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .userId(orderGroup.getUser().getId())
                .itemId(orderGroup.getItem().getId())
                .build();

        return body;
    }

    public Header<List<OrderGroupApiResponse>> search(Pageable pageable) {

        Page<OrderGroup> orderGroups = orderGroupRepository.findAll(pageable);

        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroups.stream()
                .map(orderGroup -> response(orderGroup))
                .collect(Collectors.toList());

        // List<UserApiResponse>
        // Header<List<UserApiResponse>>

        Pagination pagination = Pagination.builder()
                .totalPages(orderGroups.getTotalPages())
                .totalElements(orderGroups.getTotalElements())
                .currentPage(orderGroups.getNumber())
                .currentElements(orderGroups.getNumberOfElements())
                .build();

        return OK(orderGroupApiResponseList, pagination);
    }

}
