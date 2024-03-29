package com.example.teamarket.order.mapper;

import com.example.teamarket.order.dto.response.OrderInfoDto;
import com.example.teamarket.order.entities.Order;
import com.example.teamarket.order.service.KafkaService;
import com.example.teamarket.order.service.impl.OrderServiceImpl;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {KafkaService.class})
public interface OrderMapper {

    OrderInfoDto entityToInfoDto(Order order);
}
