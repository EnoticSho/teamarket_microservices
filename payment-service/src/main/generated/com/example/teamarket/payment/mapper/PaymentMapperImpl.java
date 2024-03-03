package com.example.teamarket.payment.mapper;

import com.example.teamarket.payment.dto.request.PaymentRequest;
import com.example.teamarket.payment.dto.response.PaymentInfoDto;
import com.example.teamarket.payment.entity.Payment;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-23T04:10:06+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public Payment dtoToEntity(PaymentRequest paymentRequest) {
        if ( paymentRequest == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setOrderId( paymentRequest.orderId() );
        payment.setEmail( paymentRequest.email() );
        payment.setTotal( paymentRequest.total() );

        return payment;
    }

    @Override
    public PaymentInfoDto entityInfoDto(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        String status = null;
        Long id = null;
        Long orderId = null;
        String email = null;
        BigDecimal total = null;
        Timestamp created = null;

        status = enumToString( payment.getStatus() );
        id = payment.getId();
        orderId = payment.getOrderId();
        email = payment.getEmail();
        total = payment.getTotal();
        created = payment.getCreated();

        PaymentInfoDto paymentInfoDto = new PaymentInfoDto( id, orderId, email, total, status, created );

        return paymentInfoDto;
    }
}
