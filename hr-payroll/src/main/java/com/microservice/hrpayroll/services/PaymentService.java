package com.microservice.hrpayroll.services;

import com.microservice.hrpayroll.entities.Payment;
import com.microservice.hrpayroll.entities.Worker;
import com.microservice.hrpayroll.feignclients.WorkerFeignClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClients workerFeignClients;

    public Payment getPayment( long workerId, int days){

        Worker worker = workerFeignClients.findById(workerId).getBody();

        return new Payment(worker.getName(),worker.getDailyIncome(), days );
    }
}
