package com.example.teamarket.order.mapper;

import com.example.teamarket.order.dto.response.OrderInfoDto;
import com.example.teamarket.order.entities.Order;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-23T04:09:57+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderInfoDto entityToInfoDto(Order order) {
        if ( order == null ) {
            return null;
        }

        Long orderId = null;
        String userEmail = null;
        Timestamp orderDate = null;
        String status = null;
        BigDecimal totalPrice = null;

        orderId = order.getOrderId();
        userEmail = order.getUserEmail();
        orderDate = order.getOrderDate();
        if ( order.getStatus() != null ) {
            status = order.getStatus().name();
        }
        totalPrice = order.getTotalPrice();

        OrderInfoDto orderInfoDto = new OrderInfoDto( orderId, userEmail, orderDate, status, totalPrice );

        return orderInfoDto;
    }
}
