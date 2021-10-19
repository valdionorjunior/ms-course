package com.microservice.hrpayroll.services;

import com.microservice.hrpayroll.entities.Payment;
import com.microservice.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")//buscando valor da variavel de ambiente no properties
    private String workerHost;

    @Autowired //injeta a instalcia pra disponibilizar pora consumir de outros MS
    private RestTemplate restTemplate;

    public Payment getPayment( long workerId, int days){
        String url = workerHost.concat("/workers/{id}");
        //criando mapara de parametro para request http
        Map<String, String> uriVariables = new HashMap<>();
        //chave/valor do objeto parametro
        uriVariables.put("id", String.valueOf(workerId));

        //realizando a request pro outrara o ms worker usando restTemplate
        Worker worker = restTemplate.getForObject(url, Worker.class, uriVariables);

        return new Payment(worker.getName(),worker.getDailyIncome(), days );
    }
}
