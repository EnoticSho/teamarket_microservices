package com.example.teamarket.payment.service;

import com.example.teamarket.payment.dto.request.PaymentRequest;
import com.example.teamarket.payment.dto.response.PaymentInfoDto;
import com.example.teamarket.payment.entity.Payment;

public interface PaymentService {
    void processPayment(PaymentRequest paymentRequest);
    void sendResponse(PaymentInfoDto paymentInfoDto);
}
