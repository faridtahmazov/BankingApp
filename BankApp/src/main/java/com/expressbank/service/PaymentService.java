package com.expressbank.service;

import com.expressbank.entity.Card;
import com.expressbank.entity.Payment;
import com.expressbank.repository.CardRepo;
import com.expressbank.repository.PaymentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepo paymentRepo;

    public void save(Payment payment){
        paymentRepo.save(payment);
    }

    public Optional<Payment> findById(int id){
        return paymentRepo.findById(id);
    }

    public List<Payment> findAll(){
        return paymentRepo.findAll();
    }

    public void remove(int id){
        Optional<Payment> payment = findById(id);
        if (payment!=null){
            paymentRepo.delete(payment.get());
        }
    }
}
