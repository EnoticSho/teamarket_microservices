package com.example.teamarket.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardInfo {

    private String cardNumber;
    private String cardValidity;
    private Integer CVV;
}
