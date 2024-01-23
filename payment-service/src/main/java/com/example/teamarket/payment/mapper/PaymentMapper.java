package com.example.teamarket.payment.mapper;

import com.example.teamarket.payment.dto.request.PaymentRequest;
import com.example.teamarket.payment.dto.response.PaymentInfoDto;
import com.example.teamarket.payment.entity.Payment;
import com.example.teamarket.payment.service.PaymentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {PaymentService.class})
public interface PaymentMapper {

    Payment dtoToEntity(PaymentRequest paymentRequest);

    @Mapping(source = "status", target = "status", qualifiedByName = "enumToString")
    PaymentInfoDto entityInfoDto(Payment payment);

    @Named("enumToString")
    default String enumToString(Enum<?> e) {
        return e != null ? e.name() : null;
    }
}
