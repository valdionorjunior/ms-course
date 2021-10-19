package com.microservice.hrpayroll.services;

import com.microservice.hrpayroll.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Payment getPayment( long workerId, int days){

        //mocado por enquando
        return new Payment("valdionorMock",300.0, days );
    }
}
